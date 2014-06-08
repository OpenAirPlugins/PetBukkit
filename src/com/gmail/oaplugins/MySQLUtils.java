package com.gmail.oaplugins;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MySQLUtils {
		
	/**
	 * Checks if UUID of entity belongs to the UUID of the player
	 * @param eUUID - UUID of the entity to try and match player with
	 * @param pUUId - UUID of the player to try and match entity with
	 * @return Whether or not it matches
	 */
	public static boolean isProperPlayer(UUID eUUID, UUID pUUID){
		try {
			String t = PetBukkit.pb.t;
			PreparedStatement p = PetBukkit.pb.c.prepareStatement("SELECT * FROM `" + t + "` WHERE pUUID = ?;");
			p.setString(1, pUUID.toString());
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				if(rs.getString("pUUID") == null || rs.getString("eUUID") == null){
					return false;
				}else{
					String eString = rs.getString("eUUID");
					if(eUUID.toString().equals(eString)){
						return true;
					}else{
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void clearReg(UUID eUUID, Player pl){
		try{
			String t = PetBukkit.pb.t;
			PreparedStatement p = PetBukkit.pb.c.prepareStatement("DELETE FROM `" + t + "` WHERE eUUID = ?;");
			p.setString(1, eUUID.toString());
			int del = p.executeUpdate();
			if(del == 1){
				pl.sendMessage(ChatColor.BLUE + "Your pet has died! :(");
			}else{}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Player getPlayer(UUID eUUID){
		try{
			String t = PetBukkit.pb.t;
			PreparedStatement p = PetBukkit.pb.c.prepareStatement("SELECT * FROM `" + t + "` WHERE eUUID = ?;");
			p.setString(1, eUUID.toString());
			
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				return Bukkit.getServer().getPlayer(UUID.fromString(rs.getString("pUUID")));
			}
		}catch(SQLException e){
			//TODO Handle non-existent UUID
		}
		return null;
	}
	
	public static boolean hasPet(Player p){
		String t = PetBukkit.pb.t;
		try {
			PreparedStatement ps = PetBukkit.pb.c.prepareStatement("SELECT * FROM `" + t + "` WHERE pUUID = ?;");
			ps.setString(1, p.getUniqueId().toString());
			final ResultSet rs = ps.executeQuery();
			if(rs.next()){
				p.sendMessage(ChatColor.RED + "You already have a pet!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}