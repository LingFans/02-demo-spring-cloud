package com.song.springcloud;

import org.junit.Test;

import java.time.ZonedDateTime;

public class App {
    
    @Test
    public void test(){
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime);
    }
}
