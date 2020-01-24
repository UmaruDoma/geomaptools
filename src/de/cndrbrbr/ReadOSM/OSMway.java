package de.cndrbrbr.ReadOSM;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;

/*
 * 	<way id="38549389">
	<nd ref="456356412"/>
	<nd ref="456356396"/>
	<nd ref="456356377"/>
	<nd ref="456356408"/>
	<nd ref="5676247883"/>
	<nd ref="4091350615"/>
	<nd ref="456356370"/>
	<tag k="highway" v="service"/>
	<tag k="lanes" v="1"/>
	<tag k="oneway" v="yes"/>
	</way>
*/ 

public class OSMway {
	
	String id;
	Location playerPos;
	
	private  Map<String, OSMNode> nodes;
	
	private  Map<String, String> tags = new HashMap();
	
	
	public OSMway(Map<String, OSMNode> nodes2, Location playerpos2) {
	
		nodes = nodes2;
		MCWCpoint.setBlockMeterScale(1/1.5);
		MCWCpoint.setRefLatN(50.622972); //Koordinaten Campus 
		MCWCpoint.setRefLonE(7.040316);
		playerPos = playerpos2;
		
	}

	// <nd ref="456356412"/>
	private String GetNodeid(String iline)
	{
		String line = StringUtls.washstring(iline);
		try {
			
			String[] parts = line.split(" ");
			int max = parts.length;
			for (int i = 1; i<max;i++)
			{
				if ((parts[i] != null)&& (parts[i].contains("="))) {
					String[] subparts = parts[i].split("=");
					switch (subparts[0]) {
						case "ref": return (subparts[1]); 
					}
				}										
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private boolean firstNode = true;
	private boolean waystarted = false;
	// draw as they come!
	// draw single way after reading,
	// then dispose
	public void OSMWayAddLine (String line)
	{
		List<MCWCpoint> ptList = new ArrayList();
		
		MCWCpoint lastNode = null; // lastnode coords
		
		// ein way gestartet
		if (line.contains("<way")) { waystarted = true; return;}
		
		// einen node gefunden
		if (waystarted && line.contains("<nd")){
			
			//nummer des node lesen
			String nodeid = GetNodeid(line);// get the Nodeid
			
			// wenn es den gibt
			if (nodes.containsKey(nodeid)) {
				OSMNode node = nodes.get(nodeid);
				
				// es war der erste
				if (firstNode) {// copy vals to lastnode
					firstNode = false;
					double la = Double.parseDouble(node.lat);
					double lo = Double.parseDouble(node.lon);
					lastNode = new MCWCpoint (la,lo,MCWCpoint.ISDEGREE);
					ptList.add(lastNode);
					playerPos.setX(lastNode.getBlockX());
					playerPos.setZ(lastNode.getBlockZ());
					playerPos.getBlock().setType(Material.GLOWSTONE);
				}
				// es war ein folgeknoten, linie wird gemalt
				else {			
					double Lat2 = Double.parseDouble(node.lat);
					double Lon2 = Double.parseDouble(node.lon);
					MCWCpoint toPoint = new MCWCpoint(Lat2,Lon2,MCWCpoint.ISDEGREE);
					DrawLine(lastNode,toPoint,playerPos);
					// copy secnode to lastnode
					System.out.print("...");
					System.out.println(toPoint.toString());
					// DrawLineInMinecraftWorld() 
					// im plugin werden georeferenzpunkt, massstab und skalierung pro welt gespeichert
					// color according to tags
					
					lastNode = toPoint;
					
					playerPos.setX(lastNode.getBlockX());
					playerPos.setZ(lastNode.getBlockZ());
					playerPos.getBlock().setType(Material.GLOWSTONE);

					ptList.add(lastNode);
					
					//2do zum schluss ein Schild mit dem Namen setzen
				}
			}
		}
		if (line.contains("</way>")) {// finished the lines of one way
			firstNode = true;
			tags.clear();
			ptList.clear();
			waystarted = false;
			
		}
		if (waystarted && line.contains("<tag")) {
			OSMtag tag = new OSMtag(line);
			tags.put(tag.k, tag.v);
		}
	}

	public void DrawLine(MCWCpoint lastNode, MCWCpoint toPoint, Location loc) {
		
		MCWCpoint p1;
		MCWCpoint p2;
		MCWCpoint norm;
		if ((lastNode ==  null)|| (toPoint==null)) {
			System.out.println("Punkt null !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return;

		}
		if (lastNode.getBlockZ()<= toPoint.getBlockZ()) 
		{
			norm = lastNode;
			p1 = new MCWCpoint(0.0,0.0,MCWCpoint.ISBLOCKS);  // lastNode ist unten
			p2 = new MCWCpoint(toPoint.getBlockX() - lastNode.getBlockX(),toPoint.getBlockZ()-lastNode.getBlockZ(),MCWCpoint.ISBLOCKS);
	
		}
		else {
			norm = toPoint;
			p1 = new MCWCpoint(0.0,0.0,MCWCpoint.ISBLOCKS);	// toPoint ist unten
			p2 = new MCWCpoint(lastNode.getBlockX() - toPoint.getBlockX(),lastNode.getBlockZ()-toPoint.getBlockZ(),MCWCpoint.ISBLOCKS);
		}
		
		
		double dx = p2.getBlockX() - p1.getBlockX();
		double dz = p2.getBlockZ() - p1.getBlockZ();
		
		boolean steigend = (dz/dx)>0;
		
		boolean schnellesX = abs(dx) > abs(dz);

		if (schnellesX) {
			double f = dx/2;
			double z = 1;
			int x  = 1;
			while (x<p2.getBlockX())
			{
				f = f-dz;
				
				if (f<0) {
					f = f + dx;
					if (steigend) z = z + 1;
					else z = z-1;
				}
				System.out.println("x= " + x + " y = " + z);
				if (loc!= null) {
					loc.setX(norm.getBlockX()+x);
					loc.setZ(norm.getBlockZ()+z);
					loc.getBlock().setType(Material.GLOWSTONE);	
				}
				//else 
				{
					double tx = norm.getBlockX()+x;
					double ty = norm.getBlockZ()+z;
					System.out.println("x= " + tx + " y = " + ty);
					
				}
				if (steigend)x = x+1;
				else x = x-1;
			}
			
		}
		else {
			double f = dz/2;
			double x = 1;
			double z = 1;
			while (z<p2.getBlockZ())
			{
				f = f-dx;
				
				if (f<0) {
					f = f + dz;
					if (steigend)x = x+1;
					else x = x-1;
				}
				System.out.println("x= " + x + " y = " + z);

				if (loc!= null) {
					loc.setX(norm.getBlockX()+x);
					loc.setZ(norm.getBlockZ()+z);
					loc.getBlock().setType(Material.GLOWSTONE);		
				}
				//else 
				{
					double tx = norm.getBlockX()+x;
					double ty = norm.getBlockZ()+z;
					System.out.println("x= " + tx + " y = " + ty);
					
				}
				if (steigend) z = z + 1;
				else z = z-1;
			}
			
		}

	}

	private double abs(double x) {
		if (x>0) return x;
		else return x*(-1);
	}
	
	
}
