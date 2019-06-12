# maven-auto-generate-test-plugin 

## 介绍  
你还在为写大量mock测试而烦恼吗，你还在苦苦的构建包装类的值吗？这里有一款简单的mock测试代码自动生成插件，解决开发人员单元覆盖率低，浪费测试时间的问题，全面提高开发人员的测试效率和测试时间。 

简称：MAGT-plugin    


## 使用  

1. 首先依赖插件：
```xml
<plugin>
    <groupId>com.uifuture.maven.plugins</groupId>
    <artifactId>maven-auto-unit-test-plugin</artifactId>
    <version>1.0.0</version>
    <configuration>
        <testPackageName>com.jiaxuan.heaven.book.service.impl</testPackageName>
        <childPackage>true</childPackage>
        <configPath>/config/test/</configPath>
        <mockPackage>com.jiaxuan.heaven.book.mapper</mockPackage>
        <configFileName>init.ftl</configFileName>
        <skipPackages>com.baomidou.mybatisplus.core.metadata;com.baomidou.mybatisplus.core.conditions</skipPackages>
        <otherProjectName>api;</otherProjectName>
    </configuration>
</plugin>
```  
**下载不了属于正常情况，还未上传至中央仓库...请耐心等待**    

2. 下载配置文件  
配置好```<configPath></configPath>```填写路径，相对路径为当前运行项目的根路径。(默认下载路径：/src/main/resources/test/template)   
运行插件的init命令，即可将配置文件下载到对应的路径。  
  
可设置配置文件的文件名，通过```<configFileName>init.ftl</configFileName>```设置配置文件的文件名称。（默认文件名称为test.ftl）  

3. 引入mock相关依赖  
自动测试代码生成插件   
项目需要依赖Jar包：
```xml

<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
    <version>1.7.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito2</artifactId>
    <version>1.7.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>2.8.47</version>
    <scope>test</scope>
    
</dependency>

``` 

3. configuration中相关配置说明 
  
运行插件的test命令即可在对应的test路径下生成测试用例。    
- ```<testPackageName>``` - 配置需要生成单元测试用例的包名（必填）  
- ```<childPackage>``` - 配置testPackageName的包是否递归获取子包下的类    
- ```<mockPackage>``` - 需要mock掉的类所在的包名   
- ```<configPath>``` - 下载配置文件的路径  
- ```<configFileName>``` - 下载下来的配置文件的名称   
- ```<skipPackages>``` - 在方法参数初始化的时候，需要进行赋值为null的类（例如，适合接口或者没有空参构造的类），在该包下的类不会进行赋值。可配置多个，使用英文分号隔开。       
- ```<otherProjectName>``` - 适合多项目，配置其他的项目名称，可以使MAGT-plugin解析到该模块下的所有类。一个项目下多个项目模块。项目模块的路径名称。可配置多个，使用英文分号隔开。         

# 版本功能

## V1.0.0    
1. 支持包下所有类中公共非静态方法生成测试方法   
2. 支持配置mock的包，将mock掉包下类的所有方法  
3. 支持基础类型和包装类型自动赋值  
4. 增加枚举参数的支持 
5. 可进行配置需要跳过的参数类型，直接配置包名，会进行跳过包内所有类的构造（用于跳过接口的构造，直接赋值为null） 
6. 优化全限定名称为简称，使用import导入包，名称存在重复的类，使用全限定名称  

