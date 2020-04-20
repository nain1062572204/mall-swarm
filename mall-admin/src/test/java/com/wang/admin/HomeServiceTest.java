package com.wang.admin;

import com.wang.mall.admin.AdminApplication;
import com.wang.mall.admin.service.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 王念
 * @create 2020-04-20 20:42
 */
@SpringBootTest(classes = {AdminApplication.class})
@RunWith(SpringRunner.class)
public class HomeServiceTest {
    @Autowired
    private HomeService homeService;
    @Test
    public void testContent(){
        homeService.content();
    }
}
