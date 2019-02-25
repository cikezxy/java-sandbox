package com.cikezxy.sandbox.java.java8;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

import com.cikezxy.sandbox.java.java8.optional.Car;
import com.cikezxy.sandbox.java.java8.optional.Insurance;
import com.cikezxy.sandbox.java.java8.optional.Person;

public class OptionalTest {

    @Test(expected = NullPointerException.class)
    public void testCreateOptionalWithNullUsingOf() {
        Optional.of(null);
    }

    @Test
    public void testCreateOptionalWithNullUsingOfNullable() {
        Optional<String> str = Optional.ofNullable(null);
        String value = str.orElse("Unknown");
        assertEquals("Unknown", value);

        str = Optional.ofNullable("abc");
        value = str.map(String::toString).orElse("Unknown");
        assertEquals("abc", value);
    }

    @Test
    public void testCreateOptionalUsingEmpty() {
        Optional<String> str = Optional.empty();
        String value = str.map(String::toString).orElse("Unknown");
        assertEquals("Unknown", value);
    }

    @Test
    public void testOptionalFlatMap() {
        Optional<Person> p = Optional.of(new Person(Optional.of(new Car(Optional.empty()))));
        String name = p.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
        assertEquals("Unknown", name);
    }


}
