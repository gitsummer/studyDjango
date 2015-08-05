package com.taxi.admin.act;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taxi.Db.InfoManage;
import com.taxi.Db.OrderManager;
import com.taxi.Db.UserManager;


import com.taxi.model.DrivInfo;
import com.taxi.model.PassInfo;
import com.taxi.model.User;

public class webInfoAction extends BaseAction {
	
	//查看信息
	public ActionForward findAllUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		  String type=request.getParameter("type");
		   System.out.println("7777777777777777777777777777777777777777777"+type);
		  // List info=WebInfo.getAdmin().findallPassInfo(type);
		  List info=InfoManage.getAdmin().findallUserInfo(type);
		   System.out.println("okokok999999999999");
		   request.setAttribute("userlist", info);
            
		if(type.equals("PassInfo"))
		{
			 System.out.println("7777777777777777777777777777777777777777777");
		      return mapping.findForward("Plist_success");
		
		}
		else
			return mapping.findForward("Dlist_success");
	}
	
	//删除info
	public ActionForward deleteInfo(ActionMapping mapping, ActionForm form,

			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  String type=request.getParameter("type");
		  System.out.println("5555555555555555555555555555555555"+type);
		  String[] selectFlag1=request.getParameterValues("selectFlag");
		  
		  
		  
		   if(selectFlag1==null)
		   System.out.println("55555555555555555222222222222222222");
		    else 
			  System.out.println("6622222222222222222");
		  
		  
		  
		  System.out.println(selectFlag1.toString());
		   ActionForward af =null;
		   
		  if(type.equals("Pass"))
		  {
			         InfoManage.getAdmin().deleteUserInfo(selectFlag1, "Pass");
		
	                 af  = new ActionForward("first.do?command=findAllUser&type=PassInfo", true);
		  }
		  else
		  {
			  InfoManage.getAdmin().deleteUserInfo(selectFlag1, "Driv");
				
              af  = new ActionForward("first.do?command=findAllUser&type=DrivInfo", true);
			  
		  }
		   return  af;
	       
	
	}
	
	// 增加info user      暂时未设置司机信息添加
	public ActionForward addInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception	{
		     request.setCharacterEncoding("GB18030");
		     System.out.println("66777777777777777777");
		     String userid=request.getParameter("userid");
		     String type=request.getParameter("ty");
		     System.out.println("68888888888888888888"+type);
             User us=new User(userid,request.getParameter("userpsw"),type);
             UserManager.getAdmin().registerUser(us);
             System.out.println("669999999999999999999999999");
             ActionForward af =null;
            if(type.equals("Dri"))
            {    
            	String name=request.getParameter("username");
            	String sex=request.getParameter("usersex");
            	String emai=request.getParameter("useremail");
                int  age =Integer.parseInt(request.getParameter("userage"));
                int score=0;
                String plate=request.getParameter("userplate");
                String license=request.getParameter("userlicense");
                String model= request.getParameter("usermodel");
                System.out.println("?????????????????????model "+model+"           "+sex);
             	 DrivInfo dri=new DrivInfo(userid,name,sex,emai,age,score,us,plate,license,model);
             	 InfoManage.getAdmin().addUserInfo(dri, "Driv");
             	   af = new ActionForward("first.do?command=findAllUser&type=DrivInfo", true);
            }
            
            
            else
            {
            	PassInfo pa=new PassInfo();
                pa.setID(request.getParameter("userid"));
                int  age =Integer.parseInt(request.getParameter("userage"));
                pa.setAge(age);
                pa.setScore(0);
                System.out.println("6psassspspspspspsppspspspsppspspspsp");
                pa.setEmail(request.getParameter("useremail"));
                pa.setAddress(request.getParameter("useraddress"));
                pa.setName(request.getParameter("username"));
                pa.setSex(request.getParameter("usersex"));
                pa.setTuser(us);
                InfoManage.getAdmin().addUserInfo(pa, "Pass");
                af = new ActionForward("first.do?command=findAllUser&type=PassInfo", true);
             
            }
                     System.out.println("6cccccccccccccccccccccccccccccccccccccccp");
                    
                     return af;
    }
	
	//根据Id 找信息 到 修改界面
	public ActionForward loadinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
			{
		     String Id=request.getParameter("id");
		     String type=request.getParameter("type");
		     System.out.println("1111111111111111111111111             "+type+"              "+Id);
		     User us=UserManager.getAdmin().findUserbyid(Id);  
		      request.setAttribute("user", us);
		   // User us=WebUser.getAdminUser().findUserbyId(Id);
		    if(type.equals("Pass"))
		    {
		    	PassInfo ps= (PassInfo) InfoManage.getAdmin().findUserInfo(Id, "Pass");
		    	request.setAttribute("passinfo", ps);
				return mapping.findForward("modify_detail");
		    }
		    else
		    {
		    	DrivInfo dri= (DrivInfo) InfoManage.getAdmin().findUserInfo(Id, "Driv");
		    	request.setAttribute("drivinfo", dri);
		    	return mapping.findForward("Dmodify_detail");
		    	
		    }
			  
			
		    
			}
	  
	
	//修改信息
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
			{
		   String ty=request.getParameter("userty");
		   User us=new User(request.getParameter("userid"),request.getParameter("userpsw"),ty);
		   UserManager.getAdmin().modify(us);
		   ActionForward  af =null;
		   if(ty.equals("Passager"))
		   {
		  PassInfo pa=new PassInfo();
          pa.setID(request.getParameter("userid"));
          pa.setAge(22);
          pa.setScore(0);
          System.out.println("667666666666666666666666666");
          pa.setEmail(request.getParameter("useremail"));
          pa.setAddress(request.getParameter("useraddress"));
          pa.setName(request.getParameter("username"));
          pa.setSex(request.getParameter("usersex"));
          pa.setTuser(us);
         // WebInfo.getAdmin().modify(pa);
          InfoManage.getAdmin().modifyInfo(pa, "Pass");
               af = new ActionForward("first.do?command=findAllUser&type=PassInfo", true);
          return af;
		   }
		   
		   else
		   {    
			   System.out.println("999999999999999999999996"+"       siji");
			   String  id=request.getParameter("userid");
			   String  name=request.getParameter("username");
			   int  age =Integer.parseInt(request.getParameter("userage"));
			   String sex=request.getParameter("usersex");
			   String  license=request.getParameter("userlicense");
			   String plate= request.getParameter("userplate");
			   String model=request.getParameter("usermodel");
			   String email=request.getParameter("useremail");
			   DrivInfo text=new DrivInfo(id,name,sex,email,age,50,us,plate,license,model);
			   InfoManage.getAdmin().modifyInfo(text, "Driv");
			   af = new ActionForward("first.do?command=findAllUser&type=DrivInfo", true);  
			    return af;
			   
		   }
          
          
			}
	
	
	public ActionForward listOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception	{
		      System.out.println("9999999999999999999999999999   listorder");
		     List info=OrderManager.getOrderAdmin().findHistory();
		     request.setAttribute("order", info); 
		     return mapping.findForward("Olist_success");
	}
}