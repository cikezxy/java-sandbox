package com.cikezxy.sandbox.beanutils;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ApacheBeanUtilsTest {

    @Test
    public void testCopyProperties() throws  Exception{
        MessageBean bean = new MessageBean("123","title","content");
        MessageBeanAlias beanAlias = new MessageBeanAlias();
        BeanUtils.copyProperties(beanAlias,bean);
        System.out.println(beanAlias);
    }

    @Test
    public void testCopyFromMap() throws InvocationTargetException, IllegalAccessException {
        Map<String,String> map = new HashMap<>();
        map.put("id","123");
        map.put("title","title");
        map.put("content","123456");
        MessageBean bean = new MessageBean();
        BeanUtils.populate(bean,map);
        System.out.println(bean);
    }
}
