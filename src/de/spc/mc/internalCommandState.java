package de.spc.mc;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Material;

import de.spc.mc.MatchColor.ColorName;

public class internalCommandState {

		private Logger log = null;
		private String  playername = null;
		private Material materie = null;
		private String lastcommand = null;
		//private Map<String, Long> NumcParams = new HashMap<String,Long>();
		
		private int widx = 1;
		private int heiy = 1;
		private int depz = 1;
		private String Imagename;
		private int radius = 30;
		
		public int getRadius()
		{
			return radius;
		}
		public String getImagename() {
			return Imagename;
		}

		public boolean isGround() {
			return ground;
		}
		private boolean ground;
		
		
		
		public int getLastx() {
			return lastx;
		}

		public void setLastx(int lastx) {
			this.lastx = lastx;
		}

		public int getLasty() {
			return lasty;
		}

		public void setLasty(int lasty) {
			this.lasty = lasty;
		}

		public int getLastz() {
			return lastz;
		}

		public void setLastz(int lastz) {
			this.lastz = lastz;
		}
		private int lastx = 0;
		private int lasty = 0;
		private int lastz = 0;
	
		internalCommandState(Logger alog)
		{
			log = alog;
		}

		public int Getwidx() {return widx;}
		public int Getheiy() {return heiy;}
		public int Getdepz() {return depz;}
		
		static public boolean IsValidCommand (String name)
		{
			switch (name)  {
			case "gforward":
			case "gspur":
			case "goff":
			case "gload":
			case "gdelete":
			case "gbuildalong":			
			case "gmap":
			case "gquad": return true;
			default: return false;
			}
		
		}
		
		public Material GetMateriel()
		{
			if (materie != null) return materie;
			return Material.GOLD_BLOCK;
		}
		public void CommandOff ()
		{
			lastcommand = null;
			materie = null;
			playername = null;
		}
		
		public void CommandOn (String cmd, String[] args, String player)
		{
			lastcommand = cmd;
			String matname = null;
			playername = player;
			log.info("args len = " + args.length);			
			
			
			switch (cmd)  {
			
				case "goff":				
				{		
					
				} break;
								
				case "gspur":	
				{
				  try {
						if ((args != null) && (args[0]!= null)) {
							matname = args[0];
							MatchMateriel (matname);
						}
						if ((args != null) && (args.length >= 1)){
							if (args[1]!= null) heiy = Integer.parseInt(args[1]); else heiy = 4;
						}
						else {  heiy = 1; }
					  }
					  catch (Exception e) {
						  widx = 4; heiy = 4; depz = 4;
						  log.info("exception gspur = " + e.toString());
					  }

				}break;		
				
				case "gquad":	
				{
				  try {
					if ((args != null) && (args[0]!= null)) {
							matname = args[0];
							MatchMateriel (matname);
						}
					if ((args != null) && (args.length >= 4)){
						if (args[1]!= null) {widx = Integer.parseInt(args[1]);} else widx = 2;
						if (args[2]!= null) {heiy = Integer.parseInt(args[2]);} else heiy = 2;
						if (args[3]!= null) {depz = Integer.parseInt(args[3]);} else depz = 2;
					}
					else { widx = 4; heiy = 4; depz = 4;}
				  }
				  catch (Exception e) {
					  widx = 6; heiy = 2; depz = 3;
					  log.info("exception  gquad = " + e.toString());
				  }
				}break;	
				
				case "gforward":											
				{
				  try {
						if ((args != null) && (args[0]!= null)) {
							matname = args[0];
							MatchMateriel (matname);
						}

						if ((args != null) && (args.length >= 3)){
							if (args[1]!= null) depz = Integer.parseInt(args[1]); else depz = 4;
							if (args[2]!= null) heiy = Integer.parseInt(args[2]); else heiy = 4;
							widx = 1;
						}
						else { heiy = 2; depz = 10; widx = 1;}
					  }
					  catch (Exception e) {
						  heiy = 3; depz = 15;widx = 1;
						  log.info("exception gforward = " + e.toString());
					  }

				}break;
				case "gmap":											
				{
				  try {
						if ((args != null) && (args.length >= 2)){
							if (args[0]!= null) Imagename = args[0]; else Imagename = "Minion.PNG";
							if (args[1]!= null) ground = args[1].equals("true"); else ground = false;
							
						}
						else {Imagename = "Minion.PNG"; ground = false;}
					  }
					  catch (Exception e) {
						  Imagename = "Minion.PNG"; ground = false;
						  log.info("exception gmap = " + e.toString());
					  }

				}break;
				
				
				case "gbuildalong":											
				{
				  try {
					   if (args != null){
				  
						if (args.length >= 1){
							if (args[0]!= null) radius = Integer.parseInt(args[0]); else radius = 10;
						}
						if (args.length >= 2){
								if (args[1]!= null) heiy = Integer.parseInt(args[1]); else heiy = 2;
											
							}
						}
						else {radius = 10; heiy = 2;}
				  }
					  
				  catch (Exception e) {
					  radius = 10;
					  heiy = 2;
					  log.info("exception gdelete = " + e.toString());
				  }

				}break;
				
				case "gdelete":											
				{
				  try {
						if ((args != null) && (args.length >= 1)){
							if (args[0]!= null) radius = Integer.parseInt(args[0]); else radius = 10;
											
						}
						else {radius = 10;}
					  }
					  catch (Exception e) {
						  radius = 10;
						  log.info("exception gdelete = " + e.toString());
					  }

				}break;

			} 
			
			//NumcParams[""]
			
		}
		public boolean IsPlayerEq (String name)
		{
			if (playername != null) {
				if (playername.equalsIgnoreCase(name))
						return true;
			}
			else log.info("playername is null");
			return false;
			
		}
		
		public boolean IsCommandEq (String name)
		{
			if (lastcommand != null) {
				if (lastcommand.equalsIgnoreCase(name))
						return true;
			}
			else log.info("lastcommand is null");
			return false;
		}
		private void MatchMateriel (String name)
		{
			materie = MatchMat(name);
			
		}
		static public Material MatchMat(String string) 
		{
			Material mat = null;
			if (string == null) return Material.GOLD_BLOCK;
			switch (string) {
				case "coal":  mat =  Material.COAL_BLOCK; break;
				case "gold":  mat =  Material.GOLD_BLOCK; break;
				case "diamond":  mat =  Material.DIAMOND_BLOCK; break;
				case "dirt":  mat =  Material.DIRT; break;
				case "fence":  mat =  Material.FENCE; break;
				case "glass":  mat =  Material.GLASS; break;
				case "sand":  mat =  Material.SAND; break;
				case "grass":  mat =  Material.GRASS; break;
				case "redstoneblock":  mat =  Material.REDSTONE_BLOCK; break;
				case "glowstone":  mat =  Material.GLOWSTONE; break;
				case "gravel":  mat =  Material.GRAVEL; break;
				case "poweredrail":  mat =  Material.POWERED_RAIL; break;
				case "potato":  mat =  Material.POTATO; break;
				case "apple":  mat =  Material.APPLE; break;
			
				default: mat = Material.GOLD_BLOCK;
			}

			
			return mat;
		}
		static public Material MatCol2Mat (String name)
		{
			Material mat = null;
			if (name == null) return Material.WHITE_GLAZED_TERRACOTTA;
			switch (name) {
//---------------------------------------------------------------
				case "Blue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "SteelBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "SlateBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "SkyBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "RoyalBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "PowderBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Navy":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "MidnightBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "MediumSlateBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "MediumBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "MediumAquaMarine":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "LightSteelBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "LightBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Indigo":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "LightSkyBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "DeepSkyBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "DarkSlateBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "DarkBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "AliceBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Aqua":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Aquamarine":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Azure":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "BlueViolet":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "CadetBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Chartreuse":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "CornflowerBlue":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Cornsilk":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Cyan":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "LightCyan":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "DarkCyan":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "Lavender":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
				case "LavenderBlush":  mat =  Material.BLUE_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Brown":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "Tan":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "Sienna":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "SandyBrown":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "SaddleBrown":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "RosyBrown":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "Moccasin":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "Chocolate":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
				case "BurlyWood":  mat =  Material.BROWN_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Gold":  mat =  Material.GOLD_BLOCK; break;
				case "GoldenRod":  mat =  Material.GOLD_BLOCK; break;
//---------------------------------------------------------------
				case "Gray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "SlateGray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "Silver":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "LightGray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "LightSlateGray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "DimGray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "DarkSlateGray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
				case "DarkGray":  mat =  Material.GRAY_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Green":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "SpringGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "SeaGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "PaleGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "OliveDrab":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "Olive":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "MintCream":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "MediumSpringGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "MediumSeaGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "Lime":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "LimeGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "LightGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "LawnGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "Khaki":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "ForestGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "LightSeaGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "DarkSeaGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "DarkGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "DarkKhaki":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
				case "DarkOliveGreen":  mat =  Material.GREEN_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Orange":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "Salmon":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "OrangeRed":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "PaleGoldenRod":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "LightSalmon":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "DarkOrange":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "LightCoral":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
				case "Coral":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Red":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "Tomato":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "Purple":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "Pink":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "MistyRose":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "MediumPurple":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "Magenta":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "LightPink":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "IndianRed":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "HotPink":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "Fuchsia":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "DeepPink":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "DarkMagenta":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "Crimson":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "FireBrick":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
				case "DarkRed":  mat =  Material.RED_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "White":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "WhiteSmoke":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "Snow":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "Linen":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "Ivory":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "GhostWhite":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "FloralWhite":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "AntiqueWhite":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "Beige":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "BlanchedAlmond":  mat =  Material.WHITE_GLAZED_TERRACOTTA; break;
				case "Bisque":  mat =  Material.ORANGE_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Yellow":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
				case "YellowGreen":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
				case "LightYellow":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
				case "LightGoldenRodYellow":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
				case "LemonChiffon":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
				case "GreenYellow":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
				case "HoneyDew":  mat =  Material.YELLOW_GLAZED_TERRACOTTA; break;
//---------------------------------------------------------------
				case "Black":  mat =  Material.BLACK_GLAZED_TERRACOTTA ; break;

				
				
				
				
				
				default: mat = Material.WHITE_GLAZED_TERRACOTTA;
			}			
			return mat;

		}
		

}
