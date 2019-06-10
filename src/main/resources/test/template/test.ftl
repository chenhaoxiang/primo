package ${javaClassDTO.packageName};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
<#--遍历导入的包-->
<#--<#list javaClassDTO.javaImplementsDTOList as implement>-->
<#--import ${implement.type};-->
<#--</#list>-->

/**
 * ${modelNameUpperCamel}Test
 * @author ${author!''}
 * @date ${date!''}
 */
public class ${modelNameUpperCamel}Test {
    @Autowired
    private ${modelNameUpperCamel} ${modelNameLowerCamel};

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
            //调用方法
            ${modelNameLowerCamel}.${method.methodName}(<#list method.javaParameterDTOList as parameter><#if parameter_index==0>${parameter.name}<#else>,${parameter.name}</#if></#list>);
    }


</#list>

}
