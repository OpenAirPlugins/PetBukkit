package com.gmail.oaplugins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ClearReg implements Listener{

	@EventHandler
	public void clearReg(EntityDeathEvent e){
		Player p = MySQLUtils.getPlayer(e.getEntity().getUniqueId());
		MySQLUtils.clearReg(e.getEntity().getUniqueId(), p);
	}
	
}
