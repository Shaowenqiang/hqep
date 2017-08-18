package com.hqep.util;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hqep.model.userModel;
import com.hqep.service.loginService;

public class SessionListener implements HttpSessionListener{
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		userModel user =(userModel)arg0.getSession().getAttribute("userlog");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/hqep/conf/spring/applicationContext.xml");

        loginService lservice = ctx.getBean(loginService.class);
		lservice.updateisoff(user.getId());
		ctx.close();
		
	}
	

}
