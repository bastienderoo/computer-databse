package com.excilys.computerdatabase.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ResourceBundle;

/**
 * Created by excilys on 17/05/17.
 */
@EnableTransactionManagement
@EnableWebMvc
@Configuration
@ComponentScan({"com.excilys.computerdatabase"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/js/**", "/fonts/**","/images/**")
                .addResourceLocations("/css/", "/js/", "/fonts/","/images/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver
                = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /*  @Bean
      public DataSource dataSource() {
          ResourceBundle bundle = ResourceBundle.getBundle("config");
          String url = bundle.getString("database.url");
          String user = bundle.getString("database.user");
          String password = bundle.getString("database.password");
          MysqlDataSource dataSource = new MysqlDataSource();
          dataSource.setUrl(url);
          dataSource.setUser(user);
          dataSource.setPassword(password);
          return dataSource;
      }*/
    @Bean(name = "dataSource")
    public HikariDataSource dataSource() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(10);
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setJdbcUrl(bundle.getString("database.url"));
        ds.setUsername(bundle.getString("database.user"));
        ds.setPassword(bundle.getString("database.password"));
        return ds;


    }


}