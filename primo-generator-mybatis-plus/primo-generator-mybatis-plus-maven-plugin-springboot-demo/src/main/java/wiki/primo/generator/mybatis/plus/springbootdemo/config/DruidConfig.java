package wiki.primo.generator.mybatis.plus.springbootdemo.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;

/**
 * druid 页面监控配置.
 * 该方式不需要添加注解：@ServletComponentScan
 *
 * @author chenhx
 * @since 2021-11-30 14:49:18
 */
@Configuration
public class DruidConfig {

    /**
     * 注册一个StatViewServlet
     * druid数据源状态监控.
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean<Servlet> servletRegistrationBean() {
        // org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),
                "/druid/*");
        // 添加初始化参数：initParams
        // 白名单 value为ip地址，多个ip用逗号隔开
        servletRegistrationBean.addInitParameter("allow", "");
        // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
        // permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据. 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        servletRegistrationBean.setEnabled(true);
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * druid过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setEnabled(true);

        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要监听，忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


}
