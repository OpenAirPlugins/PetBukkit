package com.gmail.oaplugins;

import net.minecraft.server.v1_7_R3.EntityCreature;
import net.minecraft.server.v1_7_R3.PathfinderGoal;

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
	
	@Override
	public boolean a() {
		if (this.entitycreature.aM() >= 100) {
				return false;
		} else if (this.entitycreature.aH().nextInt(120) != 0) {
			return true;
		} else if (!this.entitycreature.isAlive()){
			return false;
		} else if (this.entitycreature.passenger != null){
			return false;
		} else if (this.entitycreature.f(((CraftPlayer)MySQLUtils.getPlayer(this.entitycreature.getUniqueID())).getHandle()) < (double)(3^2)){
			return false;
		} else {
			return false;
		}
	}
	 
	@Override
	public void c(){
		this.entitycreature.getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), speed);
	}
}
 