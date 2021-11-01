package com.dataart.javaschool.spring_mvc_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJdbcRepositories
public class Application {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        displayAllBeans(applicationContext);
    }

    private static void displayAllBeans(ApplicationContext context) {
        String[] allBeanNames = context.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

}
