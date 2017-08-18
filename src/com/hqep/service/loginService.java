package com.hqep.service;

import org.springframework.stereotype.Service;

import com.hqep.model.userModel;

@Service
public interface loginService {
	public boolean  exist(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age);
	public boolean adduser(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age);
	public userModel selectuser(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age);
	 public void updateison(String id);
	 public void updateisoff(String id);
}
