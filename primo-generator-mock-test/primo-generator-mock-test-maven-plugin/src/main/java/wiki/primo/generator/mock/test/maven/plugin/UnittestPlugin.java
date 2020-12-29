/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.maven.plugin;

import com.alibaba.fastjson.JSON;
import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.core.util.PackageUtils;
import wiki.primo.generator.mock.test.core.util.StringUtils;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import wiki.primo.generator.mock.test.maven.plugin.base.AbstractPlugin;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建自动化测试
 *
 * @author chenhx
 * @version UnittestPlugin.java, v 0.1 2019-05-30 17:47 chenhx
 */
@Mojo(name = "test")
public class UnittestPlugin extends AbstractPlugin {

    /**
     * 需要测试的项目包名
     */
    @Parameter
    private String testPackageName;
    /**
     * 作者
     */
    @Parameter(defaultValue = "")
    private String author;

    /**
     * 是否获取子包下的类
     */
    @Parameter(defaultValue = "true")
    private Boolean isGetChildPackage;

    /**
     * 配置是否mock掉父类以及自身测试类非测试的方法(默认true)
     */
    @Parameter(defaultValue = "true")
    private Boolean isMockThisOtherMethod;

    /**
     * 配置是否设置基础类型的值随机生成(默认false)
     */
    @Parameter(defaultValue = "false")
    private Boolean isSetBasicTypesRandomValue;

    /**
     * 配置字符串随机值的位数（例如："10"，表示10位随机字母/数字字符）
     * V1.1.2+
     */
    @Parameter(defaultValue = "10")
    private String setStringRandomRange;
    /**
     * 配置int/Integer类型随机值的范围（例如："0,1000"，表示[0,1000)范围的int数值，配置固定的值可配置为"0",则int值固定为0）
     * V1.1.2+
     */
    @Parameter(defaultValue = "0,1000")
    private String setIntRandomRange;
    /**
     * 配置long/Long类型随机值的范围(配置规则与setIntRandomRange类似)
     * V1.1.2+
     */
    @Parameter(defaultValue = "0,10000")
    private String setLongRandomRange;
    /**
     * 配置boolean/Boolean类型随机值的范围（例如：配置为"true"/"false"表示为固定的值，其他任意值表示true和false随机）
     * V1.1.2+
     */
    @Parameter(defaultValue = "true,false")
    private String setBooleanRandomRange;



    public static void main(String[] args) throws IOException {
        builder();
    }
    private static void builder() throws IOException {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSource(new File("/Users/chenhx/Desktop/github/auto-generate-test-maven-plugin/agt-core/src/main/java/com/uifuture/maven/plugins/core/model/JavaClassModel.java"));
        JavaClass javaClass1 = builder.getClassByName("JavaClassModel");
        JavaClass javaClass2 = builder.getClassByName("com.uifuture.maven.plugins.returnData.model.JavaClassModel");
        System.out.println(javaClass1 + "====" + javaClass2);
        JavaClass javaClass3 = builder.getClassByName("JavaProjectBuilder");
        JavaClass javaClass4 = builder.getClassByName("com.thoughtworks.qdox.JavaProjectBuilder");
        System.out.println(javaClass3 + "====" + javaClass4);
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            //设置配置值
            super.execute();

            //初始化的一些操作
            init();

            getLog().info("数据初始化后，开始生成自动化测试代码" +
                    "\n开发者的配置数据：" + CommonConstant.CONFIG_ENTITY
            );

            //参数校验
            if (!paramCheck()) {
                return;
            }


            //记录每个包名下的所有类,key-包名，value-所有类的集合
            Map<String, List<String>> javaListMap = new HashMap<>();
            //如果配置的是java类，直接使用该类
            testPackageName = testPackageName.trim();
            if (testPackageName.endsWith(".java")) {
                //单类其实只是为了方便测试类中方法的生成，并不会进行文档说明使用
                List<String> javaList = new ArrayList<>();
                testPackageName = testPackageName.substring(0, testPackageName.lastIndexOf(".java"));
                String classPathName = basedir + "/src/main/java/" + testPackageName.replace(".", "/") + ".java";
                getLog().info("待生成单元测试类代码的类路径为："+classPathName);
                javaList.add(classPathName);
                javaListMap.put(testPackageName.substring(0, testPackageName.lastIndexOf(".")), javaList);
            } else {

                //遍历包名，多个包的情况
                if (testPackageName.contains(";")) {
                    String[] packageNames = testPackageName.split(";");
                    for (String packageName : packageNames) {
                        if (StringUtils.isEmpty(packageName)) {
                            continue;
                        }
                        //换行，空格去除
                        packageName = packageName.replaceAll("\\r|\\n", "").trim();
                        //获取该包下所有的类
                        List<String> javaList = PackageUtils.getClassName(basedir.getPath(), packageName, isGetChildPackage);
                        javaListMap.put(packageName, javaList);
                    }

                } else {
                    //获取该包下所有的类
                    List<String> javaList = PackageUtils.getClassName(basedir.getPath(), testPackageName, isGetChildPackage);
                    javaListMap.put(testPackageName, javaList);
                }

            }

            //遍历javaListMap，需要生成测试类的包
            for (String packageName : javaListMap.keySet()) {
                //设置当前进行生成类的测试包名
                List<String> javaList = javaListMap.get(packageName);
                getLog().info("当前遍历的包名为：" + packageName );
                //class文件绝对路径
                for (String javaNamePath : javaList) {
                    getLog().info("当前java文件的绝对路径为：" + javaNamePath);
                    if (javaNamePath.endsWith("$1")) {
                        //跳过
                        getLog().info("跳过内部类的生成:" + javaNamePath);
                        continue;
                    }
                    //准备参数值
                    JavaClassInfo javaClassInfo = new JavaClassInfo();

                    javaClassInfo.setPackageName(packageName);
    //                if(StringUtil.isNotEmpty( CommonConstant.CONFIG_ENTITY.getTestClassPackageName() )){
    //                    javaClassInfo.setPackageName(CommonConstant.CONFIG_ENTITY.getTestClassPackageName());
    //                }

                    javaClassInfo.setAbsolutePath(javaNamePath);
                    javaClassInfo.setFullyTypeName(javaClassInfo.getPackageName() + "." + javaClassInfo.getTypeName());

                    //生成测试类文件路径   被测试类的绝对路径
                    String testJavaName = CommonConstant.CONFIG_ENTITY.getBasedir() + CommonConstant.JAVA_TEST_SRC + javaClassInfo.getPackageName().replace(".", "/") + "/" + javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX + ".java";
                    javaClassInfo.setTestAbsolutePath(testJavaName);
                    getLog().info("生成的类信息javaClassInfo：" + JSON.toJSONString(javaClassInfo));

                    //核心
                    GenJava.genTest(javaClassInfo);
                }
            }
        } finally {
            //最终的一些操作
            DownFile.removeTemplateFile();
        }
    }

    /**
     * 初始化操作
     */
    private void init() {
        CommonConstant.CONFIG_ENTITY.setTestPackageName(testPackageName);
        CommonConstant.CONFIG_ENTITY.setAuthor(author);
        CommonConstant.CONFIG_ENTITY.setIsGetChildPackage(isGetChildPackage);
        CommonConstant.CONFIG_ENTITY.setIsMockThisOtherMethod(isMockThisOtherMethod);
        CommonConstant.CONFIG_ENTITY.setIsSetBasicTypesRandomValue(isSetBasicTypesRandomValue);
        CommonConstant.CONFIG_ENTITY.setSetStringRandomRange(setStringRandomRange);
        CommonConstant.CONFIG_ENTITY.setSetBooleanRandomRange(setBooleanRandomRange);
        CommonConstant.CONFIG_ENTITY.setSetIntRandomRange(setIntRandomRange);
        CommonConstant.CONFIG_ENTITY.setSetLongRandomRange(setLongRandomRange);

        //初始化json文件
        DownFile.downJsonFile();

        //下载配置文件
        DownFile.downTemplateFile();

        //初始化类源,加载类信息
        initJavaProjectBuilder();
    }

    /**
     * 参数校验
     *
     * @return 校验结果，true-校验通过，false-校验失败
     */
    private boolean paramCheck() {
        if (StringUtils.isEmpty(testPackageName)) {
            getLog().error("testPackageName必须进行配置（需要测试的项目包名）");
            return false;
        }
        return true;
    }

    /**
     * 初始化类源
     */
    private void initJavaProjectBuilder() {
        //读取包下所有的java类文件
        String mainJava = basedir.getPath() + CommonConstant.JAVA_MAIN_SRC;
        CommonConstant.javaProjectBuilder.addSourceTree(new File(mainJava));
        getLog().info("加载当前模块的类：" + mainJava);
        //读取包下所有的测试的java类文件
        String testJava = basedir.getPath() + CommonConstant.JAVA_TEST_SRC;
        CommonConstant.javaProjectBuilder.addSourceTree(new File(testJava));
        getLog().info("加载当前模块的测试类：" + testJava);

        //加载其他依赖的类 获取类加载器中的类
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader!=null) {
            CommonConstant.javaProjectBuilder.addClassLoader(loader);
        }
    }


}
