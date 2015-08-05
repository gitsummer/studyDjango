package com.taxi.act;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.taxi.Db.InfoManage;
import com.taxi.Db.OrderManager;
import com.taxi.model.DrivInfo;
import com.taxi.model.PassInfo;
import com.taxi.take.PassNeed;

// �����ȡ �鿴��Ϣ����
public class PerInfoAction extends DispatchAction {

	
//��׿�� ������Ϣ�鿴 
	public ActionForward findInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		      System.out.println("ok99999999999 ����鿴��Ϣ");
		     String json=PerJson.getPer().readJSON(request);
		     JSONObject jsonObj = JSONObject.fromString(json);
		     String Type=jsonObj.getString("Type");
		     String Id=jsonObj.getString("Id");
		     JSONObject jsonout = new net.sf.json.JSONObject();
		     response.setContentType("text/html;charset=UTF-8");
		    // Gson gson = new Gson(); 
		    // String outjson="";
		     
		     if(Type.equals("Passager"))
		     {          
		    	    System.out.println("ok99999999999 ����Pass");
		    	    PassInfo pass=(PassInfo)InfoManage.getAdmin().findUserInfo(Id, "Pass");
			         //outjson = gson.toJson(pass);  
		    	    jsonout.put("Age", pass.getAge());
		    	    jsonout.put("Sex", pass.getSex());
		    	    jsonout.put("Score", pass.getScore());
		    	    System.out.println(pass.getAge()+"  "+pass.getSex()+"    "+pass.getScore());
		     }
		     else
		     {
		    	    DrivInfo dri=(DrivInfo)InfoManage.getAdmin().findUserInfo(Id, "Driv");
	              //outjson = gson.toJson(dri); 
		      } 
		     PerJson.getPer().jsonToString(jsonout.toString(), response);
		     
		    
	     return null;
				  
	}
	
	
	
	//������ϢParameter
	public ActionForward updateInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 String json=PerJson.getPer().readJSON(request);
          Gson gson = new Gson();  
		if(request.getParameter("type").equals("Pass"))   
		{
			   PassInfo us=gson.fromJson(json, PassInfo.class);
		       InfoManage.getAdmin().modifyInfo(us, "Pass");
			
		}
		else
		{
			 DrivInfo us=gson.fromJson(json, DrivInfo.class);
			  InfoManage.getAdmin().modifyInfo(us, "Driv");
		}

		return null;
	}
	

	
	
	
	
	
	// ����鿴��ʷ��¼   ����List
	public ActionForward findOlder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 System.out.println("ok8888888888888888888888 ������ʷ��¼");
		 String json=PerJson.getPer().readJSON(request);
		 JSONObject jsonin = JSONObject.fromString(json);
	     String id=jsonin.getString("Id");
	     String type=jsonin.getString("Type");
		 List info=OrderManager.getOrderAdmin().findAllHistory(id, type);
		 Gson gson=new Gson();
		 String s = gson.toJson(info);  
		 System.out.println(s);
		 
		 response.setCharacterEncoding("utf-8");
		 
		  PerJson.getPer().jsonToString(s, response);
		
	
	
	
	    return null;
	}
}
