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


//���ฺ��׿��login ��register
public class LogRegAction extends DispatchAction {



//login ��֤ ��������״̬ true false  AccountError
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		   String json=PerJson.getPer().readJSON(request);
	       Gson gson = new Gson();  
	       User us=gson.fromJson(json, User.class);
           User user=UserManager.getAdmin().findUserbyid(us.getID());
           JSONObject jsonObj = new net.sf.json.JSONObject();
            System.out.println("ok ���ܵ���User"+json);
            
            if(user==null)
            {  jsonObj.put("status","nocount");
                System.out.println("δ�ҵ����˺�"+json);
            }
          else if(us.equals(user))
          { jsonObj.put("status","success");
             System.out.println("��½�ɹ�"+json);
          }
           else
           {
        	   jsonObj.put("status","error");
            System.out.println("�������"+json);
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
		    	 
		    	  System.out.println("ע��  ���˺��Ѵ��ڣ�������"+json);
		    	  jsonObj.put("status","exist");
		     }
		     
		     else
		     {   
		    	 
		    	  int  age =Integer.parseInt(jsonin.getString("Age"));
		    	  String Sex=jsonin.getString("Sex");
		    	  PassInfo pn=new PassInfo(id,"��",Sex,"��δ��д",age,0,us,"��δ��д");
		    	  
		    	  if(UserManager.getAdmin().registerUser(us)&&InfoManage.getAdmin().addUserInfo(pn, "Pass"))
		    	      {
		    		  jsonObj.put("status","success");
		    		  System.out.println("ע��ɹ�"+json);
		    	       }
		    	  
		    		  else
		    		  {
		    			  jsonObj.put("status","error");
		    			  System.out.println("�������쳣"+json);
		    		  }
		       }
		    			

			      PerJson.getPer().jsonToString(jsonObj.toString(), response);
		            return null;
	}
	

}
