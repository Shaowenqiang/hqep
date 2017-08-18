package com.hqep.action;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqep.model.ansoptiondetailModel;
import com.hqep.model.kindModel;
import com.hqep.model.optionModel;
import com.hqep.model.questionModel;
import com.hqep.model.sexModel;
import com.hqep.model.subkindModel;
import com.hqep.model.xlModel;
import com.hqep.model.zcModel;
import com.hqep.service.staticService;
@Controller
@RequestMapping("/static")
public class staticAction {
	 @Autowired
private staticService service;
	 @RequestMapping("/ry")
	 @ResponseBody
	 public Map getryzb()
	 {
		 List<kindModel> kindlist = service.getkind();
		 int nums  = service.getnums();
		 String categories  ="[";
		 String data  ="[";
		 DecimalFormat df = new DecimalFormat("#.00");
		 String bignum = "[";
		 String smallnum = "[";
		 for(int i=0;i<kindlist.size();i++)
		 {
			 if(i==0)
			 { categories = categories+"'"+ kindlist.get(i).getKind()+"'";
			   
			 if(i==kindlist.size()-1)
				 categories = categories +"]";
			 }
			 else
			 {
				 categories  =categories+","+"'"+ kindlist.get(i).getKind()+"'";
				 if(i==kindlist.size()-1)
					 categories = categories +"]";
			 }
			 String subtemp = "";
			 String subdata = "";
			 String subnum  = "";
			 List<subkindModel> subkindlist  =service.getsubkind(kindlist.get(i).getKind());
			 for(int j=0;j<subkindlist.size();j++)
			 {
				 if(j==0)
				 { subtemp = "'"+subkindlist.get(j).getSubkind()+"'";
				   subdata =""+ df.format(subkindlist.get(j).getNum()*100.00/(nums*1.00));
				   subnum = "['"+subkindlist.get(j).getSubkind()+"',"+subkindlist.get(j).getNum()+"]";
				 }
				 else
				 { subtemp = subtemp+","+"'"+subkindlist.get(j).getSubkind()+"'";
				   subdata = subdata + "," +df.format(subkindlist.get(j).getNum()*100.00/(nums*1.00));
				   subnum = subnum +","+"['"+subkindlist.get(j).getSubkind()+"',"+subkindlist.get(j).getNum()+"]";
				 }
			 }
			 String temp  = "{y:"+df.format((kindlist.get(i).getNum()*100.00)/(nums*1.00))+",color:colors["+i+"] ,drilldown:{name:'"+kindlist.get(i).getKind()+"',categories:["+subtemp+"],data:["+subdata+"],color:colors["+i+"]}}";
		     String bigtemp = "{name:'"+kindlist.get(i).getKind()+"',y:"+kindlist.get(i).getNum()+",drilldown:'"+this.getpy(kindlist.get(i).getKind())+"'}";
			 String smalltemp  ="{name:'"+kindlist.get(i).getKind()+"',id:'"+this.getpy(kindlist.get(i).getKind())+"',data:["+subnum+"]}";
			 
		     if(i==0)
		  {  data = data +temp;
		     bignum = bignum+bigtemp;
		     smallnum  = smallnum +smalltemp;
		    if(i==kindlist.size()-1)
		    { data = data +"]";
		      bignum = bignum + "]";
		      smallnum = smallnum + "]";
		    }
		  }
		  else
		  {
			  data = data+"," +temp;
			  bignum = bignum +"," +bigtemp;
			  smallnum = smallnum + "," +smalltemp;
			  if(i==kindlist.size()-1)
			  {    data = data +"]";
			       bignum = bignum + "]";
			       smallnum = smallnum +"]";
			  }
		  }  
		 }
		 Map map =new HashMap();
		 map.put("catagory", categories);
		 map.put("data", data);
		 map.put("bignum", bignum);
		 map.put("smallnum", smallnum);
		 return map;
		 
	 }
	 @RequestMapping("/subry")
	 @ResponseBody
	 public Map getsubryzb()
	 {
		 DecimalFormat df = new DecimalFormat("#.0000");
		 int nums  = service.getnums();
		 int bnums  =service.getSex("男");
		 int gnums  =nums -bnums;
		 //性别占比
		 String sexstr = "[['男',"+df.format(bnums*100.00/nums)+"],['女',"+(100-Float.parseFloat(df.format(bnums*100.00/nums)))+"]]";
		//学历占比
		 List<xlModel> xllist = service.getxlzb();
		 String xlstr = "[";
		 for(int i = 0;i<xllist.size();i++)
		 {
			 if(i==0)
			 xlstr = xlstr +"['"+xllist.get(i).getXl()+"',"+df.format(xllist.get(i).getNum()*100.00/nums)+"]";
			 else
				 xlstr = xlstr +",['"+xllist.get(i).getXl()+"',"+df.format(xllist.get(i).getNum()*100.00/nums)+"]"; 
		 }
		 xlstr = xlstr+"]";
		 //职称占比
		 List<zcModel> zclist = service.getzczb();
		 String zcstr = "[";
		 for(int j=0;j<zclist.size();j++)
		 {
			 if(j==0)
				 zcstr = zcstr +"['"+zclist.get(j).getZc()+"',"+df.format(zclist.get(j).getNum()*100.00/nums)+"]";
				 else
					 zcstr = zcstr +",['"+zclist.get(j).getZc()+"',"+df.format(zclist.get(j).getNum()*100.00/nums)+"]"; 
		 }
		 zcstr = zcstr+"]";
		 //年龄占比
		 int age25 = service.getage(26);
		 int age34 = service.getage(35);
		 int age44 = service.getage(45);
		 int age100 = service.getage(100);
		 int agea = age25;
		 int ageb = age34-age25;
		 int agec = age44-age34;
		 int aged = age100-age44;
		 String agestr = "[['25岁以下',"+df.format(agea*100.00/nums)+"],['25-34岁',"+df.format(ageb*100.00/nums)+"],['35-44岁',"+df.format(agec*100.00/nums)+"],['45岁以上',"+df.format(aged*100.00/nums)+"]]";
		 
		 
		 
		 
		 Map map =new HashMap();
		 map.put("sexdata", sexstr);
		 map.put("xldata", xlstr);
		 map.put("zcdata", zcstr);
		 map.put("sump", nums);
		 map.put("agedata", agestr);
		 return map;
	 }
	 @RequestMapping("/tj")
	 @ResponseBody
	 public Map getTotal()
	 {
		List<questionModel> list = service.getQuestion();
		String categorys = "[";
		String qnum  ="[";
		String astr = "";
		String bstr = "";
		String cstr ="";
		String dstr = "";
		for(int i=0;i<list.size();i++)
		{
			if(i==0)
			{categorys = categorys +"'"+list.get(i).getQuestion()+"'";
			int num = i+1;
			 qnum = qnum+"'"+num+"'";
			}
			else
				{categorys = categorys +",'"+list.get(i).getQuestion()+"'";
				int num  =i+1;
				qnum = qnum +",'"+num+"'";
				}
			int anum = service.getansnum(list.get(i).getId(), "A");
			List<optionModel> listdescript = service.getdescript(list.get(i).getId());
			int bnum = service.getansnum(list.get(i).getId(), "B");
			int cnum = service.getansnum(list.get(i).getId(), "C");
			int dnum = service.getansnum(list.get(i).getId(), "D");
			if(i==0)
			{ astr = "{y:"+anum+",description:'"+listdescript.get(0).getQcontent()+"'}";
			  bstr ="{y:"+bnum+",description:'"+listdescript.get(1).getQcontent()+"'}";
			  cstr ="{y:"+cnum+",description:'"+listdescript.get(2).getQcontent()+"'}";
			  dstr ="{y:"+dnum+",description:'"+listdescript.get(3).getQcontent()+"'}";
			
			}
			else
			{
				astr = astr +","+"{y:"+anum+",description:'"+listdescript.get(0).getQcontent()+"'}";
				bstr = bstr +","+"{y:"+bnum+",description:'"+listdescript.get(1).getQcontent()+"'}";
				cstr = cstr +","+"{y:"+cnum+",description:'"+listdescript.get(2).getQcontent()+"'}";
				dstr = dstr +","+"{y:"+dnum+",description:'"+listdescript.get(3).getQcontent()+"'}";
			}
			
		}
		astr = "["+astr+"]";
		bstr = "["+bstr+"]";
		cstr = "["+cstr+"]";
		dstr = "["+dstr+"]";
		categorys = categorys +"]";
		qnum = qnum +"]";
		Map map = new HashMap();
		map.put("categorys", categorys);
		map.put("qnum", qnum);
		map.put("astr", astr);
		map.put("bstr", bstr);
		map.put("cstr", cstr);
		map.put("dstr", dstr);
		 return map;
	 }
	 private String getpy(String str)
	 {
		 HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
	        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
	        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	      String returnstr  = PinyinHelper.toHanyuPinyinString(str, format, "");
	      return returnstr;
	 }
	 @RequestMapping("/kindstatic")
	 @ResponseBody
	 public Map getkindstatic(String qid){
		 qid =String.valueOf((Integer.valueOf(qid)+1));
		List<optionModel> list =service.getansoption(qid);
		String catestr = "[";
		String data="";
		
		for(int i=0;i<list.size();i++)
		{  if(i==0)
			catestr =catestr +"'"+list.get(i).getQcontent()+"'";
		else
			catestr = catestr +",'"+list.get(i).getQcontent()+"'";
		}
		
		catestr = catestr+"]";
		List<ansoptiondetailModel> anslist = service.getansoptiondetail(qid);
		 for(int j=0;j<anslist.size();j++)
		  {   if(j==0)
			  data = "{name:'"+anslist.get(j).getSubkind()+"',data:["+anslist.get(j).getAnum()+","+anslist.get(j).getBnum()+","+anslist.get(j).getCnum()+","+anslist.get(j).getDnum()+"],stack:'"+anslist.get(j).getKind()+"'}";
		  else
			  data = data+","+"{name:'"+anslist.get(j).getSubkind()+"',data:["+anslist.get(j).getAnum()+","+anslist.get(j).getBnum()+","+anslist.get(j).getCnum()+","+anslist.get(j).getDnum()+"],stack:'"+anslist.get(j).getKind()+"'}";
		  }
		data ="["+data+"]";
		Map map = new HashMap();
		 map.put("catedata", catestr);
		 map.put("detaildata", data);
		 return map;
	 }
	 @RequestMapping("/anskindstatic")
	 @ResponseBody
	 public Map getkindoptionstatic(String qid,String answerid){
		 qid =String.valueOf((Integer.valueOf(qid)+1));
		  List<kindModel> list  =service.getanskind(qid, answerid);
		  String str = "";
		  String substr = "";
		  for(int i=0;i<list.size();i++){
			  if(i==0)
				  str = "{name:'"+list.get(i).getKind()+"',y:"+list.get(i).getNum()+",drilldown:'"+this.getpy(list.get(i).getKind())+"' }";
			  else
				  str = str +","+"{name:'"+list.get(i).getKind()+"',y:"+list.get(i).getNum()+",drilldown:'"+this.getpy(list.get(i).getKind())+"' }";
		   List<subkindModel> sublist = service.getanssubkind(qid, answerid, list.get(i).getKind());
		   String data ="";
		   for(int j=0;j<sublist.size();j++)
		   {
			   if(j==0)
				   data = "['"+sublist.get(j).getSubkind()+"',"+sublist.get(j).getNum()+"]";
			   else
				   data = data +","+"['"+sublist.get(j).getSubkind()+"',"+sublist.get(j).getNum()+"]";
		   }
		   if(substr.equals(""))
			   substr = "{name:'"+list.get(i).getKind()+"',id:'"+this.getpy(list.get(i).getKind())+"',data:["+data+"]}";
		   else
			   substr = substr +","+"{name:'"+list.get(i).getKind()+"',id:'"+this.getpy(list.get(i).getKind())+"',data:["+data+"]}";
		  
		  }
		  
		 str = "["+str+"]";
		 substr = "["+substr+"]";
		 //性别分布
		 String sexstr="";
		 String sex = "";
		 List<sexModel> sexlist = service.getanssex(qid, answerid);
		 for(int i=0;i<sexlist.size();i++)
		 {
			 if(i==0)
			 { sexstr = "{name:'"+sexlist.get(i).getSex()+"',y:"+sexlist.get(i).getNums()+"}";
			   sex = "'"+sexlist.get(i).getSex()+"'";
			 }
			 else
			 { sexstr = sexstr +","+"{name:'"+sexlist.get(i).getSex()+"',y:"+sexlist.get(i).getNums()+"}";
			   sex  =sex+",'"+sexlist.get(i).getSex()+"'";
			 }
		 }
		 sexstr = "["+sexstr+"]";
		 sex = "["+sex+"]";
		 //学历分布
		 String xlstr = "";
		 String xl = "";
		 List<xlModel> xllist  =service.getansxl(qid, answerid);
		 for(int i=0;i<xllist.size();i++)
		 {
			 if(i==0)
				 {xlstr = "{name:'"+xllist.get(i).getXl()+"',y:"+xllist.get(i).getNum()+"}";
				 xl = "'"+xllist.get(i).getXl()+"'";
				 }
			 else
			 { xlstr = xlstr +","+"{name:'"+xllist.get(i).getXl()+"',y:"+xllist.get(i).getNum()+"}";
			   xl = xl +",'"+xllist.get(i).getXl()+"'";
			 }
		 }
		 xlstr = "["+xlstr+"]";
		 xl = "["+xl+"]";
		 //职称分布
		 String zcstr = "";
		 String zc = "";
		 List<zcModel> zclist  =service.getanszc(qid, answerid);
		 for(int i=0;i<zclist.size();i++)
		 {
			 if(i==0)
			 {zcstr = "{name:'"+zclist.get(i).getZc()+"',y:"+zclist.get(i).getNum()+"}";
			 zc = "'"+zclist.get(i).getZc()+"'";
			 }
		 else
		 { zcstr = zcstr +","+"{name:'"+zclist.get(i).getZc()+"',y:"+zclist.get(i).getNum()+"}";
		   zc = zc +",'"+zclist.get(i).getZc()+"'";
		 }
		 }
		 zcstr = "["+zcstr+"]";
		 zc = "["+zc+"]";
		 //年龄分布
		 int age25 = service.getansage(qid, answerid, "26");
		 int age34 = service.getansage(qid, answerid, "35");
		 int age44 = service.getansage(qid, answerid, "45");
		 int age100 = service.getansage(qid, answerid, "100");
		 int agea = age25;
		 int ageb = age34-age25;
		 int agec = age44-age34;
		 int aged = age100-age44;
		 String agestr = "[{name:'25岁以下',y:"+agea+"},{name:'26-34岁',y:"+ageb+"},{name:'35-44岁',y:"+agec+"},{name:'45岁以上',y:"+aged+"}]";
		 
		 
		 int nums  = service.getnums();
		 Map map = new HashMap();
		 map.put("kindata", str);
		 map.put("subkinddata", substr);
		 map.put("nums", nums);
		 map.put("sexstr", sexstr);
		 map.put("sex", sex);
		 map.put("xlstr", xlstr);
		 map.put("xl", xl);
		 map.put("zcstr", zcstr);
		 map.put("zc", zc);
		 map.put("agestr", agestr);
		 return map;
	 }
}
