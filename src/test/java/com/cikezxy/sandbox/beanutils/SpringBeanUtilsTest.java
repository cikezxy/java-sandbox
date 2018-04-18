package com.cikezxy.sandbox.beanutils;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class SpringBeanUtilsTest {

    @Test
    public void copyPropertiesTest(){
        MessageBean bean = new MessageBean("123","title","content");
        MessageBeanAlias beanAlias = new MessageBeanAlias();
        BeanUtils.copyProperties(bean,beanAlias);
        System.out.println(beanAlias);
    }

    @Test
    public void copyFromMap(){

        MessageBean bean = new MessageBean("123", "title", "content");
        AnotherBean beanAlias = new AnotherBean();
        BeanUtils.copyProperties(bean, beanAlias);
        System.out.println(beanAlias);
    }
}
