package de.cndrbrbr.ReadOSM;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//<node id="248111682" lat="50.6217335" lon="7.0402111"/>
//<node id="1626361181" lat="50.6258026" lon="7.0403119"/>	
//
//  <node id="248111700" lat="50.6255738" lon="7.0339469">
//	<tag k="bicycle" v="yes"/>
//	<tag k="crossing" v="uncontrolled"/>
//	<tag k="highway" v="crossing"/>
//	</node>

public class OSMNode {
	
	

	public  String id;
	

	public String lat;

	public String lon;
	
	private Map<String, String> tags = new HashMap();
	
	

	public void AddTag (String key, String value)
	{
		tags.put(key, value);
	}
	
	public String GetTag (String key)
	{
		if (tags.containsKey(key)) {
			return tags.get(key);
		}
		else return null;
	}
	
	
	
	static boolean multiline = false;
	static OSMNode theNode = null;
	
	/*
	 *  <node id="248111700" lat="50.6255738" lon="7.0339469">
    	<tag k="bicycle" v="yes"/>
    	<tag k="crossing" v="uncontrolled"/>
    	<tag k="highway" v="crossing"/>
  		</node>
	 * 
	 * 
	 * 
	 */
	
	
	
	static public OSMNode OSMNodeAddLine (String line)
	{
		
		if (!multiline) {
			
			if (line.contains("<node") && line.contains("/>")) {
				return new OSMNode(line);
			}
			
			if (line.contains("<node") && !line.contains("/>"))  {
				multiline = true;
				theNode = new OSMNode(line);
				return null;
			}
		}
		if (multiline) {
			if (line.contains("</node>")) 
			{				
				multiline = false;
				return theNode;
			}
			if (line.contains("<tag")) 
			{
				OSMtag tag = new OSMtag(line);
				theNode.AddTag(tag.k, tag.v);
			}
			
		}
		return null;

	}
	
	
	public OSMNode(String iline) {
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
							case "id": id = subparts[1]; break;
							case "lat": lat = subparts[1]; break;
							case "lon": lon = subparts[1]; break;
							
						}
					}										
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		
	}

}

