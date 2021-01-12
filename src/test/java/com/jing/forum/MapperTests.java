package com.jing.forum;


import com.jing.forum.dao.UserMapper;
import com.jing.forum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(150);
        System.out.println(user);

        User liubei = userMapper.selectByName("liubei");
        System.out.println(liubei);

        User user1 = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user1);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("23241");
        user.setSalt("abc");
        user.setEmail("fsdlk@qq.com");
        user.setHeaderUrl("tt");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());

    }

    @Test
    public void updateUser() {
        int i = userMapper.updateStatus(150, 1);
        System.out.println(i);

        int i1 = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(i1);

        int hello = userMapper.updatePassword(150, "hello");
        System.out.println(hello);


    }
}
