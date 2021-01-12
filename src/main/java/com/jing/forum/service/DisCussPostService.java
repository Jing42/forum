package com.jing.forum.service;

import com.jing.forum.dao.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisCussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;
}
