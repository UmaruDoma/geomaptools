package de.cndrbrbr.ReadOSM;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//<tag k="bicycle" v="yes"/>


public class OSMtag {
		

	public String k;
	public String v;
		
	
	public OSMtag(String iline) {
		super();
		String line = StringUtls.washstring(iline);
			try {
				
				String[] parts = line.split(" ");
				int max = parts.length;
				for (int i = 1; i<max;i++)
				{
					if ((parts[i] != null)&& (parts[i].contains("="))) {
						String[] subparts = parts[i].split("=");
						switch (subparts[0]) {
							case "k": k = subparts[1]; break;
							case "v": v = subparts[1]; break;														
						}
					}										
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
}
