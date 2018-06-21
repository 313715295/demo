package com.zwq.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.Serializable;

/**
 * created by zwq on 2018/6/20
 */
@Configuration
public class MybatisConfig  {


    private static final String URL = "jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=UTF-8&serverTimezone=GMT&&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "910810";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String MAPPER_LOCATION = "/mapper/*.xml";
    private static final String TYPE_ALIASES_PACKAGE = "com.zwq.pojo";

    @Bean(name = "myDruidDataSource")
    public DataSource myDruidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(URL);
        druidDataSource.setUsername(USER_NAME);
        druidDataSource.setPassword(PASSWORD);
        druidDataSource.setDriverClassName(DRIVER_CLASS_NAME);
        //初始化大小，最小，最大
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(20);
        //配置获取连接等待超时的时间
        druidDataSource.setMaxWait(60000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        //验证连接
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        //打开PSCache，并且指定每个连接上PSCache的大小
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

//        因为没有配置监控，下面的就没有配置了
//        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
//        spring.datasource.filters=stat,wall,log4j
//        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
//        spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
//        # 合并多个DruidDataSource的监控数据
//        #spring.datasource.useGlobalDataSourceStat=true
        return druidDataSource;
    }
//    下面两个bean是配置druid监控所需的 单独建一个DruidConfiguration
//    @Bean
//    public ServletRegistrationBean druidStatViewServlet() {
//        ServletRegistrationBean servletRegistrationBean =
//                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        //添加初始化参数：initParams
//        //白名单
//        servletRegistrationBean.addInitParameter("allow","192.168.1.72,127.0.0.1");
//        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry,
//        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
//        //用户名
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        //密码
//        servletRegistrationBean.addInitParameter("loginPassword","123456");
//        //禁用HTML页面上的“Reset All”功能 是否能够重置数据
//        servletRegistrationBean.addInitParameter("restEnable","false");
//        return servletRegistrationBean;
//
//    }
//
//    @Bean
//    public FilterRegistrationBean druidStatFilter() {
//        FilterRegistrationBean filterRegistrationBean =
//                new FilterRegistrationBean(new WebStatFilter());
//
//        //添加过滤规则
//        filterRegistrationBean.addUrlPatterns("/*");
//        //添加忽略资源
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("myDruidDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        return sqlSessionFactoryBean.getObject();
    }
}
