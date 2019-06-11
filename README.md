# auto-unit-test-plugin 

## 介绍 
你还在为写大量mock测试而烦恼吗，你还在苦苦的构建包装类的值吗？这里有一款简单的mock测试代码自动生成插件，解决开发人员单元覆盖率低，浪费测试时间的问题，全面提高开发人员的测试效率和测试时间。  

## 使用  

1. 首先依赖插件：
```xml
<plugin>
    <groupId>com.uifuture.maven.plugins</groupId>
    <artifactId>maven-auto-unit-test-plugin</artifactId>
    <version>1.0.1</version>
    <configuration>
        <testPackageName>com.jiaxuan.heaven.book.service.impl</testPackageName>
        <childPackage>true</childPackage>
        <configPath>/config/test/</configPath>
        <mockPackage>com.jiaxuan.heaven.book.mapper</mockPackage>
    </configuration>
</plugin>
```  
下载不了属于正常情况，还未上传至中央仓库...请耐心等待    

2. 下载配置文件  
```xml

```

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

3. 插件配置说明 
```xml

```

# 版本功能



## V1.0.1  
1. 支持包下所有类中公共非静态方法生成测试方法   
2. 支持配置mock的包，将mock掉包下类的所有方法  
3. 支持基础类型和包装类型自动赋值  
