package com.jing.forum;

import com.jing.forum.dao.AlphaDao;
import com.jing.forum.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
class ForumApplicationTests {


	@Autowired
	@Qualifier("alphaHibernate")
	private AlphaDao al2;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat sdf;


	@Test
	public void testApplicationContext() {


	}

	@Test
	public void testBeanConfig() {
		System.out.println(sdf.format(new Date()));
	}

}
