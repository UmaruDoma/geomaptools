package de.cndrbrbr.ReadOSM;

public class StringUtls {
	
	public static String washstring (String sin)
	{
		StringBuilder out = new StringBuilder();
		byte[] in = sin.getBytes();
		for (int i= 0;i< sin.length();i++)
		{
			char c  = (char)in[i];
		
			switch (c) {
				case '\"' : 
				case '<' : 
				case '/' : 
				case '>' : break;
				default : out.append(c); break;			
			}
		}
		return out.toString();
	}
}
