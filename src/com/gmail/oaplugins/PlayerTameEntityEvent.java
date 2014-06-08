package com.gmail.oaplugins;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTameEntityEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	
	private Player tamer;
	private Entity tamed;
	
	public PlayerTameEntityEvent(Player tamer, Entity tamed){
		this.tamer = tamer;
		this.tamed = tamed;
	}
	
	public Player getPlayer(){
		return this.tamer;
	}
	
	public Entity getTamed(){
		return this.tamed;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

}
