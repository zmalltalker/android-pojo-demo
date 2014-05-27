package com.zmalltalker.tests;

import com.zmalltalker.spike.Person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    @Test
    public void testName(){
        Person p = new Person();
        assertEquals("Marius", p.getName());
    }
}
