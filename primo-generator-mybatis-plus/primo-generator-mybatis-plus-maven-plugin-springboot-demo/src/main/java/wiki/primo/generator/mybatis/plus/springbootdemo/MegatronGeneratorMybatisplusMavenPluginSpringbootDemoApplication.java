package wiki.primo.generator.mybatis.plus.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//第二步：扫描mapper
@MapperScan("wiki.primo.generator.mybatis.plus.springbootdemo.mapper")
public class MegatronGeneratorMybatisplusMavenPluginSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MegatronGeneratorMybatisplusMavenPluginSpringbootDemoApplication.class, args);
    }

}
