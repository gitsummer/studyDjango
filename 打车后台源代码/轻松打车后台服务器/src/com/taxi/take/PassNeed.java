package com.taxi.take;

import java.util.Date;
//�����¼�ȴ��ӵ���������Ϣ ,��ŵ�List
public class PassNeed implements Cloneable
{
	  private  String Uid;
	  private  String Start;
	  private  String Finish;
	  private  String   Odate;
	  private  int      Tip;
	  private int  Score;
	  private int  Num;   //��¼���͸�����
	  
	  

  public PassNeed()
  {}
   
	public PassNeed(String uid, String start, String finish, String odate, int tip,
		int score, int num) {
	Uid = uid;
	Start = start;
	Finish = finish;
	Odate = odate;
	Tip = tip;
	Score = score;
	Num = num;
}

	public int getNum() {
		return Num;
	}


	public void setNum(int num) {
		Num = num;
	}


	@Override
	public String toString() {
		return "PassNeed [Uid=" + Uid + ", Start=" + Start + ", Finish="
				+ Finish + ", Tip=" + Tip + ", Score=" + Score + ", Odate="
				+ Odate + "]";
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


	public int getTip() {
		return Tip;
	}


	public void setTip(int tip) {
		Tip = tip;
	}




	public String getOdate() {
		return Odate;
	}


	public void setOdate(String odate) {
		Odate = odate;
	}


	public void setUid(String uid) {
		Uid = uid;
	}


	public String getUid() {
		return Uid;
	}


	public int getScore() {
		return Score;
	}


	public void setScore(int score) {
		Score = score;
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		   
        Object o=null;    
       try    
        {    
         o=(PassNeed)super.clone();//Object �е�clone()ʶ�����Ҫ���Ƶ�����һ������    
        }    
       catch(CloneNotSupportedException e)    
        {    
            System.out.println(e.toString());    
        }    
       return o;    
    }    
	}
	  

	  

	  

