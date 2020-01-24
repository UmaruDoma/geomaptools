package de.cndrbrbr.mc;


import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.entity.Sheep;

public class SpawnCreatures {
	
	
public void SpawnColorSheeps(Location loc, int numberIn )	{
	
	if ((numberIn == 0)||(numberIn < 2)) return;
	int number = numberIn /2;
	
	int x = loc.getBlockX();
	int z = loc.getBlockZ();
	
	for (int i = 0; i < number; i++) {
		for (int j = 0; j < number; j++) {
			Sheep shawn = loc.getWorld().spawn(loc, Sheep.class);
			shawn.setCustomName("jeb_");
			shawn.setCustomNameVisible(true);
			loc.setX(x +i);
			loc.setZ(z +j);

		}
	}
}


}
