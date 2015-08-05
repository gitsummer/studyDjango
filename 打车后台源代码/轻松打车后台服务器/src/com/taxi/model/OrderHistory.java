package com.taxi.model;

import java.util.Date;

public class OrderHistory {
	
	// 记录订单信息     
	private String Oid;
	private String  Type;
	private String Pid;
	private String Did;
	private Date    Odate;
	private String Start;
	private String Finish;
	private String   Status;
	private int    Tip;
	
	
	
	
	
	public  OrderHistory()
	{}
	
	
	public OrderHistory(String oid,String type, String pid, String did, Date odate,
			String start, String finish, String status, int tip) {
		Oid= oid;
		Type = type;
		Pid = pid;
		Did = did;
		Odate = odate;
		Start = start;
		Finish = finish;
		Status = status;
		Tip = tip;
	}


	public String getPid() {
		return Pid;
	}


	public void setPid(String pid) {
		Pid = pid;
	}


	public String getDid() {
		return Did;
	}


	public void setDid(String did) {
		Did = did;
	}


	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public String getOid() {
		return Oid;
	}
	public void setOid(String oid) {
		Oid = oid;
	}

	public Date getOdate() {
		return Odate;
	}
	public void setOdate(Date odate) {
		Odate = odate;
	}
	public String getStart() {
		return Start;
	}
	public void setStart(String start) {
		Start = start;
	}
	public String getFinish() {
		return Finish;
	}
	public void setFinish(String finish) {
		Finish = finish;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

	public int getTip() {
		return Tip;
	}
	public void setTip(int tip) {
		Tip = tip;
	}

	
	

}
