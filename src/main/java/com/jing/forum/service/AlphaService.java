package com.jing.forum.service;

import com.jing.forum.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("singleton")
public class AlphaService {

    private AlphaDao alphaDao;

    @Autowired
    public void setAlphaDao(AlphaDao alphaDao) {
        this.alphaDao = alphaDao;
    }

    @PostConstruct
    public void init() {
        System.out.println("initialize AlphaService");
    }

    public AlphaService() {
        System.out.println("construct AlphaService");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy AlphaService");
    }


    public String find() {
        return alphaDao.select();
    }

}
