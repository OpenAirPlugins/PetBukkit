package com.gmail.oaplugins.ai;

import net.minecraft.server.v1_7_R3.Entity;
import net.minecraft.server.v1_7_R3.EntityCreature;
import net.minecraft.server.v1_7_R3.PathfinderGoal;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;

public class Pathfinder extends PathfinderGoal{
	float speed;
	private EntityCreature entitycreature;
	Location loc;
	
	public Pathfinder(EntityCreature entitycreature, float speed, Location loc){
		this.speed = speed;
		this.entitycreature = entitycreature;
		this.loc = loc;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean a() {
		Entity ep = null;
		String[] s = this.entitycreature.getCustomName().split("");
		if(Bukkit.getServer().getPlayer(s[0]) != null){
			ep = ((CraftPlayer)Bukkit.getServer().getPlayer(s[0])).getHandle();
		}
		
		if(ep == null){
			return false;
		}else if (this.entitycreature.aM() >= 100) {
				return false;
		} else if (this.entitycreature.aH().nextInt(120) != 0) {
			return true;
		} else if (!this.entitycreature.isAlive()){
			return false;
		}else if (loc == null){
			return false;
		}
		else {
			return false;
		}
	}
	 
	@SuppressWarnings("deprecation")
	@Override
	public boolean b(){
		Entity ep = null;
		String[] s = this.entitycreature.getCustomName().split("");
		if((CraftPlayer)Bukkit.getServer().getPlayer(s[0]) != null){
			ep = ((CraftPlayer)Bukkit.getServer().getPlayer(s[0])).getHandle();
		}
		if(ep == null){
			return false;
		} else if (this.entitycreature.passenger != null){
			return false;
		} else if(this.loc == null){
			return false;
		}else{
			Double dist = this.entitycreature.f(ep);
			return dist >= 9D && dist <= 120D;
		}
	}
	
	@Override
	public void c(){
		this.entitycreature.getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), speed);
	}
}
 