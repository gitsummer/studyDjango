package com.taxi.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.taxi.Db.InfoManage;
import com.taxi.Db.UserManager;
import com.taxi.model.PassInfo;
import com.taxi.model.User;


//此类负责安卓端login 和register
public class LogRegAction extends DispatchAction {



//login 验证 返回三种状态 true false  AccountError
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		   String json=PerJson.getPer().readJSON(request);
	       Gson gson = new Gson();  
	       User us=gson.fromJson(json, User.class);
           User user=UserManager.getAdmin().findUserbyid(us.getID());
           JSONObject jsonObj = new net.sf.json.JSONObject();
            System.out.println("ok 接受到了User"+json);
            
            if(user==null)
            {  jsonObj.put("status","nocount");
                System.out.println("未找到此账号"+json);
            }
          else if(us.equals(user))
          { jsonObj.put("status","success");
             System.out.println("登陆成功"+json);
          }
           else
           {
        	   jsonObj.put("status","error");
            System.out.println("密码错误"+json);
           }
          
          
              PerJson.getPer().jsonToString(jsonObj.toString(), response);
                return null;
	      
	    
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		     String json=PerJson.getPer().readJSON(request);
		   

		     
		     JSONObject jsonin = JSONObject.fromString(json);
		     String id=jsonin.getString("Id");
		     String pass=jsonin.getString("Password");
		     String type=jsonin.getString("Type");
		     
		     User us=new User(id,pass,type);
		     
		     User user=UserManager.getAdmin().findUserbyid(us.getID());
		     JSONObject jsonObj = new net.sf.json.JSONObject();
		     
		     if(user!=null)
		     {
		    	 
		    	  System.out.println("注册  此账号已存在：。。。"+json);
		    	  jsonObj.put("status","exist");
		     }
		     
		     else
		     {   
		    	 
		    	  int  age =Integer.parseInt(jsonin.getString("Age"));
		    	  String Sex=jsonin.getString("Sex");
		    	  PassInfo pn=new PassInfo(id,"无",Sex,"暂未填写",age,0,us,"暂未填写");
		    	  
		    	  if(UserManager.getAdmin().registerUser(us)&&InfoManage.getAdmin().addUserInfo(pn, "Pass"))
		    	      {
		    		  jsonObj.put("status","success");
		    		  System.out.println("注册成功"+json);
		    	       }
		    	  
		    		  else
		    		  {
		    			  jsonObj.put("status","error");
		    			  System.out.println("服务器异常"+json);
		    		  }
		       }
		    			

			      PerJson.getPer().jsonToString(jsonObj.toString(), response);
		            return null;
	}
	

}
