// (c)cndrbrbr 2018 
package de.cndrbrbr.mc;

import java.util.logging.Logger;

import org.bukkit.DyeColor;
import org.bukkit.Material;

public class mappingTables {
	
	public mappingTables(Logger log) {
		super();
		this.log = log;
	}

	Logger log;

	
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


	static public DyeColor MatCol2WoolCol (String name)
	{
		DyeColor col = DyeColor.WHITE;
	
		if (name == null) return col;
		switch (name) {
//---------------------------------------------------------------
			case "Blue":  				
			case "SteelBlue":  			
			case "SlateBlue":  			
			case "RoyalBlue":  			
			case "PowderBlue":  		
			case "Navy":  				
			case "MidnightBlue":  		
			case "MediumSlateBlue":  	
			case "MediumBlue":  		
			case "MediumAquaMarine":  	
			case "Indigo":  			
			case "DeepSkyBlue":  		
			case "DarkSlateBlue":  		
			case "DarkBlue":  			
			case "AliceBlue":  			
			case "Aqua":  				
			case "Aquamarine":  		
			case "Azure":  				
			case "BlueViolet":  		
			case "CadetBlue":  			
			case "Chartreuse":  		
			case "CornflowerBlue":  	
			case "Cornsilk":  			
			case "Lavender":  			
			case "LavenderBlush":  		col =  DyeColor.BLUE; break;
//---------------------------------------------------------------
			case "LightCyan":  			
			case "Cyan":  				
			case "DarkCyan":  			col =  DyeColor.CYAN; break;	
//---------------------------------------------------------------

			
			case "LightSkyBlue":  		
			case "SkyBlue":  			
			case "LightSteelBlue":  	
			case "LightBlue":  			col =  DyeColor.LIGHT_BLUE; break;

//---------------------------------------------------------------
			case "Brown":  		
			case "Tan":  		
			case "Sienna":  	
			case "SandyBrown":  
			case "SaddleBrown": 
			case "RosyBrown":  	
			case "Moccasin":  	
			case "Chocolate":  	
			case "BurlyWood":  	col =  DyeColor.BROWN ; break;
//---------------------------------------------------------------
			case "Gold": 
			case "GoldenRod":  col =  DyeColor.YELLOW ; break;
//---------------------------------------------------------------
			case "Gray":  			
			case "SlateGray":  		
			case "LightSlateGray":  
			case "DimGray":  		
			case "DarkSlateGray":  	
			case "DarkGray":  		col =  DyeColor.GRAY ; break;
//---------------------------------------------------------------
			case "Silver": 			
			case "LightGray":  		col =  DyeColor.SILVER ; break;
//---------------------------------------------------------------
			
			case "Green":  			
			case "SpringGreen":  	
			case "SeaGreen":  		
			case "PaleGreen":  		
			case "OliveDrab": 		
			case "Olive":  			
			case "MintCream":  		
			case "MediumSpringGreen":  
			case "MediumSeaGreen":  
			case "Lime":  			
			case "LimeGreen":  		
			case "LightGreen":  	
			case "LawnGreen":  		
			case "Khaki":  			
			case "ForestGreen":  	
			case "LightSeaGreen":  
			case "DarkSeaGreen":  
			case "DarkGreen":  
			case "DarkKhaki":  
			case "DarkOliveGreen":  col =  DyeColor.GREEN; break;
//---------------------------------------------------------------
			case "Orange":  
			case "Salmon": 
			case "OrangeRed":  
			case "PaleGoldenRod":  
			case "LightSalmon":
			case "DarkOrange": 
			case "LightCoral": 
			case "Coral":  			col =  DyeColor.ORANGE ; break;
//---------------------------------------------------------------
			case "Red":  	
			case "Tomato":  
			case "MistyRose":  		
			case "IndianRed": 		
			case "Fuchsia":		 	
			case "Crimson": 		
			case "FireBrick": 		
			case "DarkRed": 		col =  DyeColor.RED ; break;
//---------------------------------------------------------------
			case "DeepPink":		
			case "HotPink": 		
			case "LightPink": 		
			case "Pink":  			col =  DyeColor.PINK ; break;
//---------------------------------------------------------------
			case "DarkMagenta": 	
			case "Magenta":  		col =  DyeColor.MAGENTA ; break;
//---------------------------------------------------------------
			case "MediumPurple":  	
			case "Purple":  		col =  DyeColor.PURPLE ; break;

//---------------------------------------------------------------
			
			case "White":  			
			case "WhiteSmoke":  	
			case "Snow":  			
			case "Linen":  			
			case "Ivory":  			
			case "GhostWhite":  	
			case "FloralWhite": 	
			case "AntiqueWhite":  	
			case "Beige":  			
			case "BlanchedAlmond": 	
			case "Bisque":  		col =  DyeColor.WHITE ; break;
//---------------------------------------------------------------
			case "Yellow":  			
			case "YellowGreen": 		
			case "LightYellow": 		
			case "LightGoldenRodYellow":
			case "LemonChiffon": 		
			case "GreenYellow": 		
			case "HoneyDew":  col =  DyeColor.YELLOW ; break;
//---------------------------------------------------------------
			case "Black":  col =  DyeColor.BLACK  ; break;

			
			default: col =  DyeColor.WHITE ;
		}			
		return col;

	}

}
