package com.excilys.computerdatabase.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by excilys on 17/05/17.
 */


@EnableWebMvc
@Configuration
@ComponentScan({"com.excilys.computerdatabase"})
public class SpringRootConfig {

}
