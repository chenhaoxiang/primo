# maven-auto-generate-test-plugin 

## 介绍  
你还在为写大量mock测试而烦恼吗，你还在苦苦的构建包装类的值吗？这里有一款简单mock测试代码自动生成的Maven插件，解决开发人员单元覆盖率低，浪费测试时间的问题，全面优化开发人员的测试效率和测试时间。 

简称：MAGT-plugin    

项目地址： https://github.com/chenhaoxiang/maven-auto-generate-test-plugin  


## 使用  

1. 首先依赖插件：
```xml
<plugin> 
  <groupId>com.uifuture.maven.plugins</groupId>
  <artifactId>maven-auto-generate-test-plugin</artifactId>
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
**1.0.0版本已上传至中央仓库.**    

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
- ```<testPackageName>``` - 配置需要生成单元测试用例的包名（必填），不要填写接口所在包名，需要实现类所在包名,会遍历包下以及子包下所有类和类的方法     
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

## V1.1.0 - 开发中 
1. 支持配置选择是否自动mock掉父类的方法 - 默认true  
2. 支持配置静态方法mock，默认不支持，需要配置    
3. 支持配置实体基础类型随机设置/使用默认值空值  
~~4. 每个测试类使用统一的before注解进行mock方法~~(考虑到后面每个分支的mock，如果同意进行mock的话，会导致分支无法全面覆盖)   
5. 测试类中的私有方法进行mock，私有方法专门开方法进行生成mock测试，默认不支持，需要配置     
6. mock注解的类，使用了全限定名称，优化为简称，类进行导入，重复类简称，第一个类使用简称，后面的类使用全限定名称  
7. 不再支持配置其他包下的类进行mock，非测试类的所有方法均进行mock，测试类的私有方法也进行mock  
8. 已生成测试类，不再进行覆盖生成  
9. 支持第三方包类的加载和构造   


## V1.1.2 - 开发中
1. 支持if-else生成多个mock分支方法  
2. 支持新增方法对应新增测试方法  
3. 支持重载方法的mock    
4. 支持多级参数构造赋值  
5. 支持简单集合构造赋值 
6. 支持mock方法进行配置，而非返回null      

### 注意 
配置mock静态方法：
默认使用@RunWith(MockitoJUnitRunner.class)，如果配置了mock静态方法，将使用@RunWith(PowerMockRunner.class)。

使用PowerMockRunner与MockitoJUnitRunner类，都无法支持父类中的属性（service的实现类中又同时注入了该类）自动注入的mock（例如mybatis中service层的泛型父类中的泛型baseMapper）。这是由于Mock类会将这两个类作为不同的实例来进行处理，只会mock掉你注入service实现类的基类，而无法注入service实现类的父类中的mapper。  
例如： 
service实现类  
```java

public class WorkFlowServiceImpl extends ServiceImpl<WorkFlowMapper, WorkFlowEntity> implements IWorkFlowService {
    @Autowired
    private WorkFlowMapper workFlowMapper;
    //...
}
```
父类ServiceImpl：
```java
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
    @Autowired
    protected M baseMapper;
    //...
}
```
在WorkFlowServiceImpl中使用时：
```java
baseMapper.deleteById("1");
```
在mock测试类中：
```java

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest({BeanConvertUtil.class, FieldBaseDto.class})
public class WorkFlowServiceImplTest {
    @InjectMocks
    private WorkFlowServiceImpl workFlowServiceImpl;
    @Mock
    private WorkFlowMapper workFlowMapper;
    //...
}
```
无法进行mock掉baseMapper实例，这是由于baseMapper与workFlowMapper并不是同一个实例！这里仅仅只能mock掉workFlowMapper。  


目前可以在service层的实现类中将baseMapper再次注入，则使用PowerMockRunner.class也可以进行mock  

service层的实现类不推荐使用泛型基类service父类进行调用泛型mapper操作数据库层！可以选择注入Mapper再进行调用。  
   
   

# 使用体验  
199个测试方法，一共覆盖309个被测试方法，使用magt生成后，仅仅只使用了3个多小时进行mock优化（仅仅优化了运行报错的方法，没有进行完善分支测试）。     

按照我以前的经验，如果全部由自己写，一切顺利的情况下，199个方法的mock测试，至少要多出几倍的时间。  
（此测试项目为使用mybatis-plus的项目，service层的实现类非常多的方法直接使用了父类方法，导致mock很麻烦，耽搁了一些时间，其他项目相对而言会节省更多时间）      

## 测试类：  
![测试类](https://raw.githubusercontent.com/chenhaoxiang/maven-auto-generate-test-plugin/master/doc/images/20190619221913.jpg)
## 测试方法：
![测试方法](https://raw.githubusercontent.com/chenhaoxiang/maven-auto-generate-test-plugin/master/doc/images/20190619223834.jpg)   
## 单元覆盖数据：  
![总覆盖率](https://raw.githubusercontent.com/chenhaoxiang/maven-auto-generate-test-plugin/master/doc/images/20190620151913.jpg)   
--- 

![单元覆盖数据](https://raw.githubusercontent.com/chenhaoxiang/maven-auto-generate-test-plugin/master/doc/images/20190620151630.jpg)   

目前1.0.0版本不支持分支的覆盖，导致覆盖率不高，将在1.0.2版本推出分支的多方法mock。大大提高分支覆盖率。      

# 贡献人员 
