package com.taxi.act;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

// 此类 将从requset 中读取Json  String ,json转String
public class PerJson {
	private static PerJson Per=new PerJson();


	
	public static PerJson getPer() {
		return Per;
	}


	//读取json -String
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
  
	
	// 把JSONObject   转为String  写到流中   抛出异常
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
