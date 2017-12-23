package com.cikezxy.sandbox.java.reflect;

import org.junit.Test;
import static org.junit.Assert.*;

public class InstanceofAssignableFromTest {

    private Integer integer = new Integer(0);

    @Test
    public void testInstanceof(){
        System.out.println("Integer instanceof Number:"+(integer instanceof Number));
        System.out.println("Integer instanceof Integer:"+(integer instanceof Integer));
        System.out.println("Integer instanceof Comparable:"+(integer instanceof Comparable));

        System.out.println("Number isInstance Integer:"+Number.class.isInstance(integer));
        System.out.println("Integer isInstance Integer:"+Integer.class.isInstance(integer));
        System.out.println("Comparable isInstance Integer:"+Comparable.class.isInstance(integer));

        System.out.println("Number isAssignableFrom Integer:"+Number.class.isAssignableFrom(Integer.class));
        System.out.println("Integer isAssignableFrom Integer:"+Integer.class.isAssignableFrom(Integer.class));
        System.out.println("Comparable isAssignableFrom Integer:"+Comparable.class.isAssignableFrom(Integer.class));

    }
}
