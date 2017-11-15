package com.cikezxy.sandbox.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MybatisGeneratorTest {

    public static void main(String[] args) throws Exception {
        String classPath = MybatisGeneratorTest.class.getResource("/").getPath();
        System.out.println(classPath);
        String filePath = classPath + "mybatis_auto.xml";
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(filePath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        myBatisGenerator.generate(null);
        System.out.println("生成完成");
    }
}
