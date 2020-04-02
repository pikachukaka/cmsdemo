package com.learning.cmsdemo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
@Autowired
UserService userService;
    private final Logger logger =
            LoggerFactory.getLogger(this.getClass());

    @Test
    public void selectAll() {
        logger.debug(userService.selectAll().toString());
    }
}