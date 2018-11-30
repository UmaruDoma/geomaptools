// (c)Sabine 2018 
package de.spc.mc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Colorable;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;



// https://yivesmirror.com/downloads/craftbukkit

public class geomaptools extends JavaPlugin implements Listener{
	
	playersList state = null;
	Logger log = null;
	String pathtomaps;

	public void onEnable(){ 
		
		log = this.getLogger();
		try {
			pathtomaps = getConfig().getString("geo.path");
		}
		catch(Exception e)
		{
			log.info(e.toString());
			saveDefaultConfig();
		}
		
		state = new playersList(log);
		log.info("spc geotools have been enabled.");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	 @EventHandler
	 public void onPlayerMove(PlayerMoveEvent event) {
		 String playername = event.getPlayer().getName();
 		 Location to = event.getTo();
		 Location from = event.getFrom();
		 internalCommandState istate = state.GetState(playername);
		 
		 if (state.IsCommandActive(playername,"gspur")) 
		 {
			 double num1 = event.getFrom().getY();
			 
			 if (state.IsMaterielRedstone(playername)) 
			 {
				 from.setY(from.getY() -2);
				 from.getBlock().setType(Material.REDSTONE_BLOCK);
				 from.setY(num1-1);
				 from.getBlock().setType(Material.POWERED_RAIL);
				 
			 }
			 else {
				 from.setY(from.getY() -1);
				 from.getBlock().setType(state.GetMaterial(playername));
			 }
		 }
		 if (istate != null) {
			 istate.setLastx(from.getBlockX());
			 istate.setLasty(from.getBlockY());
			 istate.setLastz(from.getBlockZ());
		 }
	 }
	 
	 
	 
	 private void BuildOnNeighBlocks(Material mat, Location loc, int radius, int hei, int startx, int starty, int startz)
	 {
		 if (loc == null) return;
		 log.info("BuildOnNeighBlocks "+ mat.toString()+" "+hei +" "+ loc.getX() +""+ loc.getY() +""+ loc.getZ()  + " "+ radius + " " +startx + " "+ starty+ " " + startz);
		 
		 if (loc.getBlock().getType() != mat) return;
		 
		 int locx = (int) loc.getX();
		 int locz = (int) loc.getZ(); 
		 int locy = (int) loc.getY(); 

		
		 for (int i = 1; i<= hei; i++) 
		 {
			 loc.setX(locx);
			 loc.setY(locy + i);
			 loc.setZ(locz);			 
			 loc.getBlock().setType(mat);
		 }
		 
		 
		 if (abs(abs(startx) - abs(locx)) > radius) return;
		 if (abs(abs(startz) - abs(locz)) > radius) return;
		 
		 // x
		 loc.setX(locx + 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 BuildOnNeighBlocks(mat,loc,radius,hei,  startx, starty, startz);

	 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz + 1);
		 BuildOnNeighBlocks(mat,loc,radius,hei,  startx, starty, startz);

		 loc.setX(locx - 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 BuildOnNeighBlocks(mat,loc,radius,hei,  startx, starty, startz);

	 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz - 1);
		 BuildOnNeighBlocks(mat,loc,radius,hei, startx, starty, startz);
		 
		 
	 }	 
	 
	 
	 
	 private void RemoveNeighBlocks(Material mat, Location loc, int radius, int startx, int starty, int startz)
	 {
		 if (loc == null) return;
		 log.info("RemoveNeighBlocks "+ mat.toString() +" "+ loc.getX() +""+ loc.getY() +""+ loc.getZ()  + " "+ radius + " " +startx + " "+ starty+ " " + startz);
		 
		 if (loc.getBlock().getType() != mat) return;
		 
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
		 RemoveNeighBlocks(mat,loc,radius, startx, starty, startz);

		 // y
		 loc.setX(locx);
		 loc.setY(locy + 1);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,loc,radius, startx, starty, startz);
		 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz + 1);
		 RemoveNeighBlocks(mat,loc,radius, startx, starty, startz);

		 loc.setX(locx - 1);
		 loc.setY(locy);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,loc,radius, startx, starty, startz);

		 // y
		 loc.setX(locx);
		 loc.setY(locy - 1);
		 loc.setZ(locz);
		 RemoveNeighBlocks(mat,loc,radius, startx, starty, startz);
		 
		 // z
		 loc.setX(locx);
		 loc.setY(locy);
		 loc.setZ(locz - 1);
		 RemoveNeighBlocks(mat,loc,radius, startx, starty, startz);
		 
		 
	 }
 
	 private void SetColAndMat (Material mat,DyeColor theColor, Location loc)
	 {
		 Block b = loc.getBlock();
		 b.setType(mat);
		 if (mat == Material.WOOL) {
			 
	        BlockState bs = loc.getBlock().getState();
	        Wool wool = (Wool) bs.getData();
	        wool.setColor(theColor);
	        bs.update();
		 }

			
			//Colorable col = (Colorable)b.getState().getData();
			//col.setColor(theColor);
			// this.getLogger().info("colmat " + mat + theColor);
			//((Wool) b.getState().getData()).setColor(theColor);
			
	 }

	 
	 
	 private void DrawBlocksDirection(Material mat,DyeColor theColor, Location loc, int ix, int iy, int iz, String  heading)
	 {
		 if (loc == null) return;
		 //World world = loc.getWorld(); 		 
		 int startx = (int) loc.getX();
		 int startz = (int) loc.getZ(); 
		 int starty = (int) loc.getY();
		 
		 
		 
		 switch (heading)  {
			case "North":	  // -z +x +y			
			{						
				//setBlock (world, clickX, clickY+1, clickZ, mat);
				//setBlock (world, clickX, clickY+1, clickZ-1, mat);
				
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
				//setBlock (world, clickX, clickY+1, clickZ, mat);
				//setBlock (world, clickX, clickY+1, clickZ+1, mat);
				
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
				//setBlock (world, clickX, clickY+1, clickZ, mat);
				//setBlock (world, clickX+1, clickY+1, clickZ, mat);
				
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
				//setBlock (world, clickX, clickY+1, clickZ, mat);
				//setBlock (world, clickX-1, clickY+1, clickZ, mat);
				
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
	 
	 
	 private void DrawBlocks (Material mat, Location loc, int ix, int iy, int iz)
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

	 private String getOrientation (double yaw)
	 {
		 this.getLogger().info("yaw =  "+ yaw);
		 if (((yaw > 0)&&(yaw <= 45))|| ((yaw >= 315)&&(yaw <= 360))|| ((yaw >= -45)&&(yaw <= 0)) || ((yaw >= -360)&&(yaw <= -315))){
			 this.getLogger().info("South");
			 return "South";
		 }
		 if (((yaw > 45)&&(yaw <= 135)) || ((yaw >= -315)&&(yaw <= -225))){
			 this.getLogger().info("West");
			 return "West";
		 }
		 if (((yaw > 135)&&(yaw <= 225))|| ((yaw >= -225)&&(yaw <= -135)) ){
			 this.getLogger().info("North");
			 return "North";
		 }
		 if (((yaw > 225)&&(yaw <= 315)|| ((yaw >= -135)&&(yaw <= -45)))){
			 this.getLogger().info("East");
			 return "East";
		 }
		 this.getLogger().info("NULL");
		 return null;
	 }
	 
	private void setBlock (World world, double x, double y, double z, Material mat)
	{
		 Location loc = new Location(world, x, y, z);
		 loc.getBlock().setType(mat);
	}
	 
	 @EventHandler
	 public void onBlockPlaceEvent (BlockPlaceEvent event) {
		 
		 this.getLogger().info(".");
		 Player player = event.getPlayer(); if (player == null) return;
		 String playername = player.getName(); if (playername == null) return;
		 Location playerplace = player.getLocation(); if (playerplace == null) return;
		 double yaw = event.getPlayer().getLocation().getYaw();
		 this.getLogger().info("..");
	
		 Block clickedblock =  event.getBlockPlaced(); if (clickedblock == null) return;
		 
		 Location blockpos = clickedblock.getLocation(); if (blockpos == null) return;
		 internalCommandState istate = state.GetState(playername); if (istate == null) return;
	//	 Action action = event.getAction(); if (action== null) return;
		 Location clickpos =  clickedblock.getLocation();
		 this.getLogger().info("...");
		 
		 Material mat;
		 
//		 if ((action.equals(Action.RIGHT_CLICK_AIR)||
//			(action.equals(Action.RIGHT_CLICK_BLOCK))))
		 {
			
			 
			// Die Farbe des geclickten Blockes holen 
			mat =  clickedblock.getType();
			Colorable col = (Colorable)clickedblock.getState().getData();
			DyeColor theColor;// = col.getColor();
			this.getLogger().info(mat + " " + col);  
			theColor = ((Wool) clickedblock.getState().getData()).getColor();
			this.getLogger().info(mat + " " + theColor);  
				 
			// ItemMeta b = event.getItem().getItemMeta();
			 //clickedblock.setTypeIdAndData(0, arg1, arg2)
			// this.getLogger().info("meta........."+b.toString()+"......");
			  
			 if (state.IsCommandActive(playername,"gquad"))
			 { 
					 if (clickedblock == null) {
						 this.getLogger().info("Block is null");
						 return;
					 }
					 Location loc =  clickedblock.getLocation();
					 if (loc == null) {
						 this.getLogger().info("Location is null");
						 return;
					 }
					 Vector vec = playerplace.getDirection();
					 
					 float pitsch = playerplace.getPitch();
					 this.getLogger().info("direction X "+ vec.getX() + ", Y "+ vec.getY() + ",Z "+ vec.getZ()+ ", yaw "+ yaw + ", pitch " + pitsch);
					 //DrawBlocks (mat, loc, istate.Getwidx(),istate.Getheiy(),istate.Getdepz());
					 DrawBlocksDirection(mat,theColor, clickpos,istate.Getwidx(),istate.Getheiy(),istate.Getdepz(), getOrientation (yaw));				 

			 }
			 if (state.IsCommandActive(playername,"gforward")) 
			 {
				 this.getLogger().info("gforward selected !!!");

				 				 
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 DrawBlocksDirection(mat,theColor, clickpos,1,istate.Getheiy(),istate.Getdepz(), getOrientation (yaw));				 
				 
				// Location playerpos = event.getPlayer().getLocation();
				// if (playerpos == null) {
				//	 this.getLogger().info("playerpos is null");
				//	 return;
				// }
				/* World world   =  clickpos.getWorld();				 
				 double clickX =  clickpos.getX();
				 double clickY =  clickpos.getY(); 
				 double clickZ =  clickpos.getZ();
				 Location loc = new Location(world, clickX, clickY, clickZ); 

				 
				 double deltaX = abs(clickX) - abs(playerplace.getX());
				 double deltaZ = abs(clickZ) - abs(playerplace.getZ());
				 
				 this.getLogger().info("direction X "+ clickX + ", Y "+ clickZ + ",Z "+ clickY );
				 
				 String heading = getOrientation (yaw);
				 
				switch (heading)  {
					case "North":				
					{						
						setBlock (world, clickX, clickY+1, clickZ, mat);
						setBlock (world, clickX, clickY+1, clickZ-1, mat);
						
					} break;
					case "South":				
					{						
						setBlock (world, clickX, clickY+1, clickZ, mat);
						setBlock (world, clickX, clickY+1, clickZ+1, mat);
						
					} break;
					case "East":				
					{						
						setBlock (world, clickX, clickY+1, clickZ, mat);
						setBlock (world, clickX+1, clickY+1, clickZ, mat);
						
					} break;
					case "West":				
					{						
						setBlock (world, clickX, clickY+1, clickZ, mat);
						setBlock (world, clickX-1, clickY+1, clickZ, mat);
						
					} break;
				}*/
				 
				 						
			}
			 if (state.IsCommandActive(playername,"gdelete")) 
			 {
				 this.getLogger().info("gdelete selected !!!");
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 RemoveNeighBlocks(mat, clickpos,istate.getRadius(),(int)clickpos.getX(),(int)clickpos.getY(),(int)clickpos.getZ());				 
			 }
			 
			 if (state.IsCommandActive(playername,"gbuildalong")) 
			 {
				 this.getLogger().info("gbuildalong selected !!!");
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 BuildOnNeighBlocks(mat, clickpos,istate.getRadius(),istate.Getheiy(),(int)clickpos.getX(),(int)clickpos.getY(),(int)clickpos.getZ());				 
			 }
			 
			 
			 
			 if (state.IsCommandActive(playername,"gmap")) 
			 {
				 this.getLogger().info("gmap selected !!!");

				 				 
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 createMap m = new createMap();
				 m.doit( clickpos,istate.isGround(),istate.getImagename(),pathtomaps);
				 				 
				 
				 						
			} 

			//else {
					// this.getLogger().info("gformward not active");
				 
			//}
					 
				 

		 }
		 
	 }

	private double abs(double z) {
		if (z>=0) return z;
		else return -z;
		
	}

	public void onDisable(){ 
		this.getLogger().info("spc geotools have been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

		if(internalCommandState.IsValidCommand(cmd.getName())){ 

			if (sender instanceof Player) {
				String playername = sender.getName();
				
				String cmdname = cmd.getName();
				switch (cmdname)  {
				case "goff":				
				{
					state.CommandOff(playername);
					
				} break;
								
				case "gspur":	
				case "gquad":	
				case "gmap":
				case "gdelete":
				case "gbuildalong":				
				case "gforward":											
				{
					state.CommandOn(cmd.getName(),args,playername);
					this.getLogger().info("Activted "+ cmd.getName() + " for " + playername);

				} break;
				
				case "gload":
				{
					try {
						BufferedImage img = ImageIO.read(new URL("https://www.paulirish.com/lovesyou/new-browser-logos/firefox-256.png"));
						
					    
						if ((img.getHeight()< 500)&&(img.getWidth()<500))
						{
							File outputfile = new File("E:\\MinecraftAG\\mapdata\\saved.png");
						    ImageIO.write(img, "png", outputfile);
						}
					}
					catch (Exception e)
					{
						this.getLogger().info(e.toString());
					}
					state.CommandOff(playername);
				} break;

				
				}
				
									

			}
			
			else {
				sender.sendMessage("this command can only be run by a player");
			}
			
			return true;

		} // true, event behandelt

		return false; 

	}



}