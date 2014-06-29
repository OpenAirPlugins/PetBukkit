package com.gmail.oaplugins;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.EntityTypes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.oaplugins.Updater.UpdateResult;
import com.gmail.oaplugins.Updater.UpdateType;
import com.gmail.oaplugins.entities.CustomEntityChicken;
import com.gmail.oaplugins.entities.CustomEntityCow;
import com.gmail.oaplugins.entities.CustomEntityOcelot;
import com.gmail.oaplugins.entities.CustomEntityPig;
import com.gmail.oaplugins.entities.CustomEntitySheep;
import com.gmail.oaplugins.entities.CustomEntityType;

public class PetBukkit extends JavaPlugin {

	static PetBukkit pb;
	
	private FoodRecipes fr;
	private Tamer tamer;
	private ClearReg cr;
	MySQLUtils utils;
	
	boolean a = true;
	String b = "";
	String t = "";
	
	MySQL sql = null;
	PreparedStatement ps;	
	Connection c = null;
	Statement s = null;
	
	public void onEnable(){
		pb = this;
		tamer = new Tamer();
		utils = new MySQLUtils();
		fr = new FoodRecipes();
		cr = new ClearReg();
		
		fr.chickenFood();
		fr.cowFood();
		fr.ocelotFood();
		fr.pigFood();
		fr.sheepFood();
		
		this.registerEntity("Chicken", 93, CustomEntityChicken.class);
		this.registerEntity("Cow", 92, CustomEntityCow.class);
		this.registerEntity("Ocelot", 98, CustomEntityOcelot.class);
		this.registerEntity("Pig", 90, CustomEntityPig.class);
		this.registerEntity("Sheep", 91, CustomEntitySheep.class);
		
		ConfigurationLoader.load(this);

		getLogger().info("Initializing MySQL...");
		String host = getConfig().getString("mysql.hostname");
		String port = getConfig().getString("mysql.port");
		String database = getConfig().getString("mysql.database");
		String user = getConfig().getString("mysql.username");
		String pass = getConfig().getString("mysql.password");
		t = getConfig().getString("mysql.tablename"); 
		sql = new MySQL(this, host, port, database, user, pass);
		c = sql.openConnection();
		String sqls = "CREATE TABLE IF NOT EXISTS " + t + " (`pUUID` TEXT NOT NULL, `eUUID` TEXT NOT NULL)"; 
		try {
			s = sql.openConnection().createStatement();
			s.execute(sqls);
			ps = c.prepareStatement("INSERT INTO `" + t + "`(pUUID, eUUID) VALUES (?, ?);");
		} catch (SQLException e) {
			b = e.getMessage();
			a = false;
		}
		if(!a){
			getLogger().severe("ERROR INITIALIZING MYSQL: " + b + " ENSURE YOUR CONFIG.YML IS PROPERLY CONFIGURED.");
		}else{
			getLogger().info("MySQL initialized!");
		}
		
		Bukkit.getServer().getPluginManager().registerEvents(tamer, this);
		Bukkit.getServer().getPluginManager().registerEvents(cr, this);
		
		if(getConfig().getBoolean("Update.Enabled")){
			Updater updater = new Updater(this, 80883, this.getFile(), UpdateType.NO_DOWNLOAD, true);
			if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
		    	getLogger().info("A new version of PetBukkit is available! " + updater.getLatestName());
			}
		}else{
			getLogger().warning("Update checker disabled!");
		}
		
		getLogger().info("PetBukkit has been enabled!");
	}

	public void onDisable(){
		getLogger().warning("PetBukkit has been disabled!");
	}
	
	/**
	 * Spawn custom entities, 1 at a time
	 * @param entity - Custom entity to spawn
	 * @param loc - Location to spawn it at
	 * @param world - World to spawn it in
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public UUID spawnCustomEntity(CustomEntityType entity, String name, Location loc, net.minecraft.server.v1_7_R3.World world) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		EntityInsentient e = (EntityInsentient)(entity.getCustomClass().getDeclaredConstructor(net.minecraft.server.v1_7_R3.World.class).newInstance(world));
        e.setPosition(loc.getX(), loc.getY(), loc.getZ());
        
        String type = entity.getEntityType().toString().toLowerCase();
        String s = type.substring(0, 1).toUpperCase() + type.substring(1);

        e.setCustomName(name + "'s " + s);
        e.setCustomNameVisible(true);

        world.addEntity(e);

        return e.getUniqueID();
    }
	
    @SuppressWarnings("unchecked")
    public void registerEntity(String name, int id, Class<? extends EntityInsentient> customClass) {
        try {
 
            List<Map<?, ?>> dataMaps = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMaps.add((Map<?, ?>) f.get(null));
                }
            }
 
            ((Map<Class<? extends EntityInsentient>, String>) dataMaps.get(1)).put(customClass, name);
            ((Map<Class<? extends EntityInsentient>, Integer>) dataMaps.get(3)).put(customClass, id);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
