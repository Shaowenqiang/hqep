package com.hqep.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hqep.model.ansoptiondetailModel;
import com.hqep.model.answerModel;
import com.hqep.model.kindModel;
import com.hqep.model.optionModel;
import com.hqep.model.questionModel;
import com.hqep.model.sexModel;
import com.hqep.model.subkindModel;
import com.hqep.model.xlModel;
import com.hqep.model.zcModel;

@Repository
public interface staticDao {
public List<kindModel> kindzb();
public List<subkindModel> subkindzb(@Param(value="kind") String kind);
public int getnums();
public List<questionModel> getquestion();
public int getansnum(answerModel model);
public List<optionModel> getdescript(optionModel model);
public int getsex(@Param(value="sex") String sex);
public List<xlModel> xlzb();
public List<zcModel> zczb();
public int getage(@Param(value="age") int age);
public List<optionModel> getansoption(@Param(value="qid") String qid);
public List<ansoptiondetailModel> getansoptiondetail(@Param(value="qid") String qid);
public List<kindModel> getanskind(answerModel model);
public List<subkindModel> getanssubkind(answerModel model);
public List<sexModel> getanssex(answerModel model);
public List<xlModel> getansxl(answerModel model);
public List<zcModel> getanszc(answerModel model);
public int getansage(answerModel model);
}
