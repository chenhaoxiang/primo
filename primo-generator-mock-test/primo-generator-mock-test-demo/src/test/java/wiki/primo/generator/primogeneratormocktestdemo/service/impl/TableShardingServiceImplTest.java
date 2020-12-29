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
    import wiki.primo.generator.primogeneratormocktestdemo.query.TableShardingQueryBo;
    import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
    import wiki.primo.generator.primogeneratormocktestdemo.entity.TableSharding;
    import wiki.primo.generator.primogeneratormocktestdemo.query.TableShardingQueryBo.QueryBoExt;
    import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

/**
* TableShardingServiceImplTest
* @author 
* @date 2020-12-29 15:23:11
*/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class TableShardingServiceImplTest {
@InjectMocks
private TableShardingServiceImpl tableShardingServiceImpl;

    @Test
    public void pageTest() {
    //组装测试方法的参数
                TableShardingQueryBo query = new TableShardingQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setAge(0);
                query.setCreateTime(new Date());
                query.setToken("");


                int pageNo = 0;


                int pageSize = 0;


    //mock方法

    //调用方法
    tableShardingServiceImpl.page(query,pageNo,pageSize);
    }


    @Test
    public void getOneTest() {
    //组装测试方法的参数
                TableShardingQueryBo query = new TableShardingQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setAge(0);
                query.setCreateTime(new Date());
                query.setToken("");


    //mock方法

    //调用方法
    tableShardingServiceImpl.getOne(query);
    }


    @Test
    public void pageTest1() {
    //组装测试方法的参数
                QueryWrapper queryWrapper = new QueryWrapper();



                int pageNo = 0;


                int pageSize = 0;


    //mock方法

    //调用方法
    tableShardingServiceImpl.page(queryWrapper,pageNo,pageSize);
    }


    @Test
    public void updateTest() {
    //组装测试方法的参数
                TableSharding entity = new TableSharding();

                entity.setId(0);
                entity.setName("");
                entity.setAge(0);
                entity.setCreateTime(new Date());
                entity.setToken("");

                TableShardingQueryBo query = new TableShardingQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setAge(0);
                query.setCreateTime(new Date());
                query.setToken("");


    //mock方法

    //调用方法
    tableShardingServiceImpl.update(entity,query);
    }


    @Test
    public void updateByColumnTest() {
    //组装测试方法的参数
                TableSharding entity = new TableSharding();

                entity.setId(0);
                entity.setName("");
                entity.setAge(0);
                entity.setCreateTime(new Date());
                entity.setToken("");


                String column = "";


                Object value = new Object();


    //mock方法

    //调用方法
    tableShardingServiceImpl.updateByColumn(entity,column,value);
    }


    @Test
    public void listTest() {
    //组装测试方法的参数
                TableShardingQueryBo query = new TableShardingQueryBo();

                query.setQueryBoExt(new QueryBoExt());
                query.setId(0);
                query.setName("");
                query.setAge(0);
                query.setCreateTime(new Date());
                query.setToken("");


    //mock方法

    //调用方法
    tableShardingServiceImpl.list(query);
    }



}
