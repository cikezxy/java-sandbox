package com.cikezxy.sandbox.java.regex;

import java.util.regex.Pattern;

import org.junit.Test;

public class ChineseCharTest {

    private static final String TEST_STRING = "您好！您申请的精英分期已通过审核！请登录嗨钱网APP完成签约。点击（http://ajlfja/fafa）立即领取";

    private static final Pattern HICASH_NET_PATTERN = Pattern.compile("\u55e8\u94b1\u7f51");

    private static final Pattern HICASH_PATTERN = Pattern.compile("\u55e8\u94b1");

    @Test
    public void testHicash() {
        String result1 = TEST_STRING.replaceAll("\u55e8\u94b1\u7f51", "千百块");
        System.out.println(result1);

        String result2 = TEST_STRING.replaceAll("\u55e8\u94b1", "千百块");
        System.out.println(result2);
    }

    @Test
    public void testRemoveHttp() {

        String[] strings = TEST_STRING.split("[！。？；]");
        String result = TEST_STRING;
        for (String str : strings) {
            if (str.contains("http")) {
                result = result.replaceAll(str, "");
            }
        }
        System.out.println(result);
    }

    @Test
    public void test() {
        System.out.println(null + "dddd");
    }
}
