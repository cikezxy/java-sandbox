package com.cikezxy.sandbox.spring.core.factorybean;

import com.cikezxy.sandbox.spring.core.MyType;
import com.cikezxy.sandbox.spring.core.MyTypeFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanAppConfig {

    @Bean
    public MyTypeFactoryBean factoryBean(){
        return new MyTypeFactoryBean();
    }

    @Bean(name = "first")
    public MyType first() throws Exception{
        return factoryBean().getObject();
    }

    @Bean(name = "second")
    public MyType second()throws Exception{
        return factoryBean().getObject();
    }
}
