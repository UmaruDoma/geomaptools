// (c)cndrbrbr 2018 
package de.cndrbrbr.mc;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Material;

public class playersList {

	private Logger log = null;
	private Map<String, internalCommandState> playerz = new HashMap<String, internalCommandState>();

	public  playersList (Logger alog)
	{
		log = alog;
	}
	
	public void CommandOn (String cmd, String[] args, String player) 
	{
		CommandOff (player); 
		internalCommandState state = new internalCommandState(log);
		state.CommandOn(cmd,args,player);
		playerz.put(player, state);
	}
	
	public internalCommandState GetState(String player)
	{
		internalCommandState state;
		if (playerz.containsKey(player)) {
			state = playerz.get(player);
			return state;
		} else {
			return null;
		}
	}
	public void CommandOff (String player) 
	{
		if (playerz.containsKey(player)) {
			playerz.remove(player);		
		} 		
	}
	
	boolean IsCommandActive (String player, String command)
	{
		
		internalCommandState state;
		 //log.info("internalCommandState "+ player + ", player "+ command );

		if (playerz.containsKey(player)) {
			state = playerz.get(player);
			return state.IsCommandEq(command);
		} else {
			return false;
		}
	}
	public Material GetMaterial (String player)
	{	
		internalCommandState state;
		if (playerz.containsKey(player)) {
			state = playerz.get(player);
			return state.GetMateriel();
		} else {
			return null;
		}
	}
	boolean IsMaterielRedstone(String player)
	{
		Material mat = GetMaterial (player);
		return (mat.equals(Material.REDSTONE_BLOCK));		
	}
}
