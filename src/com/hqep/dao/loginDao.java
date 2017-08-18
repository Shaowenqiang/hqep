package com.hqep.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hqep.model.userModel;

@Repository
public interface loginDao {
     public int existuser(userModel model);
     public int save(userModel model);
     public userModel selectuser(userModel model);
     public int updateison(@Param(value="id") String id);
     public int updateisoff(@Param(value="id") String id);
}
