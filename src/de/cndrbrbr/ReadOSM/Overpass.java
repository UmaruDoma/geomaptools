package de.cndrbrbr.ReadOSM;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

public class Overpass {
	
	private Location playerpos;
	
	public Overpass(Location ploc) 
	{
		playerpos = ploc;
	}
	
	// input rectangle for Import, startpoint and meter, startpoint endpoint 
	public void importOSMData2World() 
	{
	try {
		
		// import osm data by rect
		ReadOSMData importer = new ReadOSMData();
		importer.importOSM("50.621030", "50.628777", "7.035969", "7.047216"); 
	
		// Read data into MC Map
		//ReadOSMFile of = new ReadOSMFile("M:\\MinecraftAG\\temp\\resultOSM.txt");	
		
		Map<String, OSMNode> nodes = new HashMap();
		
		
		// read the nodes from the file einmal durch!
		
		ReadOSMFile of = new ReadOSMFile("M:\\MinecraftAG\\temp\\resultOSM.txt");	
		String line = of.readFirstLine();
		
		while (line != null) {
			System.out.println(line);
			OSMNode nod = OSMNode.OSMNodeAddLine(line);
			if (nod!= null)nodes.put(nod.id, nod);
			line = of.readNextLine();
		}
		
		// the read the ways from the file, nochmal durch!
		
		OSMway way = new OSMway(nodes, playerpos);
		line = of.readFirstLine();
		while (line != null) {
			System.out.println(line);
			way.OSMWayAddLine(line);
			line = of.readNextLine();
		}
				
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
}