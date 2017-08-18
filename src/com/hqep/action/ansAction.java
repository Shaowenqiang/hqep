package com.hqep.action;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqep.model.ansModel;
import com.hqep.service.ansService;

@Controller
@RequestMapping("/ans")
public class ansAction {
	 @Autowired
		private ansService ansservice;
	 @RequestMapping("/in")
	public String ansin(String userid,ansModel model)
	{
		for(int i=1;i<=50;i++)
		{
			String value  = this.getValue(model, "Q"+i);
			if(value!=null)
			{   String type = "";
				if(value.equals("A")||value.equals("B")||value.equals("C")||value.equals("D"))
					{type = "1";
					}
				else
				{
				type = "2";
				}
				boolean flag =  ansservice.exist(userid, i+"");
				if(flag)
				{
					ansservice.upans(i+"", userid, type, value);
				}
				else
					ansservice.addans(i+"", userid, type, value);
					
			}
		}
		return "redirect:/view/success.html";
	}
	  private <T> String getValue(T t, String fieldname) {
	        String realmethod = fieldname;
	        Object value = null;
	        String realvalue = null;
	                    try {
	                        Method mothod = t.getClass().getMethod("get" + realmethod, new Class[] {});
	                        value = mothod.invoke(t, new Object[] {});

	                        if (value != null)
	                            realvalue = value.toString();
	                    } catch (Exception e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	        return realvalue;
	    }
}
