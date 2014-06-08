package com.gmail.oaplugins;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigurationLoader {

	public static String mysql_hostname = "hostname";
	public static String mysql_port = "80";
	public static String mysql_database = "database";
	public static String mysql_username = "username";
	public static String mysql_password = "password";
	public static String mysql_tablename = "PetBukkit";
    
	public static void load(Plugin plugin) {
        FileConfiguration conf = plugin.getConfig();
        for (Field field : ConfigurationLoader.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())) {
                String path = field.getName().replaceAll("_", ".");
                try {
                    if (conf.isSet(path)) {
                        field.set(null, conf.get(path));
                    } else {
                        conf.set(path, field.get(null));
                    }
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        plugin.saveConfig();
    }
}
