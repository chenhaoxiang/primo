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
    import wiki.primo.generator.primogeneratormocktestdemo.entity.User;
    import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
    import wiki.primo.generator.primogeneratormocktestdemo.query.UserQueryBo.QueryBoExt;
    import wiki.primo.generator.primogeneratormocktestdemo.query.UserQueryBo;
    import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

/**
* UserServiceImplTest
* @author 
* @date 2020-12-29 15:23:11
*/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class UserServiceImplTest {
@InjectMocks
private UserServiceImpl userServiceImpl;

    @Test
    public void pageTest() {
    //组装测试方法的参数
                UserQueryBo query = new UserQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setPassword("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setSex(0);
                query.setPhone("");
                query.setEmail("");
                query.setDescribe("");
                query.setRole(0);


                int pageNo = 0;


                int pageSize = 0;


    //mock方法

    //调用方法
    userServiceImpl.page(query,pageNo,pageSize);
    }


    @Test
    public void getOneTest() {
    //组装测试方法的参数
                UserQueryBo query = new UserQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setPassword("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setSex(0);
                query.setPhone("");
                query.setEmail("");
                query.setDescribe("");
                query.setRole(0);


    //mock方法

    //调用方法
    userServiceImpl.getOne(query);
    }


    @Test
    public void pageTest1() {
    //组装测试方法的参数
                QueryWrapper queryWrapper = new QueryWrapper();



                int pageNo = 0;


                int pageSize = 0;


    //mock方法

    //调用方法
    userServiceImpl.page(queryWrapper,pageNo,pageSize);
    }


    @Test
    public void updateTest() {
    //组装测试方法的参数
                User entity = new User();

                entity.setId(0);
                entity.setName("");
                entity.setPassword("");
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entity.setPastTime(new Date());
                entity.setSex(0);
                entity.setPhone("");
                entity.setEmail("");
                entity.setDescribe("");
                entity.setRole(0);

                UserQueryBo query = new UserQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setPassword("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setSex(0);
                query.setPhone("");
                query.setEmail("");
                query.setDescribe("");
                query.setRole(0);


    //mock方法

    //调用方法
    userServiceImpl.update(entity,query);
    }


    @Test
    public void updateByColumnTest() {
    //组装测试方法的参数
                User entity = new User();

                entity.setId(0);
                entity.setName("");
                entity.setPassword("");
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entity.setPastTime(new Date());
                entity.setSex(0);
                entity.setPhone("");
                entity.setEmail("");
                entity.setDescribe("");
                entity.setRole(0);


                String column = "";


                Object value = new Object();


    //mock方法

    //调用方法
    userServiceImpl.updateByColumn(entity,column,value);
    }


    @Test
    public void listTest() {
    //组装测试方法的参数
                UserQueryBo query = new UserQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setPassword("");
                query.setCreateTime(new Date());
                query.setUpdateTime(new Date());
                query.setPastTime(new Date());
                query.setSex(0);
                query.setPhone("");
                query.setEmail("");
                query.setDescribe("");
                query.setRole(0);


    //mock方法

    //调用方法
    userServiceImpl.list(query);
    }



}
