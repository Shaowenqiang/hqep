package com.hqep.dao;

import org.springframework.stereotype.Repository;

import com.hqep.model.inansModel;

@Repository
public interface ansDao {
public void save(inansModel model);
public int existans(inansModel model);
public void update(inansModel model);
}
