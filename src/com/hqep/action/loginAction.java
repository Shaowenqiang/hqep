package com.hqep.action;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.hqep.model.userModel;
import com.hqep.service.loginService;

@Controller
@RequestMapping("/login")
public class loginAction {
    @Autowired
	private loginService lservice;
	 @RequestMapping("/in")
	public String login(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age,HttpServletRequest request)
	{
		 String returnstr = "redirect:/view/login.html";
		 if(!"".equals(kind)&&!"".equals(subkind)&&!"".equals(dept)&&!"".equals(name)&&!"".equals(sex)&&!"".equals(xl)&&!"".equals(zc)&&!"".equals(duty)&&age!=0)
		 {
			 
	
		boolean flag =  this.exist(kind, subkind, dept, name,sex,xl,zc,duty,age);
		userModel model = null;
	     if(flag ==false)
	     {
	    	boolean successflag  = lservice.adduser(kind, subkind, dept, name, sex,xl,zc,duty,age);
	    	if(successflag)
	    	{
	    	   model =	lservice.selectuser(kind, subkind, dept, name,sex,xl,zc,duty,age);
	    	}
	     }
	     else
	     {
	    	  model = 	lservice.selectuser(kind, subkind, dept, name,sex,xl,zc,duty,age);
	    	  lservice.updateison(model.getId());
	     }
		request.getSession().setAttribute("userlog", model);
		returnstr = "investigation";
		 }
		return returnstr;
	}
	 private boolean exist(String kind,String subkind,String dept,String name,String sex,String xl,String zc,String duty,int age)
	 {
		 boolean flag =lservice.exist(kind, subkind, dept, name,sex,xl,zc,duty,age);
		 return flag;
	 }
	 @RequestMapping("/adminin")
	 public String adminlogin(String adminname,String adminpwd,HttpServletRequest request)
	 {
		 String md = Hashing.md5().newHasher().putString("baihuipwd", Charsets.UTF_8).hash().toString();
		 System.out.println(md);
		 String returnstr="redirect:/view/login.html";
		if(adminname.equals("baihui")&&md.equals(adminpwd))
		{
			request.getSession().setAttribute("admin", md);
			returnstr = "statics";
		}
		
		 return returnstr;
	 }
}
