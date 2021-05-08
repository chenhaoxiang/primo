package wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class PrimoDubboSwaggerTestServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimoDubboSwaggerTestServerApplication.class, args);
    }

}
