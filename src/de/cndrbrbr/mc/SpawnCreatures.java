package de.cndrbrbr.mc;


import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Sheep;

public class SpawnCreatures {
	
	
String[]texte= {"Jonas","Leon","Luca","Finn","Noah","Liam","Elias","Paul","Julian","Milan","Mia","Emma","Leonie","Sophie","Emilia","Lina","Marie","Anna","Mila","Ella","Lars","Lucius","Sebastian","Lucas","Maarten","Linus","Sabine","Britta","Lotta"};
	
	
public void SpawnColorSheeps(Location loc, int numberIn, String color )	{
	
	if ((numberIn == 0)||(numberIn < 2)|| (numberIn > 100)) return;
	int number = numberIn /2;
	boolean doJeb = true;
	DyeColor dcol  = null;
	
	if (color.equalsIgnoreCase("all")) {
		doJeb = true;
	}
	else {
		 dcol = mappingTables.MatchDyeColor (color);	
		 doJeb = false;
	}
		
	
	int x = loc.getBlockX();
	int z = loc.getBlockZ();
	
	for (int i = 0; i < number; i++) {
		for (int j = 0; j < number; j++) {
			Sheep shawn = loc.getWorld().spawn(loc, Sheep.class);
			if (doJeb) {
				shawn.setCustomName("jeb_");
				shawn.setCustomNameVisible(false);
			}
			else {
				shawn.setColor(dcol);
				shawn.setCustomName(getRandomName());
				shawn.setCustomNameVisible(true);
			}
			loc.setX(x +i);
			loc.setZ(z +j);

		}
	}
}

private String getRandomName()
{
	int number = new Random().nextInt(texte.length);
	return (texte[number]);
}



}
