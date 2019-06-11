package ${javaClassDTO.packageName};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

/**
* ${modelNameUpperCamel}Test
* @author ${author!''}
* @date ${date!''}
*/
@RunWith(MockitoJUnitRunner.class)
@PowerMockIgnore("javax.management.*")
public class ${modelNameUpperCamel}Test {
@InjectMocks
private ${modelNameUpperCamel} ${modelNameLowerCamel};
<#--遍历mock的类-->
<#list javaClassDTO.javaMockClassInfoDTOList as mockClass>
    @Mock
    private ${mockClass.type} ${mockClass.name};
</#list>

<#--遍历方法-->
<#list javaClassDTO.javaMethodDTOList as method>
    @Test
    public void ${method.methodTestName}Test() <#list method.javaExceptionsDTOList as exceptions><#if exceptions_index==0>throws ${exceptions.type}<#else>,${exceptions.type}</#if></#list>{
    //组装参数
    <#list method.javaParameterDTOList as parameter>
    <#--判断是否是自定义参数-->
        <#if parameter.customType>
        <#--获取内部参数-->
            ${parameter.type} ${parameter.name} = <#if parameter.value??>${parameter.value}<#else >new ${parameter.type}()</#if>;
            <#list javaClassDTO.javaParameterDTOMap['${parameter.keyName}'] as cParameter>
            <#--设置内部值-->
                ${parameter.name}.set${cParameter.upName}(<#if cParameter.value??>${cParameter.value}<#else >new ${cParameter.type}()</#if>);
            </#list>
        <#else >
            ${parameter.type} ${parameter.name} = <#if parameter.value??>${parameter.value}<#else >new ${parameter.type}()</#if>;
        </#if>
    </#list>

    //mock方法
    <#list method.javaMockMethodInfoMap?keys as key>

        <#list method.javaMockMethodInfoMap[key] as methodInfo>
            PowerMockito.when(${methodInfo.className}.${methodInfo.name}(<#if methodInfo.parameterName??><#list methodInfo.parameterName as parameterN><#if parameterN_index==0>any()<#else>,any()</#if></#list></#if>)).thenReturn(null);
        </#list>

    </#list>

    //调用方法
    ${modelNameLowerCamel}.${method.methodName}(<#list method.javaParameterDTOList as parameter><#if parameter_index==0>${parameter.name}<#else>,${parameter.name}</#if></#list>);
    }


</#list>

}
