package com.hqep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hqep.model.ansoptiondetailModel;
import com.hqep.model.kindModel;
import com.hqep.model.optionModel;
import com.hqep.model.questionModel;
import com.hqep.model.sexModel;
import com.hqep.model.subkindModel;
import com.hqep.model.xlModel;
import com.hqep.model.zcModel;

@Service
public interface staticService {
	public List<kindModel> getkind();
	public List<subkindModel> getsubkind(String kind);
	public int getnums();
	public List<questionModel> getQuestion();
	public int getansnum(String qid,String answerid);
	public List<optionModel> getdescript(String qid);
	public int getSex(String sex);
	 public List<xlModel> getxlzb();
	 public List<zcModel> getzczb();
	 public int getage(int age);
	 public List<optionModel> getansoption(String qid);
	 public List<ansoptiondetailModel> getansoptiondetail(String qid);
	 public List<kindModel> getanskind(String qid,String answerid);
	 public List<subkindModel> getanssubkind(String qid,String answerid,String kind);
	 public List<sexModel> getanssex(String qid,String answerid);
	 public List<xlModel> getansxl(String qid,String answerid);
	 public List<zcModel> getanszc(String qid,String answerid);
	 public int getansage(String qid,String answerid,String age);
}
