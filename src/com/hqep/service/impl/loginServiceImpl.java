package com.hqep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hqep.dao.loginDao;
import com.hqep.model.userModel;
import com.hqep.service.loginService;

@Repository
public class loginServiceImpl implements loginService{
   @Autowired
	private loginDao dao;
   public boolean  exist(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age)
   {
	   userModel model =new userModel();
	   model.setDept(dept);
	   model.setKind(kind);
	   model.setName(name);
	   model.setSubkind(subkind);
	   model.setAge(age);
	   model.setDuty(duty);
	   model.setSex(sex);
	   model.setXl(xl);
	   model.setZc(zc);
	   int num = dao.existuser(model);
	   boolean flag = false;
	   if(num>0)
		   flag = true;
	   return flag;
   }
   public boolean adduser(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age)
   {
	   boolean flag = false;
	   try {
		userModel model =new userModel();
		   model.setDept(dept);
		   model.setKind(kind);
		   model.setName(name);
		   model.setSubkind(subkind);
		   model.setAge(age);
		   model.setDuty(duty);
		   model.setSex(sex);
		   model.setXl(xl);
		   model.setZc(zc);
		   model.setIson("1");
		   dao.save(model);
		   flag=true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return flag;
   }
   public userModel selectuser(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age)
   {
	   userModel model =new userModel();
	   model.setDept(dept);
	   model.setKind(kind);
	   model.setName(name);
	   model.setSubkind(subkind);
	   model.setAge(age);
	   model.setDuty(duty);
	   model.setSex(sex);
	   model.setXl(xl);
	   model.setZc(zc);
	   model = dao.selectuser(model);
	   return model;
   }
   public void updateison(String id){
	   dao.updateison(id);
   }
   public void updateisoff(String id){
	   dao.updateisoff(id);
   }
}
