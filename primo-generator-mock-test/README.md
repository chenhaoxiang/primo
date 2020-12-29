# primo-generator-mock-test
 
## 介绍  
你还在为写大量单元测试而烦恼吗，你还在苦苦的构建包装类的值吗？这里有一款mock单元测试代码自动生成的Maven插件，解决开发人员消耗大量时间在单元测试的问题，全面优化开发人员的测试效率和测试时间。   
 
注意，本插件目前无法完成所有的mock测试，让你不用修改一行代码，暂时需要在primo-generator-mock-test生成mock测试代码的基础上再进行一点修改，例如分支覆盖，断言使用。

本插件的目标是：实现让开发人员不再写一行mock测试代码，primo-generator-mock-test帮你全部实现    
愿景是：减少开发人员的测试负担，专注业务开发与迭代  

（此项目目前在我公司几个团队使用，节省了团队成员非常多的单元测试时间，在此分享出来）  

## 使用  

### 1. 首先依赖插件：

#### 最新版本 

最新版本：1.0.0-SNAPSHOT

最简单配置：
```xml

<plugin>
    <groupId>wiki.primo.generator</groupId>
    <artifactId>primo-generator-mock-test-maven-plugin</artifactId>
    <version>（版本号）</version>
    <configuration>
        <testPackageName>（待测试类的包名，0.1.0-SNAPSHOT+支持配置多个，英文分号进行隔开）</testPackageName>
    </configuration>
</plugin>
```
（使用最简单的配置，即可使用，感觉后面说明过多的不用往下看）  

示例： 
```xml
<plugin> 
    <groupId>wiki.primo.generator</groupId>
    <artifactId>primo-generator-mock-test-maven-plugin</artifactId>
    <version>1.0.0-SNAPSHOT</version> 
        <configuration>
            <testPackageName>wiki.primo.generator.primogeneratormocktestdemo.service.impl</testPackageName> 
        </configuration>
</plugin>
``` 

### 2. 生成测试代码 
在引入插件的项目模块下运行maven插件的 primo-generator-mock-test:test 命令    
```
mvn primo-generator-mock-test:test
```   

直接运行mvn primo-generator-mock-test:test即可下载模板文件&生成测试类  

相关配置：
```<configPath></configPath>```填写路径，相对路径为当前运行项目的根路径。(默认下载路径：/src/main/resources/test/template)   
第一次运行运行插件的primo-generator-mock-test:test命令，即可将配置文件下载到对应的路径。  
  
可设置配置文件的文件名，通过```<configFileName>primo-generator-mock-test.ftl</configFileName>```设置配置文件的文件名称。（默认文件名称为primo-generator-mock-test.ftl）  

### 3. 引入mock相关依赖  
自动测试代码生成插件   
插件生成的mock测试类方法依赖powermock&mockito，建议直接引入如下依赖（不依赖对于插件的运行没有影响）   
```xml
<dependency>
    <groupId>wiki.primo.generator</groupId>
    <artifactId>primo-generator-mock-test-jar</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>

``` 

### 4. configuration中相关配置属性  
在引入插件的项目名模块下，运行插件的megatron:test命令即可在对应的test路径下生成测试用例。

#### 必填  
- ```<testPackageName>```:必填）配置需要生成单元测试用例的包名，不要填写接口所在包名，需要实现类所在包名,会遍历包下以及子包下所有类和类的方法（支持配置多个包名，英文分号隔开不同的包名）。
可以配置单个类。例如：wiki.primo.generator.primogeneratormocktestdemo.service.impl.UserServiceImpl.java 
注意，配置单个类一定要以.java结尾。  

不支持的类：  
- 接口
- 枚举
- 抽象类
- 私有类     

#### 选填 

注意：在1.0.0-SNAPSHOT版本之前的jar包未上传至中央仓库  

##### 1.0.0-SNAPSHOT
- ```<jsonConfigPath>```: json配置文件路径,defaultValue = "/src/main/resources/test/template/"  
- ```<jsonConfigFileName>```: json配置文件名称,defaultValue = "primo-generator-mock-test.json"  
- ```<isDownloadTemplateFile>```:  是否将Template配置文件下载到本地，默认true 
- ```<isDownloadJsonFile>```:  是否将json配置文件下载到本地，默认true  


##### 0.1.1-SNAPSHOT
- ```<configPath>```:下载配置文件的路径  
- ```<author>```:作者名称    
- ```<configFileName>```:下载下来的配置文件的名称   
- ```<isGetChildPackage>```:配置testPackageName的包是否递归获取子包下的类(默认true)    
- ```<isMockThisOtherMethod>```:配置是否mock掉父类以及自身测试类非测试的方法(默认true),父类/本类方法调用需要使用this进行调用，目前版本无法支持mock        
- ```<isSetBasicTypesRandomValue>```:配置是否设置基础类型的值随机生成(默认false)
    
- ```<setStringRandomRange>```:配置字符串随机值的位数（例如："10"，表示10位随机字母/数字字符）    
- ```<setIntRandomRange>```:配置int/Integer类型随机值的范围（例如："0,1000"，表示[0,1000)范围的int数值，配置固定的值可配置为"0",则int值固定为0 ）    
- ```<setLongRandomRange>```:配置long/Long类型随机值的范围(配置规则与setIntRandomRange类似)   
- ```<setBooleanRandomRange>```:配置boolean/Boolean类型随机值的范围（例如：配置为"true"/"false"表示为固定的值，其他任意值表示true和false随机）    
 

# 版本功能


## 0.2.1-SNAPSHOT
- fix - 修复在windows下无法生成测试代码的bug

## 0.2.0-SNAPSHOT
- 支持配置json，通过json构造参数的值 
    - 只支持实际方法的参数赋值，mock的参数赋值暂时不支持
    - 本次只支持自定义的类型的值进行配置  
下面为json配置中属性的描述：  
```json
{
"isOpen": "是否开启json配置-默认false",
"list":
  [
    {
      "scope":"作用域：全局（global）、包（package）、类（class）、方法（method） - 默认全局",
      "scopeValue": "作用域的值，global则无需配置该值，package则为包名，class则为类名，method则为方法名", 
      "fullyType": "类型的全限定名称",
      "value":{
        "若type=base，则该值固定为value":"值",
        "若type=custom，自定义类型，value下的key为fastjson序列化的属性名称":"值"
      }
    }
  ]
}
```
- 删除废弃的配置  
- fix - 修复修改configFileName时无法下载配置文件的bug  

### json配置说明  
- 可以通过配置json来进行配置某个参数类型的参数值  
- 作用域（scope）：全局（global）、包（package）、类（class）、方法（method）
参数值配置优先级： 通过插件选填属性配置 < 全局（global）< 包（package）< 类（class）< 方法（method）  


## 0.1.1-SNAPSHOT
- testPackageName配置多个包，支持分号间换行，空格 
- testPackageName可以配置单个实现类。例如：<testPackageName>wiki.primo.generator.primogeneratormocktestdemo.service.impl.UserServiceImpl.java </testPackageName> 注意，配置单个类一定要以.java结尾。
- 外部依赖只需要引入primo-generator-mock-test-jar即可。  

## 0.1.0-SNAPSHOT
- 解决参数数量相同，重载方法的mock报错，进行注释代码 
- 解决mock方法参数名称改变问题  
- 内存中加载内加载器中类，不再需要手动在插件中依赖需要的类，也就是配置第三方依赖不再需要了 
- 不需要网络便可以运行，原来是通过网络流下载文件，本版本已经修改为jar包读取 
- 增加测试类目录可配置 

## 0.0.1
### 新增/优化功能  
1. 支持包下所有类中公共非静态方法生成测试方法   
2. 支持配置mock的包，将mock掉包下类的所有方法  
3. 支持基础类型和包装类型自动赋值  
4. 增加枚举参数的支持 
5. 可进行配置需要跳过的参数类型，直接配置包名，会进行跳过包内所有类的构造（用于跳过接口的构造，直接赋值为null） 
6. 优化全限定名称为简称，使用import导入包，名称存在重复的类，使用全限定名称  
7. 支持配置选择是否自动mock掉父类&自身非测试的方法 - 默认true  
8. 支持配置实体基础类型随机设置/使用默认值空值   
    a. 随机 String长度:10位数字与字母，使用JDK UUID进行生成，确保唯一  
    b. 随机 int:[0,1000)  
    c. 随机 byte:[0,1)  
    d. 随机 short:[0,127)   
    e. 随机 double:[0.00,10000.00)   
    f. 随机 float:[0.00f,10000.00f)   
    g. 随机 long:[0L,100000L)   
    g. 随机 char:数字/字母      
9. ~~每个测试类使用统一的before注解进行mock方法~~(考虑到后面每个分支的mock，如果同意进行mock的话，会导致分支无法全面覆盖)   
10. mock注解的类，使用了全限定名称，优化为简称，类进行导入，重复类简称，第一个类使用简称，后面的类使用全限定名称   
11. 不再支持配置其他包下的类进行mock，非测试类的所有方法均进行mock，测试类的私有方法也进行mock  
12. 已生成测试类，不再进行覆盖生成   
13. 支持第三方包类的加载和构造    
14. 对于一些没有setter方法的属性，也进行了set值；期望：对于没有setter的属性值，不进行设置  
15. 支持字符串、int、long、布尔类型随机值的范围设置     
16. 支持配置生成父类属性的set方法进行设置值，默认true，生成的set方法包含父类的属性(注意，父类如果不在当前项目中，需要在插件中引入包的依赖)     
17. 测试类新增方法支持追加生成mock测试方法  
18. 支持在不同包下的测试类同时进行生成   
19. 初始化下载配置文件不再需要，直接运行生成，自动检测是否下载，未下载先进行下载配置文件再生成  



## 其他功能排期  
- 配置文件可以配置不进行下载到本地，默认下载      
- 支持日志级别设置，方便使用者进行调试  
- 支持if-else生成多个mock分支方法   
- 方法的参数值支持json文件进行配置
- 检测代码实现类的方法覆盖率百分比查看以及通知到钉钉群
- 支持简单集合构造赋值  
- 支持配置静态方法mock，需要进行配置静态类的全限定名称(静态方法不建议进行mock)    
- 测试类中的私有方法进行mock，私有方法专门开方法进行生成mock测试，默认不支持，需要配置(私有方法不建议进行mock)   
- 同一个测试方法中存在Mock方法名称重复（参数个数不同）无法进行区分，仅仅对于第一个方法进行mock，且会存在重复mock代码生成；期望：支持同名方法的mock  
- 无法支持重名方法（参数个数相同，参数类型不同），会出现生成的mock方法引用不明确；期望：mock方法引用明确（通过匹配参数类型解决） 
- 不支持嵌套自定义参数的构造；期望：支持多级参数的构造  
- 不支持集合的构造；期望：支持集合的构造  
- mock方法返回值不支持自定义，统一是返回null；期望：支持mock返回值的自定义/生成值   
- 不支持Spring自定义事务管理器DataSourceTransactionManager的mock；期望：支持自定义事务的mock   
- 支持多级参数构造赋值   
- 支持重载方法的mock    
- 支持断言配置          
- 参数值的配置yml文件    
- 生成的测试方法可以配置是否编译报错，强制开发者主动进行单元测试

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
199个测试方法，一共覆盖309个被测试方法，使用primo-generator-mock-test生成后，仅仅只使用了3个多小时进行mock优化（仅仅优化了运行报错的方法，没有进行完善分支测试）。     

按照我以前的经验，如果全部由自己写，一切顺利的情况下，199个方法的mock测试，至少要多出几倍的时间。  
（此测试项目为使用mybatis-plus的项目，service层的实现类非常多的方法直接使用了父类方法，导致mock很麻烦，耽搁了一些时间，其他项目相对而言会节省更多时间）      

