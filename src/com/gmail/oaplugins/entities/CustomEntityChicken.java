package com.gmail.oaplugins.entities;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R3.EntityChicken;
import net.minecraft.server.v1_7_R3.EntityHuman;
import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.EntityLiving;
import net.minecraft.server.v1_7_R3.Items;
import net.minecraft.server.v1_7_R3.PathfinderGoalBreed;
import net.minecraft.server.v1_7_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R3.PathfinderGoalFollowParent;
import net.minecraft.server.v1_7_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_7_R3.PathfinderGoalPanic;
import net.minecraft.server.v1_7_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_7_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_7_R3.PathfinderGoalTempt;
import net.minecraft.server.v1_7_R3.World;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R3.util.UnsafeList;
import org.bukkit.entity.Player;

import com.gmail.oaplugins.ai.Pathfinder;

public class CustomEntityChicken extends EntityChicken{

	protected boolean f;
	
	public CustomEntityChicken(World world){
		super(world);
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public void e(float sideMove, float forMove) {
		Player p = null;
		if(Bukkit.getServer().getPlayer(this.getCustomName().replace("'s Chicken", "")) != null){
			p = Bukkit.getServer().getPlayer(this.getCustomName().replace("'s Chicken", ""));
			f = true;
		}
		if (this.passenger == null || !(this.passenger instanceof EntityHuman)) {
	        super.e(sideMove, forMove);
	        this.W = 0.5F;    
	        
	        try{
	        	 
	            Field gsa = net.minecraft.server.v1_7_R3.PathfinderGoalSelector.class.getDeclaredField("b");
	            gsa.setAccessible(true);
	 
	            gsa.set(this.goalSelector, new UnsafeList());
	 
	        } catch (SecurityException e) {
	            e.printStackTrace();
	        } catch (NoSuchFieldException e) {
	            e.printStackTrace();
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	 
	        this.goalSelector.a(0, new PathfinderGoalFloat(this));
	        try{
	        	this.goalSelector.a(1, new Pathfinder(this, 1.2F, p.getLocation()));
	        }catch(NullPointerException e){
	        	return;
	        }
	        this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.4D));
	        this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
	        this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, Items.SEEDS, false));
	        this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.1D));
	        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 1.0D));
	        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
	        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
	        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
	        return;
	    }
		
		this.lastYaw = this.yaw = this.passenger.yaw;
	    this.pitch = this.passenger.pitch * 0.5F;
	 
	    this.b(this.yaw, this.pitch);
	    this.aO = this.aM = this.yaw;
	    
	    this.W = 1.0F;
	    
	    sideMove = ((EntityLiving) this.passenger).bd * 0.5F;
	    forMove = ((EntityLiving) this.passenger).be * 0.6F;
	 
	    if (forMove <= 0.0F) {
	        forMove *= 0.25F;
	    }
	    sideMove *= 0.75F;
	 
	    float speed = 0.35F;
	    this.i(speed);
	    super.e(sideMove, forMove);
	    
	    try {
	    	Field jump = null;
			jump = EntityLiving.class.getDeclaredField("bc");
			jump.setAccessible(true);
			 
			if (jump != null && this.onGround) {
			    try {
			        if (jump.getBoolean(this.passenger)) {
			            double jumpHeight = 0.5D;
			            this.motY = jumpHeight;
			        }
			    } catch (IllegalAccessException e) {
			        e.printStackTrace();
			    }
			}
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	}
}