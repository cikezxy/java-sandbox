package com.cikezxy.sandbox;

public class Test {

    public static void main(String[] args) {

        String str = "31801070500869|1|https://sign.facebank.cn/hicashChannel/SIGNFPXL37222683364955176960197001015b44275eee380.pdf";
        String[] content = str.split("\\|");
        System.out.println(content);
    }
}
