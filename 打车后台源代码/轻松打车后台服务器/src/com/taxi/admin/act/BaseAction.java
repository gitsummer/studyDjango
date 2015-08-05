package com.taxi.admin.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


// admin��½����һ��session �� ��ÿ����ҳ���󶼻���������࣬ ���ȼ���Ƿ��½
public class BaseAction extends DispatchAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("Uid") == null) {
			return mapping.findForward("index");
		}
		return super.execute(mapping, form, request, response);
	}


	
	
}
