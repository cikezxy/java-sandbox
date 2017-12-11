package com.cikezxy.sandbox.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class HelloAgent {

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("cikezxy:name=HelloWorld");
        mBeanServer.registerMBean(new Hello(), helloName);
        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
