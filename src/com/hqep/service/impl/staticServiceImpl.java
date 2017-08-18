package com.hqep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hqep.dao.staticDao;
import com.hqep.model.ansoptiondetailModel;
import com.hqep.model.answerModel;
import com.hqep.model.kindModel;
import com.hqep.model.optionModel;
import com.hqep.model.questionModel;
import com.hqep.model.sexModel;
import com.hqep.model.subkindModel;
import com.hqep.model.xlModel;
import com.hqep.model.zcModel;
import com.hqep.service.staticService;

@Repository
public class staticServiceImpl implements staticService{
   @Autowired
	private staticDao dao;
 public List<kindModel> getkind(){
	 List<kindModel> list = dao.kindzb();
	 return list;
 }
 public List<subkindModel> getsubkind(String kind){
	 List<subkindModel> list= dao.subkindzb(kind);
	 return list;
 }
 public int getnums(){
	 int nums = dao.getnums();
	 return nums;
 }
 public List<questionModel> getQuestion(){
	 List<questionModel> list = dao.getquestion();
	 return list;
 }
 public int getansnum(String qid,String answerid)
 {
	 answerModel model  = new answerModel();
	 model.setQid(qid);
	 model.setAnswerid(answerid);
	 int num = dao.getansnum(model);
	 return num;
 }
 public List<optionModel> getdescript(String qid)
 {
	 optionModel model = new optionModel();
	 model.setQid(qid);
	 List<optionModel> list = dao.getdescript(model);
	 return list;
 }
 public int getSex(String sex){
	 int sexnums = dao.getsex(sex);
	 return sexnums;
 }
 public List<xlModel> getxlzb()
 {
	 List<xlModel> list = dao.xlzb();
	 return list;
 }
 public List<zcModel> getzczb()
 {
	 List<zcModel> list = dao.zczb();
	 return list;
 }
 public int getage(int age){
	 int num  =dao.getage(age);
	 return num;
 }
 public List<optionModel> getansoption(String qid){
	 List<optionModel> list  =dao.getansoption(qid);
	 return list;
 }
 public List<ansoptiondetailModel> getansoptiondetail(String qid){
	 List<ansoptiondetailModel> list = dao.getansoptiondetail(qid);
	 return list;
 }
 public List<kindModel> getanskind(String qid,String answerid){
	  answerModel model  =new answerModel();
	  model.setAnswerid(answerid);
	  model.setQid(qid);
	 List<kindModel> list = dao.getanskind(model);
	 return list;
 }
 public List<subkindModel> getanssubkind(String qid,String answerid,String kind){
	 answerModel model  =new answerModel();
	  model.setAnswerid(answerid);
	  model.setQid(qid);
	  model.setAnswercontent(kind);
	  List<subkindModel> list = dao.getanssubkind(model);
	  return list;
 }
 public List<sexModel> getanssex(String qid,String answerid){
	 answerModel model  =new answerModel();
	  model.setAnswerid(answerid);
	  model.setQid(qid);
	  List<sexModel> list = dao.getanssex(model);
	  return list;
 }
 public List<xlModel> getansxl(String qid,String answerid){
	 answerModel model  =new answerModel();
	  model.setAnswerid(answerid);
	  model.setQid(qid);
	  List<xlModel> list = dao.getansxl(model);
	  return list;
 }
 public List<zcModel> getanszc(String qid,String answerid){
	 answerModel model  =new answerModel();
	  model.setAnswerid(answerid);
	  model.setQid(qid);
	  List<zcModel> list = dao.getanszc(model);
	  return list;
 }
 public int getansage(String qid,String answerid,String age){
	 answerModel model  =new answerModel();
	  model.setAnswerid(answerid);
	  model.setQid(qid);
	  model.setUserid(age);
	  int nums = dao.getansage(model);
	  return nums;
 }
}