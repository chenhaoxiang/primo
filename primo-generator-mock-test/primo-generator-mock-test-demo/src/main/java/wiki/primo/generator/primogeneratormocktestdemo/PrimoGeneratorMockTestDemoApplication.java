package wiki.primo.generator.primogeneratormocktestdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("wiki.primo.generator.primogeneratormocktestdemo.mapper")
public class PrimoGeneratorMockTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimoGeneratorMockTestDemoApplication.class, args);
    }
}
