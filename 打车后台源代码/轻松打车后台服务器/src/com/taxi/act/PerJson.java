package com.taxi.act;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

// ���� ����requset �ж�ȡJson  String ,jsonתString
public class PerJson {
	private static PerJson Per=new PerJson();


	
	public static PerJson getPer() {
		return Per;
	}


	//��ȡjson -String
	public String readJSON(HttpServletRequest request){  
        StringBuffer json = new StringBuffer();  
        String line = null;  
        try {  
            BufferedReader reader = request.getReader();  
            while((line = reader.readLine()) != null) {  
                json.append(line);  
            }  
        }  
        catch(Exception e) {  
            System.out.println("Error reading JSON string: " + e.toString());  
        }  
        return json.toString();  
    }  
  
	
	// ��JSONObject   תΪString  д������   �׳��쳣
	public void  jsonToString(String jsonObj,HttpServletResponse response)
	{
		try {
			response.getWriter().write(jsonObj);
			response.getWriter().flush();
			response.getWriter().close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Writing JSON string: " + e.toString());  
			
		}
		
		
		
		
	}
}
