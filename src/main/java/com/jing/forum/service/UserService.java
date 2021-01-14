package com.jing.forum.service;

import com.jing.forum.dao.UserMapper;
import com.jing.forum.entity.User;
import com.jing.forum.util.ForumUtil;
import com.jing.forum.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${forum.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> map= new HashMap<>();
        if(user == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if(StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMessage", "账号不能为空!");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMessage", "密码不能为空!");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())) {
            map.put("emailMessage", "邮箱不能为空!");
            return map;
        }

        User user1 = userMapper.selectByName(user.getUsername());
        if(user1 != null) {
            map.put("usernameMessage", "该账号已存在");
            return map;
        }

        User user2 = userMapper.selectByEmail(user.getEmail());
        if(user2 != null) {
            map.put("emailMessage", "该邮箱已存在");
            return map;
        }

        user.setSalt(ForumUtil.generateUUID().substring(0,5));
        user.setPassword(ForumUtil.md5(user.getPassword()+user.getSalt()));

        return map;
    }

}
