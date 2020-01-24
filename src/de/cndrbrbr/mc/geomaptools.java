// (c)cndrbrbr 2018 
package de.cndrbrbr.mc;


import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Colorable;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import de.cndrbrbr.ReadOSM.*;



// https://yivesmirror.com/downloads/craftbukkit

public class geomaptools extends JavaPlugin implements Listener{
	
	playersList state = null;
	Logger log = null;
	String pathtomaps;
	mcbuildgraph gm; // graphic manager

	public void onEnable(){ 
		
		log = this.getLogger();
		try {
			pathtomaps = getConfig().getString("geo.path");
			saveDefaultConfig();
		}
		catch(Exception e)
		{
			log.info(e.toString());
			saveDefaultConfig();
		}
		
		state = new playersList(log);
		gm = new mcbuildgraph(log);
		log.info("spc geotools have been enabled.");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable(){ 
		this.getLogger().info("spc geotools have been disabled.");
	}
	 @EventHandler
	 public void onBlockBreak(BlockBreakEvent event) {
		 Player p =   event.getPlayer();
		 Block b= event.getBlock();
		 Material mat = b.getType();
		

		  Bukkit.broadcastMessage("Block break! "+p.getName()+" "+mat.name()
		  		+" "+ b.getLocation().getWorld().getName()
				+" "+  b.getLocation().getBlockX()
				+" "+  b.getLocation().getBlockZ()
				+" "+  b.getLocation().getBlockY());
	 }

/*	 @EventHandler
	 public void onBlockPlace(BlockPlaceEvent event) {
		 Player p = event.getPlayer();
		 Block b= event.getBlock();
		 Material mat = b.getType();

		  Bukkit.broadcastMessage("Block place! "+p.getName()+" "+mat.name()
	  		+" "+ b.getLocation().getWorld().getName()
			+" "+  b.getLocation().getBlockX()
			+" "+  b.getLocation().getBlockZ()
			+" "+  b.getLocation().getBlockY());

	 }
	 */

	 
	 @EventHandler
	 public void onPlayerMove(PlayerMoveEvent event) {
		 String playername = event.getPlayer().getName();
 		 Location to = event.getTo();
		 Location from = event.getFrom();
		 internalCommandState istate = state.GetState(playername);
	//	  blockinHand = event.getPlayer().getItemInHand().getData().getItemType();
		 
			
		 if (state.IsCommandActive(playername,"gspur")) 
		 {
			 Material mat;
			 DyeColor theColor = null;
			 ItemStack items = event.getPlayer().getInventory().getItemInMainHand();
			 
			 if (items != null) {	 
				 mat = items.getType();			 
				 if (mat == Material.WOOL) {
					 theColor= ((Wool)items.getData()).getColor();
				 }
			 }
			 else {
				 mat = state.GetMaterial(playername);
			 }

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
				 gm.SetColAndMat ( mat, theColor, from);
				 
			 }
		 }
		 if (istate != null) {
			 istate.setLastx(from.getBlockX());
			 istate.setLasty(from.getBlockY());
			 istate.setLastz(from.getBlockZ());
			 
		 }
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
			DyeColor theColor;// = col.getColor();
			if (mat == Material.WOOL) {
				theColor = ((Wool) clickedblock.getState().getData()).getColor();
			}
			else theColor = null;
			this.getLogger().info(mat + " " + theColor);  
				 
			  
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
					 gm.DrawBlocksDirection(mat,theColor, clickpos,istate.Getwidx(),istate.Getheiy(),istate.Getdepz(), yaw);				 

			 }
			 if (state.IsCommandActive(playername,"gforward")) 
			 {
				 this.getLogger().info("gforward selected !!!");

				 				 
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 gm.DrawBlocksDirection(mat,theColor, clickpos,1,istate.Getheiy(),istate.Getdepz(), yaw);				 
			 
				 						
			}
			 if (state.IsCommandActive(playername,"gdelete")) 
			 {
				 this.getLogger().info("gdelete selected !!!");
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 gm.RemoveNeighBlocks(mat,theColor, clickpos,istate.getRadius(),(int)clickpos.getX(),(int)clickpos.getY(),(int)clickpos.getZ());				 
			 }
			 
			 if (state.IsCommandActive(playername,"gbuildalong")) 
			 {
				 this.getLogger().info("gbuildalong selected !!!");
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				  
				 gm.BuildOnNeighBlocks(mat,theColor, clickpos,istate.getRadius(),istate.Getheiy(),(int)clickpos.getX(),(int)clickpos.getY(),(int)clickpos.getZ());				 
			 }
			 
			 
			 
			 if (state.IsCommandActive(playername,"gmap")) 
			 {
				 this.getLogger().info("gmap selected !!!");

				 				 
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 createMap m = new createMap();
				 this.getLogger().info("gmap "+istate.getImagename()+ " "+ pathtomaps);
				 m.doit( clickpos,istate.isGround(),istate.getImagename(),pathtomaps,true,gm);
				 				 
				 
				 						
			} 
			 if (state.IsCommandActive(playername,"gspawnColorSheeps")) 
			 {
				 this.getLogger().info("gspawnColorSheeps selected !!!");

				 				 
				
				 if (clickpos == null) {
					 this.getLogger().info("clickpos is null");
					 return;
				 }
				 
				 SpawnCreatures sp = new SpawnCreatures();
				 this.getLogger().info("gspawnColorSheeps "+istate.getNumber());
				 sp.SpawnColorSheeps(clickpos,istate.getNumber());
				 						
			} 
			
				 
				 

		 }
		 
	 }

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

		this.getLogger().info("Activted "+ cmd.getName() + " for " + sender);
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
					case "gspawnColorSheeps":
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
					
					case "gOSMOverpass":
	
					{
						Location startBlock = ((Player) sender).getLocation();
						Overpass ov = new Overpass(startBlock);
						ov.importOSMData2World();
						
					
					}break;
				
				}// switch					

			}
			
			else {
				sender.sendMessage("this command can only be run by a player");
			}
			
			return true;

		} // true, event behandelt

		return false; 

	}



}