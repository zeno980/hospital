package com.zakary.test;

import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/mvc-dispatcher-servlet.xml")
public class BaseTest {
    protected static ClassPathXmlApplicationContext context = null;
}

