// (c)cndrbrbr 2018 
package de.cndrbrbr.mc;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Material;

import de.cndrbrbr.mc.MatchColor.ColorName;

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
		private int number = 10;
		
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
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
			case "gOSMOverpass":
			case "gspawnColorSheeps":
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
				
				
				case "gspawnColorSheeps":											
				{
				  try {
						if ((args != null) && (args[0]!= null)) {
							number = Integer.parseInt(args[0]);
						}

					  }
					  catch (Exception e) {
						  log.info("exception gspawnColorSheeps = " + e.toString());
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
			materie = mappingTables.MatchMat(name);
			
		}
		

}
