/*
* primo-generator-mock-test
*/
package wiki.primo.generator.primogeneratormocktestdemo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.junit.MockitoJUnitRunner;
    import wiki.primo.generator.primogeneratormocktestdemo.query.UserTemplateQueryBo;
    import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
    import wiki.primo.generator.primogeneratormocktestdemo.entity.UserTemplate;
    import wiki.primo.generator.primogeneratormocktestdemo.query.UserTemplateQueryBo.QueryBoExt;
    import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

/**
* UserTemplateServiceImplTest
* @author 
* @date 2020-12-29 15:23:11
*/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class UserTemplateServiceImplTest {
@InjectMocks
private UserTemplateServiceImpl userTemplateServiceImpl;

    @Test
    public void pageTest() {
    //组装测试方法的参数
                UserTemplateQueryBo query = new UserTemplateQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setExt("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setUserId(0);
                query.setTemplateCode(0);
                query.setDomain("");
                query.setPassword("");


                int pageNo = 0;


                int pageSize = 0;


    //mock方法

    //调用方法
    userTemplateServiceImpl.page(query,pageNo,pageSize);
    }


    @Test
    public void getOneTest() {
    //组装测试方法的参数
                UserTemplateQueryBo query = new UserTemplateQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setExt("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setUserId(0);
                query.setTemplateCode(0);
                query.setDomain("");
                query.setPassword("");


    //mock方法

    //调用方法
    userTemplateServiceImpl.getOne(query);
    }


    @Test
    public void pageTest1() {
    //组装测试方法的参数
                QueryWrapper queryWrapper = new QueryWrapper();



                int pageNo = 0;


                int pageSize = 0;


    //mock方法

    //调用方法
    userTemplateServiceImpl.page(queryWrapper,pageNo,pageSize);
    }


    @Test
    public void updateTest() {
    //组装测试方法的参数
                UserTemplate entity = new UserTemplate();

                entity.setId(0);
                entity.setExt("");
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entity.setPastTime(new Date());
                entity.setUserId(0);
                entity.setTemplateCode(0);
                entity.setDomain("");
                entity.setPassword("");

                UserTemplateQueryBo query = new UserTemplateQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setExt("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setUserId(0);
                query.setTemplateCode(0);
                query.setDomain("");
                query.setPassword("");


    //mock方法

    //调用方法
    userTemplateServiceImpl.update(entity,query);
    }


    @Test
    public void updateByColumnTest() {
    //组装测试方法的参数
                UserTemplate entity = new UserTemplate();

                entity.setId(0);
                entity.setExt("");
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entity.setPastTime(new Date());
                entity.setUserId(0);
                entity.setTemplateCode(0);
                entity.setDomain("");
                entity.setPassword("");


                String column = "";


                Object value = new Object();


    //mock方法

    //调用方法
    userTemplateServiceImpl.updateByColumn(entity,column,value);
    }


    @Test
    public void listTest() {
    //组装测试方法的参数
                UserTemplateQueryBo query = new UserTemplateQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setExt("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setUserId(0);
                query.setTemplateCode(0);
                query.setDomain("");
                query.setPassword("");


    //mock方法

    //调用方法
    userTemplateServiceImpl.list(query);
    }



}
