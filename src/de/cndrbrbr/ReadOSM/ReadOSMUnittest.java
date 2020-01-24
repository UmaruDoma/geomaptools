package de.cndrbrbr.ReadOSM;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;

class ReadOSMUnittest {

	
	@Test
	void Importtest() {
		//fail("Not yet implemented");
	//	ReadOSMData importer =new ReadOSMData();
	//	importer.importOSM("","","",""); 
	}
	
	@Test
	void test() {
		//fail("Not yet implemented");
		
		Map<String, OSMNode> nodes = new HashMap();
		

		// read the nodes from the file einmal durch!
		
		ReadOSMFile of = new ReadOSMFile("E:\\Minecraft\\dev\\data\\resultOSM.txt");	
		String line = of.readFirstLine();
		
		while (line != null) {
			System.out.println(line);
			OSMNode nod = OSMNode.OSMNodeAddLine(line);
			if (nod!= null)nodes.put(nod.id, nod);
			line = of.readNextLine();
		}
		
		// the read the ways from the file, nochmal durch!
		OSMway way = new OSMway(nodes,null);
		line = of.readFirstLine();
		while (line != null) {
			System.out.println(line);
			way.OSMWayAddLine(line);
			line = of.readNextLine();
		}
		
		int a = 5;
		
		
		
	}
	@Test
	void coordtest() 
	{
		SphericalMercator merc = new SphericalMercator();
		// lat=\"50.6229454\" lon=\"7.0394219\
		double meterx =  merc.lon2x(7.0394219);
		double metery =  merc.lat2y(50.6229454);
		int a = 5;
	}
	
	@Test
	void OSMNodetest() 
	{
		Map<String, OSMNode> nodes = new HashMap();
		String aNode = "<node id=\"248111682\" lat=\"50.6217335\" lon=\"7.0402111\"/>";
		
		OSMNode no = OSMNode.OSMNodeAddLine (aNode);
		nodes.put(no.id, no);
		 
		List<String> nodelem =  new ArrayList();
		OSMNode no1 = OSMNode.OSMNodeAddLine("<node id=\"4233640121\" lat=\"50.6229454\" lon=\"7.0394219\">");
		OSMNode no2 = OSMNode.OSMNodeAddLine("<tag k=\"barrier\" v=\"bollard\"/>");
		OSMNode no3 = OSMNode.OSMNodeAddLine( "<tag k=\"bicycle\" v=\"yes\"/>");
		OSMNode no4 = OSMNode.OSMNodeAddLine( "<tag k=\"bollard\" v=\"removable\"/>");
		OSMNode no5 = OSMNode.OSMNodeAddLine( "<tag k=\"foot\" v=\"yes\"/>");
		OSMNode no6 = OSMNode.OSMNodeAddLine( "<tag k=\"material\" v=\"metal\"/>");
		OSMNode no7 = OSMNode.OSMNodeAddLine("</node>");
		nodes.put(no.id, no7);

	
	}
	@Test
	void BresenhamLine() {
		OSMway os = new OSMway(null,null);
		MCWCpoint p1 = new MCWCpoint(10.0,10.0,MCWCpoint.ISBLOCKS);	// toPoint ist unten
		MCWCpoint p2= new MCWCpoint(20.0,22.0,MCWCpoint.ISBLOCKS);	// toPoint ist unten
		os.DrawLine(p1, p2, null);
	}

}
