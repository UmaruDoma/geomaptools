package de.cndrbrbr.ReadOSM;
import java.io.*;
import java.net.*;
import java.util.*;
public class ReadOSMData {

	ReadOSMData (){}
	
	public boolean importOSM(String south, String north, String west, String east) 
	{
		try {
	        URL url = new URL("https://lz4.overpass-api.de/api/interpreter");

	        StringBuilder postData = new StringBuilder();
	        postData.append("<bbox-query s=\"50.621030\" n=\"50.628777\" w=\"7.035969\" e=\"7.047216\"/>");
//	        postData.append("<bbox-query s=\""+south+"\" n=\""+north+"\" w=\""+west+"\" e=\""+east+"\"/>");
	        postData.append("<recurse type=\"node-way\"/>");
	        postData.append("<union>");
	        postData.append("<item/>");
	        postData.append("<recurse type=\"down\"/>");
	        postData.append("</union>");
	        postData.append("<print/>");
	        
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);

	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        FileOutputStream outputStream = new FileOutputStream("E:\\Minecraft\\dev\\data\\resultOSM.txt");
	        for (int c; (c = in.read()) >= 0;) {
	            System.out.print((char)c);
	        	outputStream.write((char)c);
	        }
	        outputStream.close();
	        return true;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	       
	 }
	    
	  
	   
	    
	}

