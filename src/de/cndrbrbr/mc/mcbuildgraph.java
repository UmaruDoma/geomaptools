// (c)cndrbrbr 2018 
package de.cndrbrbr.mc;

import java.util.logging.Logger;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.Wool;

public class mcbuildgraph {
	
	public mcbuildgraph(Logger log) {
		super();
		this.log = log;
	}

	Logger log;
	
	// math
	
	private double abs(double z) {
		if (z>=0) return z;
		else return -z;
		
	}
	
	
	// geo 
	
	 public String getOrientation (double yaw)
	 {
		 log.info("yaw =  "+ yaw);
		 if (((yaw > 0)&&(yaw <= 45))|| ((yaw >= 315)&&(yaw <= 360))|| ((yaw >= -45)&&(yaw <= 0)) || ((yaw >= -360)&&(yaw <= -315))){
			 log.info("South");
			 return "South";
		 }
		 if (((yaw > 45)&&(yaw <= 135)) || ((yaw >= -315)&&(yaw <= -225))){
			 log.info("West");
			 return "West";
		 }
		 if (((yaw > 135)&&(yaw <= 225))|| ((yaw >= -225)&&(yaw <= -135)) ){
			 log.info("North");
			 return "North";
		 }
		 if (((yaw > 225)&&(yaw <= 315)|| ((yaw >= -135)&&(yaw <= -45)))){
			 log.info("East");
			 return "East";
		 }
		 log.info("NULL");
		 return null;
	 }
	
	// block operations

	 public void SetColAndMat (Material mat,DyeColor theColor, Location loc)
	 {
		 Block b = loc.getBlock();
		 b.setType(mat);
		 if (mat == Material.WOOL) {
			 
	        BlockState bs = loc.getBlock().getState();
	        Wool wool = (Wool) bs.getData();
	        wool.setColor(theColor);
	        bs.update();
		 }

	 }
	 
	public void setBlockColMat (World world, double x, double y, double z, Material mat,DyeColor theColor)
	{
		 Location loc = new Location(world, x, y, z);
		 SetColAndMat ( mat, theColor,  loc);
	}
	
	public DyeColor GetColor (Block b) 
	{
		if (b.getType() == Material.WOOL) {
			DyeColor col = ((Wool) b.getState().getData()).getColor();		
			return col;
		}
		else return null;
	}
		
	 public boolean IsSameMaterialAndColor (Location loc,Material mat, DyeColor theColor)
	 {
		 Block b = loc.getBlock();	 
		 
		 if ((b.getType() == mat) && (theColor == GetColor (b) )) return true;
		 return false;
	 }

	
	 // building
	  
	 public void BuildOnNeighBlocks(Material mat, DyeColor col, Location loc, int radius, int hei, int startx, int starty, int startz)
	 {
		 if (loc == null) return;
//		 log.info("BuildOnNeighBlocks "+ mat.toString()+" "+hei +" "+ loc.getX() +""+ loc.getY() +""+ loc.getZ()  + " "+ radius + " " +startx + " "+ starty+ " " + startz);
		 
		 if (!IsSameMaterialAndColor (loc,mat,col)) return;
		 
		 int locx = (int) loc.getX();
		 int locz = (int) loc.getZ(); 
		 int locy = (int) loc.getY(); 

		
		 for (int i = 1; i<= hei; i++) 
		 {
			 loc.setX(locx);
			 loc.setY(locy + i);
			 loc.setZ(locz);
			 SetColAndMat (mat,col, loc);
			 //loc.getBlock().setType(mat);
		 }
		 
		 
		 if (abs(abs(startx) - abs(locx)) > radius) return;
		 if (abs(abs(startz) - abs(locz)) > radius) return;
		 
		 // x
		 loc.setX(locx + 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 BuildOnNeighBlocks(mat,col,loc,radius,hei,  startx, starty, startz);

	 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz + 1);
		 BuildOnNeighBlocks(mat,col,loc,radius,hei,  startx, starty, startz);

		 loc.setX(locx - 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 BuildOnNeighBlocks(mat,col,loc,radius,hei,  startx, starty, startz);

	 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz - 1);
		 BuildOnNeighBlocks(mat,col,loc,radius,hei, startx, starty, startz);
		 
		 
	 }	 
	 
	 
	 
	 public void RemoveNeighBlocks(Material mat,DyeColor col, Location loc, int radius, int startx, int starty, int startz)
	 {
		 if (loc == null) return;
		 log.info("RemoveNeighBlocks "+ mat.toString() +" "+ loc.getX() +""+ loc.getY() +""+ loc.getZ()  + " "+ radius + " " +startx + " "+ starty+ " " + startz);
		 
		 if (!IsSameMaterialAndColor (loc,mat,col)) return;
		 
		 int locx = (int) loc.getX();
		 int locy = (int) loc.getY();
		 int locz = (int) loc.getZ(); 

		 
		 loc.getBlock().setType(Material.AIR);
		 
		 if (abs(abs(startx) - abs(locx)) > radius) return;
		 if (abs(abs(starty) - abs(locy)) > radius) return;
		 if (abs(abs(startz) - abs(locz)) > radius) return;
		 
		 // x
		 loc.setX(locx + 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,col,loc,radius, startx, starty, startz);

		 // y
		 loc.setX(locx);
		 loc.setY(locy + 1);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,col,loc,radius, startx, starty, startz);
		 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz + 1);
		 RemoveNeighBlocks(mat,col,loc,radius, startx, starty, startz);

		 loc.setX(locx - 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,col,loc,radius, startx, starty, startz);

		 // y
		 loc.setX(locx);
		 loc.setY(locy - 1);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,col,loc,radius, startx, starty, startz);
		 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz - 1);
		 RemoveNeighBlocks(mat,col,loc,radius, startx, starty, startz);
		 
		 
	 }
 
	

	 
	 
	 public void DrawBlocksDirection(Material mat,DyeColor theColor, Location loc, int ix, int iy, int iz, double yaw )
	 {
		 if (loc == null) return;
		 
		 String  heading = getOrientation (yaw);
		 		 
		 int startx = (int) loc.getX();
		 int startz = (int) loc.getZ(); 
		 int starty = (int) loc.getY();
		 
		 
		 
		 switch (heading)  {
			case "North":	  // -z +x +y			
			{									
			     for (int x =  startx; x < startx + ix; x++) {
				     for (int z = startz; z > startz - iz; z--) {
				    	 for (int y = starty; y < starty + iy; y++ ) {
					    	 if (loc.getBlock() != null) SetColAndMat (mat,theColor,loc);
					    	 loc.setX(x);
					    	 loc.setZ(z);
					    	 loc.setY(y);
				    	 }
				     }	 
			     }
			     if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);  //.setType(mat);
				
			} break;
			case "South":	// +z -x			
			{								
			     for (int x =  startx; x > startx - ix; x--) {
				     for (int z = startz; z < startz + iz; z++) {
				    	 for (int y = starty; y < starty + iy; y++ ) {
					    	 if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);
					    	 loc.setX(x);
					    	 loc.setZ(z);
					    	 loc.setY(y);
				    	 }
				     }	 
			     }
		    	 if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);	 

				
			} break;
			case "East":				
			{						
				
			     for (int x =  startx; x < startx + iz; x++) {
				     for (int z = startz; z < startz + ix; z++) {
				    	 for (int y = starty; y < starty + iy; y++ ) {
					    	 if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);
					    	 loc.setX(x);
					    	 loc.setZ(z);
					    	 loc.setY(y);
				    	 }
				     }	 
			     }
		    	 if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);	 

				
			} break;
			case "West":				
			{						
				
			     for (int x =  startx; x > startx - iz; x--) {
				     for (int z = startz; z > startz - ix; z--) {
				    	 for (int y = starty; y < starty + iy; y++ ) {
					    	 if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);
					    	 loc.setX(x);
					    	 loc.setZ(z);
					    	 loc.setY(y);
				    	 }
				     }	 
			     }
		    	 if (loc.getBlock() != null)SetColAndMat (mat,theColor,loc);
				
			} break;
			}

	 }
	 
	 
/*	 private void DrawBlocks (Material mat, Location loc, int ix, int iy, int iz)
	 {
		 int startx = (int) loc.getX();
		 int startz = (int) loc.getZ(); 
		 int starty = (int) loc.getY();
	     for (int x =  startx; x < startx + ix; x++) {
		     for (int z = startz; z < startz + iz; z++) {
		    	 for (int y = starty; y < starty + iy; y++ ) {
			    	 if (loc.getBlock() != null)loc.getBlock().setType(mat);
			    	 loc.setX(x);
			    	 loc.setZ(z);
			    	 loc.setY(y);
		    	 }
		     }	 
	     }
    	 if (loc.getBlock() != null)loc.getBlock().setType(mat);		 
	 }
*/

	 

	

}
