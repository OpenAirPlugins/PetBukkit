package com.gmail.oaplugins;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_7_R3.EntityChicken;
import net.minecraft.server.v1_7_R3.EntityCow;
import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.EntityOcelot;
import net.minecraft.server.v1_7_R3.EntityPig;
import net.minecraft.server.v1_7_R3.EntitySheep;
import net.minecraft.server.v1_7_R3.EntityTypes;

import org.bukkit.entity.EntityType;

public enum CustomEntityType {

	COW("Cow", 92, EntityType.COW, EntityCow.class, CustomEntityCow.class),
	PIG("Pig", 90, EntityType.PIG, EntityPig.class, CustomEntityPig.class),
	SHEEP("Sheep", 91, EntityType.SHEEP, EntitySheep.class, CustomEntitySheep.class),
	OCELOT("Ocelot", 98, EntityType.OCELOT, EntityOcelot.class, CustomEntityOcelot.class),
	CHICKEN("Chicken", 93, EntityType.CHICKEN, EntityChicken.class, CustomEntityChicken.class);
	
	private String name; 
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;
 
    private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }
 
    public String getName(){
        return this.name;
    }
 
    public int getID(){
        return this.id;
    }
 
    public EntityType getEntityType(){
        return this.entityType;
    }
 
    public Class<? extends EntityInsentient> getNMSClass(){
        return this.nmsClass;
    }
 
    public Class<? extends EntityInsentient> getCustomClass(){
        return this.customClass;
    }
    
    public static Class<? extends EntityInsentient> getCustomClassFromEntityType(EntityType e){
    	for(CustomEntityType ent : values()){
    		if(ent.entityType == e){
    			return ent.getCustomClass();
    		}
    	}
    	return null;
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
