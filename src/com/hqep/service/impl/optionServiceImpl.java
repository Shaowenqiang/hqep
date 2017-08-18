package com.hqep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hqep.dao.optionDao;
import com.hqep.model.optionModel;
import com.hqep.service.optionService;
@Repository
public class optionServiceImpl implements optionService {
	@Autowired
	private optionDao dao;
	public String insertoption() {
		int a  =5;
	    for(int i=2;i<=10;i++)
	    {
	    	for(int j=1;j<=4;j++)
	    	{
	    		String px  ="";
	    		optionModel model = new optionModel();
	    		model.setId(a+"");
	    		if(j==1)
	    		px="A";	
	    		if(j==2)
		    		px="B";	
	    		if(j==3)
		    		px="C";	
	    		if(j==4)
		    		px="D";	
	    		model.setPx(px);
	    		model.setQcontent(i+":"+j);
	    		model.setQid(i+"");
	    		dao.save(model);
	    		a++;
	    	}
	    		
	    }
		return "";
	}
}
