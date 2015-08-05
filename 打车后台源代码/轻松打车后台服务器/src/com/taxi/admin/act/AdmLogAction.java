package com.taxi.admin.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.taxi.Db.UserManager;
import com.taxi.model.User;

public class AdmLogAction extends DispatchAction {
	
	public ActionForward webLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String Uid=request.getParameter("Uid");
		String Psw=request.getParameter("Psw");
		 User user=new User(Uid,Psw,"Admin");
	 	String str=UserManager.getAdmin().loginUser(user);
	 	User us=UserManager.getAdmin().findUserbyid(user.getID());
	 	
//	    if (str.equals("true"))
//	    {
//	    	 System.out.println("okok");
//	     request.getSession().setAttribute("Uid", Uid);                       //把账号写入会话
//	 	 return mapping.findForward("success");
//	    }
//	     else{
//	    	 System.out.println("error");
//	    	return mapping.findForward("index");
//	    }
	 	
	 	
	 	if(us.equals(user))
	 	{
	 		 System.out.println("okok");
	        request.getSession().setAttribute("Uid", Uid);                       //把账号写入会话
     	 	 return mapping.findForward("success");
	 	}
	     else{
                     	 System.out.println("error");
                    	return mapping.findForward("index");
         }
	}
	

}
