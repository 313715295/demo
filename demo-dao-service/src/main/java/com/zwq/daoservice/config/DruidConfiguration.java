package com.zwq.daoservice.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * created by zwq on 2018/5/6
 */
@Configuration
public class DruidConfiguration {


    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单
        servletRegistrationBean.addInitParameter("allow","192.168.1.72,127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry,
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //用户名
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        //密码
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //禁用HTML页面上的“Reset All”功能 是否能够重置数据
        servletRegistrationBean.addInitParameter("restEnable","false");

        return servletRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加忽略资源
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
