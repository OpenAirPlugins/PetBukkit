package com.gmail.oaplugins.entities;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R3.EntityChicken;
import net.minecraft.server.v1_7_R3.EntityHuman;
import net.minecraft.server.v1_7_R3.EntityLiving;
import net.minecraft.server.v1_7_R3.EntityOcelot;
import net.minecraft.server.v1_7_R3.Items;
import net.minecraft.server.v1_7_R3.PathfinderGoalBreed;
import net.minecraft.server.v1_7_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R3.PathfinderGoalFollowOwner;
import net.minecraft.server.v1_7_R3.PathfinderGoalJumpOnBlock;
import net.minecraft.server.v1_7_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_7_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_7_R3.PathfinderGoalOcelotAttack;
import net.minecraft.server.v1_7_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_7_R3.PathfinderGoalRandomTargetNonTamed;
import net.minecraft.server.v1_7_R3.PathfinderGoalTempt;
import net.minecraft.server.v1_7_R3.World;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R3.util.UnsafeList;

import com.gmail.oaplugins.ai.Pathfinder;

public class CustomEntityOcelot extends EntityOcelot{
	
	public CustomEntityOcelot(World world){
		super(world);
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public void e(float sideMove, float forMove) {
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
	 
	        this.goalSelector.a(1, new Pathfinder(this, 1.2F, Bukkit.getServer().getPlayer(this.getCustomName().replace("'s Ocelot", "")).getLocation()));
	        this.goalSelector.a(1, new PathfinderGoalFloat(this));
	        this.goalSelector.a(2, this.bp);
	        this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.6D, Items.RAW_FISH, true));
	        this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 5.0F));
	        this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 1.33D));
	        this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3F));
	        this.goalSelector.a(8, new PathfinderGoalOcelotAttack(this));
	        this.goalSelector.a(9, new PathfinderGoalBreed(this, 0.8D));
	        this.goalSelector.a(10, new PathfinderGoalRandomStroll(this, 0.8D));
	        this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
	        this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed(this, EntityChicken.class, 750, false));
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

