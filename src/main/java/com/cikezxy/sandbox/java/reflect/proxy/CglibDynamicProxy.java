package com.cikezxy.sandbox.java.reflect.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibDynamicProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new HelloInterceptor(new HelloConcrete()));

        HelloConcrete hello = (HelloConcrete) enhancer.create();
        hello.sayHello();
    }

}

class HelloInterceptor implements MethodInterceptor {
    private Object target;

    public HelloInterceptor(Object obj) {
        this.target = obj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Invoking sayHello");
        // 方式1：在构造函数中传入被代理对象，调用method对象的invoke方法，此时第一个参数传被代理对象
        Object result = method.invoke(target, objects);

        // 方式2：cglib是通过继承方式实现代理，因此也可以调用methodProxy的invokeSuper方法，此时第一个参数是代理对象本身
        // Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}
