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
    public void ${method.methodName}Test() <#list method.javaExceptionsDTOList as exceptions><#if exceptions_index==0>throws ${exceptions.type}<#else>,${exceptions.type}</#if></#list>{
            //组装参数
        <#list method.javaParameterDTOList as parameter>
            ${parameter.type} ${parameter.name} = <#if parameter.value??>${parameter.value}<#else >new ${parameter.type}()</#if>;
        </#list>
            //调用方法
            ${modelNameLowerCamel}.${method.methodName}(<#list method.javaParameterDTOList as parameter><#if parameter_index==0>${parameter.name}<#else>,${parameter.name}</#if></#list>);
    }


</#list>

}
