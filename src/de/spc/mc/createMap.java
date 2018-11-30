package de.spc.mc;
import java.awt.Color;

import org.bukkit.Location;
import org.bukkit.Material;


public class createMap {
	
	
	public void doit(Location loc, boolean ground, String name,String path) {
		// TODO Auto-generated method stub
	//String testmap = "E:/MinecraftAG/mapdata/"+name;
		String testmap = path+name;
		try {
			 int startx = (int) loc.getX();
			 int startz = (int) loc.getZ(); 
			 int starty = (int) loc.getY();

			MatchColor m = new MatchColor();
			ImagePixelReader read = new ImagePixelReader(testmap);
			if (read != null) { 
			int wid = read.Getxdim();
			int hei = read.Getydim();
			for (int x = 0; x<wid; x++) {
				for (int y = 0;y<hei;y++)
				{

					Color c = read.GetColor(x, y);
					//System.out.println(c.toString());
					
					String col = m.getColorNameFromColor(c);
					System.out.println(".........................."+col);
					Material mat = internalCommandState.MatCol2Mat (col);
			    	 if (loc.getBlock() != null)loc.getBlock().setType(mat);
			    	 if (ground == true) {
				    	 loc.setX(startx+ wid - x);
				    	 loc.setZ(startz+ hei - y);
				    	 loc.setY(starty);// hoehe
			    	 }
			    	 else {
				    	 loc.setX(startx+wid - x);
				    	 loc.setZ(startz);
				    	 loc.setY(starty+hei-y);// hoehe
			    		 
			    	 }

				}
			}
			}// if read
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
