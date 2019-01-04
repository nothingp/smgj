package com.szkingdom;

import com.szkingdom.entity.Users;
import com.szkingdom.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lange
 * @date 2018-12-16 21:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class SpringRedisTest {

    @Autowired
    private RedisService<String, Users> redisService;

    @Test
    public void set(){
        Users user = new Users();
        user.setUserId(1L);
        user.setUsername("tom");
        user.setPassword("123");
        redisService.setValue("user_tom", user);
    }

}
