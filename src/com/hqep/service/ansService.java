package com.hqep.service;

import org.springframework.stereotype.Service;

@Service
public interface ansService {
	public boolean  exist(String userid,String qid);
	public boolean addans(String qid,String userid,String type,String value);
	public boolean upans(String qid,String userid,String type,String value);
}
