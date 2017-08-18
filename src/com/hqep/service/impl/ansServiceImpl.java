package com.hqep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hqep.dao.ansDao;
import com.hqep.model.inansModel;
import com.hqep.model.userModel;
import com.hqep.service.ansService;
import com.hqep.service.loginService;

@Repository
public class ansServiceImpl implements ansService{
   @Autowired
	private ansDao dao;
   public boolean  exist(String userid,String qid)
   {
	   inansModel model =new inansModel();
	   model.setUserid(userid);
	   model.setQid(qid);
	  int count =  dao.existans(model);
	   boolean flag = false;
	   if(count>0)
		   flag = true;
	   return flag;
   }
   public boolean addans(String qid,String userid,String type,String value)
   {
	   boolean flag = false;
	   try {
		inansModel model =new inansModel();
		   model.setQid(qid);
		   model.setUserid(userid);
		   model.setType(type);
		   if("1".equals(type))
			   model.setAnswerid(value);
		   if("2".equals(type))
			   model.setAnswercontent(value);
		   dao.save(model);
		   flag=true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return flag;
   }
   public boolean upans(String qid,String userid,String type,String value)
   {
	   boolean flag = false;
	   try {
		inansModel model =new inansModel();
		   model.setQid(qid);
		   model.setUserid(userid);
		   if("1".equals(type))
			   model.setAnswerid(value);
		   if("2".equals(type))
			   model.setAnswercontent(value);
		   dao.update(model);
		   flag=true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return flag;
   }
}
