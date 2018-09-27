package com.cikezxy.sandbox.beanutils;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonBeanConvertTest {

    @Test
    public void beanToJson() throws JsonProcessingException {
        MessageBean messageBean = new MessageBean();
        messageBean.setId("123");
        messageBean.setTitle("message");
        messageBean.setContent("789");

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(messageBean);
        System.out.println(str);
        Assert.assertEquals("{\"id\":\"123\",\"title\":\"message\",\"content\":\"789\"}",str);
    }

    @Test
    public void jsonToBeanTest() throws IOException {
        String jsonString = "{\"id\":\"123\",\"title\":\"message\",\"content\":\"789\"}";
        ObjectMapper mapper = new ObjectMapper();
        MessageBean bean = mapper.readValue(jsonString, MessageBean.class);
        Assert.assertEquals("123",bean.getId());
        System.out.println(bean);
    }

    @Test
    public void beanToJsonWithAlias() throws JsonProcessingException {
        MessageBeanAlias messageBean = new MessageBeanAlias();
        messageBean.setId("123");
        messageBean.setTitle("message");
        messageBean.setContent("789");

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(messageBean);
        System.out.println(str);
        Assert.assertEquals(str,"{\"title\":\"message\",\"msg_id\":\"123\",\"body\":\"789\"}");
    }

    @Test
    public void jsonToBeanWithAliasTest() throws IOException {
        String jsonString = "{\"title\":\"message\",\"msg_id\":\"123\",\"body\":\"789\"}";
        ObjectMapper mapper = new ObjectMapper();
        MessageBeanAlias bean = mapper.readValue(jsonString, MessageBeanAlias.class);
        Assert.assertEquals("123",bean.getId());
        System.out.println(bean);
    }

    @Test
    public void jsonToMapTest() throws IOException {
        String jsonString = "{\"title\":\"message\",\"msg_id\":\"123\",\"body\":{\"title\":\"message\",\"msg_id\":\"123\",\"body\":\"789\"}}";
        ObjectMapper mapper = new ObjectMapper();
        Map bean = mapper.readValue(jsonString, Map.class);
        System.out.println(bean);
    }

    @Test
    public void convertBeanToMapTest() {
        MessageBeanAlias beanAlias = new MessageBeanAlias();
        beanAlias.setId("abc");
        beanAlias.setContent("111");
        beanAlias.setTitle("kkk");
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.convertValue(beanAlias, Map.class);
        System.out.println(map);
        Assert.assertEquals("abc", map.get("msg_id"));
    }
}
