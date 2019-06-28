package ${javaClassDTO.packageName};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.junit.MockitoJUnitRunner;
<#list javaClassDTO.javaImplementsDTOList as implements>
    import ${implements.type};
</#list>

import static org.mockito.ArgumentMatchers.any;

/**
* ${javaClassDTO.modelNameUpperCamel}Test
* @author ${javaClassDTO.author!''}
* @date ${javaClassDTO.date!''}
*/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class ${javaClassDTO.modelNameUpperCamel}Test {
@InjectMocks
private ${javaClassDTO.modelNameUpperCamel} ${javaClassDTO.modelNameLowerCamel};
<#--遍历mock的类-->
<#list javaClassDTO.javaMockClassInfoDTOList as mockClass>
    @Mock
    private ${mockClass.type} ${mockClass.name};
</#list>

<#--遍历方法-->
<#list javaClassDTO.javaMethodDTOList as method>
    @Test
    public void ${method.methodTestName}() <#list method.javaExceptionsDTOList as exceptions><#if exceptions_index==0>throws ${exceptions.type}<#else>,${exceptions.type}</#if></#list>{
    //组装测试方法的参数
    <#list method.javaParameterDTOList as parameter>
    <#--判断是否是自定义参数-->
        <#if parameter.customType>
        <#--获取内部参数-->
            ${parameter.type} ${parameter.name} = <#if parameter.value??>${parameter.value}<#else >new ${parameter.type}()</#if>;

            <#list parameter.javaParameterDTOList as cParameter>
            <#--设置内部值-->
                ${parameter.name}.set${cParameter.upName}(<#if cParameter.value??>${cParameter.value}<#else >new ${cParameter.type}()</#if>);
            </#list>

        <#else >
            ${parameter.type} ${parameter.name} = <#if parameter.value??>${parameter.value}<#else >new ${parameter.type}()</#if>;
        </#if>
    </#list>

    //mock方法
    <#if method.javaMockMethodInfoDTOList??>
        <#--定义变量-->
        <#assign mockThis = 0>
        <#list method.javaMockMethodInfoDTOList as mockMethInfo>
            <#if mockMethInfo.fieldName==javaClassDTO.modelNameLowerCamel>
                // mock当前测试类方法
                <#if mockThis==0>
                    ${javaClassDTO.modelNameLowerCamel} = PowerMockito.spy(${javaClassDTO.modelNameLowerCamel});
                    <#assign mockThis = 1>
                </#if>
            </#if>
            <#if mockMethInfo.returnType=='void'>
                //返回void
                PowerMockito.doNothing().when(${mockMethInfo.fieldName}).${mockMethInfo.name}(
                <#if mockMethInfo.javaParameterDTOList??>
                    <#list mockMethInfo.javaParameterDTOList as mockParameter>
                        <#if mockParameter_index==0>any()<#else>,any()</#if>
                    </#list>
                </#if>);
            <#else >
                PowerMockito.doReturn(null).when(${mockMethInfo.fieldName}).${mockMethInfo.name}(
                <#if mockMethInfo.javaParameterDTOList??>
                    <#list mockMethInfo.javaParameterDTOList as mockParameter>
                        <#if mockParameter_index==0>any()<#else>,any()</#if>
                    </#list>
                </#if>
                );
            </#if>
        </#list>
    </#if>

    //调用方法
    ${javaClassDTO.modelNameLowerCamel}.${method.methodName}(<#list method.javaParameterDTOList as parameter><#if parameter_index==0>${parameter.name}<#else>,${parameter.name}</#if></#list>);
    }


</#list>

}
