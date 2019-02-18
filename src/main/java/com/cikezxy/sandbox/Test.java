package com.cikezxy.sandbox;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class Test {

    public static void main(String[] args) {

        System.out.println(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
    }
}
