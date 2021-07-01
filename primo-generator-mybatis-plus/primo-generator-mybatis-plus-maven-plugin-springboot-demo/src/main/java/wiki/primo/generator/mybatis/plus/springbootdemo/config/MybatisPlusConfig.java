package wiki.primo.generator.mybatis.plus.springbootdemo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 分页插件
 *
 * @author chenhx
 * @since 2021-07-01
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //分页限制，防止炸库，限制放开
        paginationInterceptor.setLimit(-1L);
        return paginationInterceptor;
    }
}
