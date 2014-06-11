package com.gmail.oaplugins;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.Navigation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftChicken;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftCow;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftOcelot;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPig;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftSheep;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.gmail.oaplugins.entities.CustomEntityType;
import com.gmail.oaplugins.events.PlayerTameEntityEvent;

public class Tamer implements Listener{
		
	@SuppressWarnings("deprecation")
	@EventHandler
	public void TameEntity(PlayerInteractEntityEvent e) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		Player p = e.getPlayer();
		if(e.getRightClicked() != null){
			if(p.getItemInHand() != null && p.getItemInHand().hasItemMeta()){
				ItemStack its = p.getItemInHand();
				String s = "";
				String name = its.getItemMeta().getDisplayName();
				if(name != null){
					s = name;
				}else{
					return;
				}

				switch(s){
				case "Chicken Food":
					if(e.getRightClicked().getType() == EntityType.CHICKEN){
						if(p.hasPermission("tierone.chicken")){
							if(!MySQLUtils.hasPet(p)){
								World world = e.getRightClicked().getWorld();
								Location l = e.getRightClicked().getLocation();
								CraftWorld w = (CraftWorld)l.getWorld();
								e.getRightClicked().remove();
								UUID uuid = PetBukkit.pb.spawnCustomEntity(CustomEntityType.CHICKEN, p.getName(), l, w.getHandle());
								Entity ent = getFromUUID(uuid, world);
								Location loc = p.getLocation();
								EntityInsentient ei = ((CraftChicken)ent).getHandle();
								Navigation n = ei.getNavigation();
								n.a(loc.getX(), loc.getY(), loc.getZ(), loc.distanceSquared(p.getLocation()));
								((Damageable)ent).setMaxHealth(20D);
								((Damageable)ent).setHealth(20D);
								Bukkit.getServer().getPluginManager().callEvent(new PlayerTameEntityEvent(p, ent));
								p.setItemInHand(new ItemStack(Material.AIR, 1));
								p.updateInventory();
						}
					}else{
						p.sendMessage(ChatColor.RED + "You can't tame this entity!");
					}		
				}
					break;
				case "Apple":
					if(MySQLUtils.isProperPlayer(e.getRightClicked().getUniqueId(), p.getUniqueId())){
						Damageable ent = (Damageable)e.getRightClicked();
						ent.setHealth(ent.getHealth() + 5D);
					}
					break;
	
					case "Cow Food":
						if(e.getRightClicked().getType() == EntityType.COW){
							if(p.hasPermission("tierone.cow")){
								if(!MySQLUtils.hasPet(p)){
									World world = e.getRightClicked().getWorld();
									Location l = e.getRightClicked().getLocation();
									CraftWorld w = (CraftWorld)l.getWorld();
									e.getRightClicked().remove(); 
									UUID uuid = PetBukkit.pb.spawnCustomEntity(CustomEntityType.COW, p.getName(), l, w.getHandle());
									Entity ent = getFromUUID(uuid, world);
									Location loc = p.getLocation();
									EntityInsentient ei = ((CraftCow)ent).getHandle();
									Navigation n = ei.getNavigation();
									n.a(loc.getX(), loc.getY(), loc.getZ(), loc.distanceSquared(p.getLocation()));
									((Damageable)ent).setMaxHealth(20D);
									((Damageable)ent).setHealth(20D);
									Bukkit.getServer().getPluginManager().callEvent(new PlayerTameEntityEvent(p, ent));
									p.setItemInHand(new ItemStack(Material.AIR, 1));
									p.updateInventory();
							}
						}else{
							p.sendMessage(ChatColor.RED + "You can't tame this entity!");
						}	
					}
						break;
					case "Milk":
						if(MySQLUtils.isProperPlayer(e.getRightClicked().getUniqueId(), p.getUniqueId())){
							Damageable ent = (Damageable)e.getRightClicked();
							ent.setHealth(ent.getHealth() + 5D);
						}
						break;
						
					case "Ocelot Food":
						if(e.getRightClicked().getType() == EntityType.OCELOT){
							if(p.hasPermission("tierone.ocelot")){
								if(!MySQLUtils.hasPet(p)){
									World world = e.getRightClicked().getWorld();
									Location l = e.getRightClicked().getLocation();
									CraftWorld w = (CraftWorld)l.getWorld();
									e.getRightClicked().remove();
									UUID uuid = PetBukkit.pb.spawnCustomEntity(CustomEntityType.OCELOT, p.getName(), l, w.getHandle());
									Entity ent = getFromUUID(uuid, world);
									Location loc = p.getLocation();
									EntityInsentient ei = ((CraftOcelot)ent).getHandle();
									Navigation n = ei.getNavigation();
									n.a(loc.getX(), loc.getY(), loc.getZ(), loc.distanceSquared(p.getLocation()));
									((Damageable)ent).setMaxHealth(20D);
									((Damageable)ent).setHealth(20D);
									Bukkit.getServer().getPluginManager().callEvent(new PlayerTameEntityEvent(p, ent));
									p.setItemInHand(new ItemStack(Material.AIR, 1));
									p.updateInventory();
							}
						}else{
							p.sendMessage(ChatColor.RED + "You can't tame this entity!");
						}		
					}
						break;
					case "Cooked Fish":
						if(MySQLUtils.isProperPlayer(e.getRightClicked().getUniqueId(), p.getUniqueId())){
							Damageable ent = (Damageable)e.getRightClicked();
							ent.setHealth(ent.getHealth() + 5D);
						}
						break;
						
					case "Pig Food":
						if(e.getRightClicked().getType() == EntityType.PIG){
							if(p.hasPermission("tierone.pig")){
								if(!MySQLUtils.hasPet(p)){
									World world = e.getRightClicked().getWorld();
									Location l = e.getRightClicked().getLocation();
									CraftWorld w = (CraftWorld)l.getWorld();
									e.getRightClicked().remove();
									UUID uuid = PetBukkit.pb.spawnCustomEntity(CustomEntityType.PIG, p.getName(), l, w.getHandle());
									Entity ent = getFromUUID(uuid, world);
									Location loc = p.getLocation();
									EntityInsentient ei = ((CraftPig)ent).getHandle();
									Navigation n = ei.getNavigation();
									n.a(loc.getX(), loc.getY(), loc.getZ(), loc.distanceSquared(p.getLocation()));
									((Damageable)ent).setMaxHealth(20D);
									((Damageable)ent).setHealth(20D);
									Bukkit.getServer().getPluginManager().callEvent(new PlayerTameEntityEvent(p, ent));
									p.setItemInHand(new ItemStack(Material.AIR, 1));
									p.updateInventory();
							}
						}else{
							p.sendMessage(ChatColor.RED + "You can't tame this entity!");
						}		
					}
						break;
					case "Carrot":
						if(MySQLUtils.isProperPlayer(e.getRightClicked().getUniqueId(), p.getUniqueId())){
							Damageable ent = (Damageable)e.getRightClicked();
							ent.setHealth(ent.getHealth() + 5D);
						}
						break;
						
					case "Sheep Food":
						if(e.getRightClicked().getType() == EntityType.SHEEP){
							if(p.hasPermission("tierone.sheep")){
								if(!MySQLUtils.hasPet(p)){
									World world = e.getRightClicked().getWorld();
									Location l = e.getRightClicked().getLocation();
									CraftWorld w = (CraftWorld)l.getWorld();
									e.getRightClicked().remove();
									UUID uuid = PetBukkit.pb.spawnCustomEntity(CustomEntityType.SHEEP, p.getName(), l, w.getHandle());
									Entity ent = getFromUUID(uuid, world);
									Location loc = p.getLocation();
									EntityInsentient ei = ((CraftSheep)ent).getHandle();
									Navigation n = ei.getNavigation();
									n.a(loc.getX(), loc.getY(), loc.getZ(), loc.distanceSquared(p.getLocation()));
									((Damageable)ent).setMaxHealth(20D);
									((Damageable)ent).setHealth(20D);
									Bukkit.getServer().getPluginManager().callEvent(new PlayerTameEntityEvent(p, ent));
									p.setItemInHand(new ItemStack(Material.AIR, 1));
									p.updateInventory();
							}
						}else{
							p.sendMessage(ChatColor.RED + "You can't tame this entity!");
						}		
					}
						break;
					case "Cocoa Beans":
						if(MySQLUtils.isProperPlayer(e.getRightClicked().getUniqueId(), p.getUniqueId())){
							Damageable ent = (Damageable)e.getRightClicked();
							ent.setHealth(ent.getHealth() + 5D);
						}
						break;
				}
				return;
			}
			
			if(MySQLUtils.isProperPlayer(e.getRightClicked().getUniqueId(), e.getPlayer().getUniqueId())){
				e.getRightClicked().setPassenger(e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void setValues(PlayerTameEntityEvent e){
		try {
			PetBukkit.pb.ps.setString(1, e.getPlayer().getUniqueId().toString());
			PetBukkit.pb.ps.setString(2, e.getTamed().getUniqueId().toString());
			PetBukkit.pb.ps.executeUpdate();
		} catch (SQLException e1) {
			
			PetBukkit.pb.getLogger().severe(e1.getMessage());
		}
	}
	
	public Entity getFromUUID(UUID uuid, World w){
		Entity e = null;
		for(Entity ent : w.getEntities()){
			if(ent.getUniqueId().equals(uuid)){
				e = ent;
			}
		}
		return e;
	}
}