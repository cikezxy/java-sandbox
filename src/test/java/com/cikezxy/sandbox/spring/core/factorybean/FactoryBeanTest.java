package com.cikezxy.sandbox.spring.core.factorybean;

import com.cikezxy.sandbox.spring.core.MyType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = FactoryBeanAppConfig.class)
@ContextConfiguration(locations = "classpath:FactoryBeanAppConfiguration.xml")
public class FactoryBeanTest {

    @Resource(name = "first")
    private MyType first;
    @Resource(name = "second")
    private MyType second;

    @Test
    public void test() {
        Assert.assertNotNull(first);
        Assert.assertNotNull(second);
        Assert.assertFalse(first == second);
    }
}
