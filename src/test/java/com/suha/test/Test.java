package com.suha.test;

import com.suha.controller.web.WebIndexController;
import com.suha.pojo.NewsInfo;
import com.suha.service.NewsInfoService;
import com.suha.service.RedisService;
import com.suha.util.Page;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-*.xml")
public class Test {

    @Autowired
    private RedisService rs;

    @org.junit.Test
    public void test(){
        rs.delAll();
    }
}
