package com.gmail.oaplugins;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R3.EntityCow;
import net.minecraft.server.v1_7_R3.EntityHuman;
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

public class CustomEntityCow extends EntityCow{

	public CustomEntityCow(World world){
		super(world);
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void e(float sideMove, float forMove) {
		if (this.passenger == null || !(this.passenger instanceof EntityHuman)) {
	        super.e(sideMove, forMove);
	        this.W = 0.5F;    // Make sure the entity can walk over half slabs, instead of jumping
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
	        this.goalSelector.a(1, new Pathfinder(this, 1.2F, Bukkit.getServer().getPlayer(this.getCustomName().replace("'s Cow", "")).getLocation()));
	        this.goalSelector.a(1, new PathfinderGoalPanic(this, 2.0D));
	        this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
	        this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.25D, Items.WHEAT, false));
	        this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.25D));
	        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 1.0D));
	        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
	        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
	        return;
	    }
		
		this.lastYaw = this.yaw = this.passenger.yaw;
	    this.pitch = this.passenger.pitch * 0.5F;
	 
	    // Set the entity's pitch, yaw, head rotation etc.
	    this.b(this.yaw, this.pitch);
	    this.aO = this.aM = this.yaw;
	    
	    this.W = 1.0F;    // The custom entity will now automatically climb up 1 high blocks
	    
	    sideMove = ((EntityLiving) this.passenger).bd * 0.5F;
	    forMove = ((EntityLiving) this.passenger).be * 0.6F;
	 
	    if (forMove <= 0.0F) {
	        forMove *= 0.25F;    // Make backwards slower
	    }
	    sideMove *= 0.75F;    // Also make sideways slower
	 
	    float speed = 0.35F;    // 0.2 is the default entity speed. I made it slightly faster so that riding is better than walking
	    this.i(speed);    // Apply the speed
	    super.e(sideMove, forMove);
	    
	    try {
	    	Field jump = null; //Jump up jump up and get down
			jump = EntityLiving.class.getDeclaredField("bc");//And jump around
			jump.setAccessible(true);
			 
			if (jump != null && this.onGround) {    // Wouldn't want it jumping while on the ground would we?
			    try {
			        if (jump.getBoolean(this.passenger)) {
			            double jumpHeight = 0.5D;
			            this.motY = jumpHeight;    // Used all the time in NMS for entity jumping
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
