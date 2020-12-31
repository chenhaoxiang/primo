package wiki.primo.dubbo.swagger.javadoc;

import com.alibaba.dubbo.doc.annotation.InterfaceDesc;
import com.alibaba.dubbo.doc.annotation.MethodDesc;
import com.alibaba.dubbo.doc.annotation.MethodParamDesc;
import com.alibaba.dubbo.doc.annotation.MethodRetDesc;
import com.alibaba.fastjson.JSON;
import wiki.primo.dubbo.swagger.api.ApiMethod;
import wiki.primo.dubbo.swagger.api.ApiParam;
import wiki.primo.dubbo.swagger.core.common.SwaggerMoreException;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.RootDoc;
import io.swagger.annotations.Api;
import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;

/**
 * DubboSwaggerDoclet
 *
 * @author chenhx
 * @date 2019-08-28 10:31
 */
public class DubboSwaggerDoclet {
    public static final String OPTION_CLASS_DIR = "-classDir";
    private static String classDir;

    public static boolean start(RootDoc rootDoc) {
        if (StringUtils.isEmpty(classDir)) {
            rootDoc.printError("-classDir is not specified.");
            return false;
        }
        rootDoc.printNotice("Writing classes to " + classDir);
        try {
            parseAndAnnotate(rootDoc);
        } catch (Exception e) {
            rootDoc.printError(e.getMessage());
            rootDoc.printError(Arrays.toString(e.getStackTrace()));
            if (nonNull(e.getCause())) {
                rootDoc.printError(e.getCause().getMessage());
                rootDoc.printError(Arrays.toString(e.getCause().getStackTrace()));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 调试入口
        String[] docArgs = new String[]{"-doclet", DubboSwaggerDoclet.class.getName(), System.getProperty("user.dir") + "/primo-dubbo-swagger-annotations/src/main/java/" + "/com/souche/swagger/more/demoApi/RoleService.java",
                "-classDir", System.getProperty("user.dir") + "/primo-dubbo-swagger-annotations/target/classes"};
        com.sun.tools.javadoc.Main.execute(docArgs);
    }

    private static void parseAndAnnotate(RootDoc rootDoc) throws ClassNotFoundException, NotFoundException, CannotCompileException, IOException {
        for (ClassDoc classDoc : rootDoc.classes()) {
            Class clazz = Class.forName(classDoc.qualifiedName());
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(new ClassClassPath(clazz));
            CtClass ctClass = pool.get(clazz.getName());
            boolean hidden = classDoc.getRawCommentText().contains("@hidden");
            if (hidden) {
                continue;
            }
            if (!StringUtils.isEmpty(classDoc.getRawCommentText())) {
                annotateApiAndInterfaceDesc(ctClass, clazz, classDoc.commentText(), hidden);
            }
            for (MethodDoc methodDoc : classDoc.methods()) {
                Method method = Stream.of(clazz.getDeclaredMethods()).filter(m -> m.getName().equals(methodDoc.name()))
                        .filter(m -> methodDoc.signature().equals("(" + Stream.of(m.getParameterTypes()).map(Class::getName).collect(joining(", ")) + ")"))
                        .findAny().orElseThrow(() -> new SwaggerMoreException("Unable to find the corresponding method based on methodDoc " + methodDoc.name() + methodDoc.signature()));
                List<String> names = newArrayList();
                List<String> values = newArrayList();
                for (ParamTag paramTag : methodDoc.paramTags()) {
                    names.add(paramTag.parameterName());
                    values.add(paramTag.parameterComment());
                }
                String returnDescription = methodDoc.tags("return").length == 0 ? "" : methodDoc.tags("return")[0].text();
                hidden = methodDoc.getRawCommentText().contains("@hidden");
                if (!StringUtils.isEmpty(methodDoc.getRawCommentText())) {
                    addApiMethodAndMethodDesc(ctClass, method, methodDoc.commentText(), names, values, returnDescription, hidden);
                } else {
                    rootDoc.printWarning("No javadoc found in method " + classDoc.qualifiedName() + "." + methodDoc.name() + methodDoc.signature());
                }
            }
            ctClass.writeFile(classDir);
            rootDoc.printNotice("Successfully annotated " + clazz.getTypeName());
        }
    }

    private static void annotateApiAndInterfaceDesc(CtClass ctClass, Class clazz,
                                                    String apiDescription,
                                                    boolean hidden) {
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        apiDescription = StringUtils.isEmpty(apiDescription) ? clazz.getSimpleName() : apiDescription;
        Api api = AnnotationUtils.findAnnotation(clazz, Api.class);
        AnnotationsAttribute attr = getAnnotationAttr(ctClass);
        if (isNull(api)) {
            Annotation apiAnn = new Annotation(Api.class.getName(), constPool);
            apiAnn.addMemberValue("hidden", new BooleanMemberValue(hidden, constPool));
            if (!hidden) {
                ArrayMemberValue tagsMemberValue = new ArrayMemberValue(constPool);
                tagsMemberValue.setValue(new MemberValue[]{new StringMemberValue(apiDescription, constPool)});
                apiAnn.addMemberValue("tags", tagsMemberValue);
            }
            attr.addAnnotation(apiAnn);
        } else {
            apiDescription = api.tags().length == 0 || StringUtils.isEmpty(api.tags()[0]) ? apiDescription : api.tags()[0];
        }
        InterfaceDesc interfaceDesc = AnnotationUtils.findAnnotation(clazz, InterfaceDesc.class);
        if (isNull(interfaceDesc)) {
            Annotation interfaceDescAnn = new Annotation(InterfaceDesc.class.getName(), constPool);
            interfaceDescAnn.addMemberValue("value", new StringMemberValue(apiDescription, constPool));
            attr.addAnnotation(interfaceDescAnn);
        }
        ctClass.getClassFile().addAttribute(attr);
    }

    private static void addApiMethodAndMethodDesc(CtClass ctClass, Method method,
                                                  String description,
                                                  List<String> names,
                                                  List<String> values,
                                                  String returnDescription,
                                                  boolean hidden) throws NotFoundException {
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        for (CtMethod ctMethod : ctClass.getDeclaredMethods(method.getName())) {
            if (Objects.equals(Stream.of(ctMethod.getParameterTypes()).map(CtClass::getSimpleName).collect(joining()),
                    Stream.of(method.getParameterTypes()).map(Class::getSimpleName).collect(joining()))) {
                AnnotationsAttribute attr = getAnnotationAttr(ctMethod);
                ApiMethod apiMethod = AnnotationUtils.findAnnotation(method, ApiMethod.class);
                if (isNull(apiMethod)) {
                    Annotation apiMethodAnn = new Annotation(ApiMethod.class.getName(), constPool);
                    apiMethodAnn.addMemberValue("value", new StringMemberValue(description, constPool));
                    apiMethodAnn.addMemberValue("hidden", new BooleanMemberValue(hidden, constPool));
                    ArrayMemberValue paramsArrayMemberValue = new ArrayMemberValue(constPool);
                    MemberValue[] memberValues = new MemberValue[names.size()];
                    for (int i = 0; i < names.size(); i++) {
                        Annotation apiParamAnn = new Annotation(ApiParam.class.getName(), constPool);
                        apiParamAnn.addMemberValue("name", new StringMemberValue(names.get(i), constPool));
                        apiParamAnn.addMemberValue("value", new StringMemberValue(values.get(i), constPool));
                        memberValues[i] = new AnnotationMemberValue(apiParamAnn, constPool);
                    }
                    paramsArrayMemberValue.setValue(memberValues);
                    apiMethodAnn.addMemberValue("params", paramsArrayMemberValue);
                    attr.addAnnotation(apiMethodAnn);
                } else {
                    names = newArrayList();
                    values = newArrayList();

                    //通过方法获取 - 参数名称
                    Parameter[] par = method.getParameters();
                    if (par != null) {
                        for (Parameter p : par) {
                            String name = p.getName();
                            names.add(name);
                        }
                    }
                    if (names.size() != apiMethod.params().length) {
                        throw new SwaggerMoreException("[DubboSwaggerDoclet->addApiMethodAndMethodDesc]Method " + method.getDeclaringClass().getName() + "." + method.getName() + "，The number of parameters does not match the number of parameters in the comment，names=" + JSON.toJSONString(names) + ", names.size()=" + names.size() + ", apiMethod.params().length=" + apiMethod.params().length);
                    }

                    ApiParam[] params = apiMethod.params();
                    for (int i = 0; i < params.length; i++) {
                        names.add(names.get(i));
                        values.add(params[i].value());
                    }
                    description = apiMethod.value();
                    hidden = apiMethod.hidden();
                }
                if (isNull(AnnotationUtils.findAnnotation(method, MethodDesc.class))) {
                    Annotation methodDescAnn = new Annotation(MethodDesc.class.getName(), constPool);
                    methodDescAnn.addMemberValue("value", new StringMemberValue(description, constPool));
                    attr.addAnnotation(methodDescAnn);
                }
                if (isNull(AnnotationUtils.findAnnotation(method, MethodParamDesc.class))) {
                    Annotation methodParamDescAnn = new Annotation(MethodParamDesc.class.getName(), constPool);
                    ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
                    MemberValue[] memberValues = new MemberValue[names.size()];
                    for (int i = 0; i < names.size(); i++) {
                        memberValues[i] = new StringMemberValue(names.get(i) + " : " + values.get(i), constPool);
                    }
                    arrayMemberValue.setValue(memberValues);
                    methodParamDescAnn.addMemberValue("value", arrayMemberValue);
                    attr.addAnnotation(methodParamDescAnn);
                }
                if (isNull(AnnotationUtils.findAnnotation(method, MethodRetDesc.class))) {
                    returnDescription = StringUtils.isEmpty(returnDescription) ? method.getReturnType().getSimpleName() : returnDescription;
                    Annotation methodRetDesc = new Annotation(MethodRetDesc.class.getName(), constPool);
                    methodRetDesc.addMemberValue("value", new StringMemberValue(returnDescription, constPool));
                    attr.addAnnotation(methodRetDesc);
                }
                ctMethod.getMethodInfo().addAttribute(attr);
            }
        }
    }

    public static boolean validOptions(String[][] options, DocErrorReporter reporter) {
        Arrays.stream(options).forEach(s -> {
            if (OPTION_CLASS_DIR.equalsIgnoreCase(s[0])) {
                classDir = s[1];
            }
        });
        return true;
    }

    public static int optionLength(String option) {
        return OPTION_CLASS_DIR.equalsIgnoreCase(option) ? 2 : 0;
    }

    private static AnnotationsAttribute getAnnotationAttr(CtClass ctClass) {
        for (Object o : ctClass.getClassFile().getAttributes()) {
            if (o instanceof AnnotationsAttribute) {
                return (AnnotationsAttribute) o;
            }
        }
        return new AnnotationsAttribute(ctClass.getClassFile().getConstPool(), AnnotationsAttribute.visibleTag);
    }

    private static AnnotationsAttribute getAnnotationAttr(CtMethod ctMethod) {
        for (Object o : ctMethod.getMethodInfo().getAttributes()) {
            if (o instanceof AnnotationsAttribute) {
                return (AnnotationsAttribute) o;
            }
        }
        return new AnnotationsAttribute(ctMethod.getMethodInfo().getConstPool(), AnnotationsAttribute.visibleTag);
    }
}
