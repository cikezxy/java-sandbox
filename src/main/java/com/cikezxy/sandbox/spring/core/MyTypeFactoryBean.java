package com.cikezxy.sandbox.spring.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyTypeFactoryBean implements FactoryBean<MyType> {
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public MyType getObject() throws Exception {
        return new MyType(idGenerator.incrementAndGet());
    }

    @Override
    public Class<?> getObjectType() {
        return MyType.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
