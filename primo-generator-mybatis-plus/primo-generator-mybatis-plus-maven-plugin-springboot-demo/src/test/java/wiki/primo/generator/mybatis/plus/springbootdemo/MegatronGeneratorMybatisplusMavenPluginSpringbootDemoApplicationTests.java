package wiki.primo.generator.mybatis.plus.springbootdemo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UserQueryBo;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.User;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MegatronGeneratorMybatisplusMavenPluginSpringbootDemoApplicationTests {

    @Autowired
    private IUserService userService;
    @Test
    void contextLoads() {

    }

    /**
     * 查询
     */
    @Test
    public void selectUser(){
        User u = userService.getById(1);
        System.out.println(u);
    }
    /**
     * 查询
     */
    @Test
    public void page(){
        UserQueryBo userQueryBo = new UserQueryBo();
        userQueryBo.setId(1);

        IPage u = userService.page(userQueryBo,1,1);
        System.out.println(u);
    }

}
