package com.gmail.oaplugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class FoodRecipes {
	/* 
	     - Recipe list - 
	 	Chicken = Seeds, Bowl, Wheat
	 	Cow = MilkBucket, Bowl, Wheat
	 	Ocelot = CookedFish, Bowl, wheat
	 	Pig = Carrot, Bowl, Wheat
	 	Sheep = CocoaBeans, Bowl, Wheat
	 */
	
	//Tier 1 Recipes
	public void chickenFood(){
		ItemStack chickenFood = new ItemStack(Material.MUSHROOM_SOUP); //Create a new item that the recipe will return
		ItemMeta meta = chickenFood.getItemMeta(); //Get the item meta for the item (name, description, etc.)
		meta.setDisplayName("Chicken Food"); //Set the item's name in inventory
		List<String> lore = new ArrayList<String>(); //Create a new ArrayList of Strings that will be used for the lore
		lore.add("Right-click on a chicken to tame it!"); //add a string to the lore
		meta.setLore(lore); //Set the meta's lore to the ArrayList
		
		chickenFood.setItemMeta(meta); //Update the item's meta to the one with the changed name and such
		
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(chickenFood)); //Create a new recipe that will return the itemstack up there^
		recipe.shape(new String[] { " X ", " Y ", " Z " }); //Set the shape for the recipe ("TOP", "MID", "BTM") (spaces = blank)
		recipe.setIngredient('X', Material.SEEDS); //Set the value of 'X' to Seeds
		recipe.setIngredient('Y', Material.BOWL); //Set the value of 'Y' to Bowl
		recipe.setIngredient('Z', Material.WHEAT); //Set the value of 'Z' to What
		PetBukkit.pb.getServer().addRecipe(recipe); //Add the recipe to the server
		//make sure to call in main class with'fr.<functionName>'
	}
	
	public void cowFood(){
		ItemStack cowFood = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta meta = cowFood.getItemMeta();
		meta.setDisplayName("Cow Food");
		List<String> lore = new ArrayList<String>();
		lore.add("Right-click on a cow to tame it!");
		meta.setLore(lore);
		
		cowFood.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(cowFood));
		recipe.shape(new String[] { " X ", " Y ", " Z " });
		recipe.setIngredient('X', Material.MILK_BUCKET);
		recipe.setIngredient('Y', Material.BOWL);
		recipe.setIngredient('Z', Material.WHEAT);
		PetBukkit.pb.getServer().addRecipe(recipe);
	}
	
	public void ocelotFood(){
		ItemStack ocelotFood = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta meta = ocelotFood.getItemMeta();
		meta.setDisplayName("Ocelot Food");
		List<String> lore = new ArrayList<String>();
		lore.add("Right-click on an Ocelot to tame it!");
		meta.setLore(lore);
		
		ocelotFood.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(ocelotFood));
		recipe.shape(new String[] { " X ", " Y ", " Z " });
		recipe.setIngredient('X', Material.COOKED_FISH);
		recipe.setIngredient('Y', Material.BOWL);
		recipe.setIngredient('Z', Material.WHEAT);
		PetBukkit.pb.getServer().addRecipe(recipe);
	}
	
	public void pigFood(){
		ItemStack pigFood = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta meta = pigFood.getItemMeta();
		meta.setDisplayName("Pig Food");
		List<String> lore = new ArrayList<String>();
		lore.add("Right-click on a Pig to tame it!");
		meta.setLore(lore);
		
		pigFood.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(pigFood));
		recipe.shape(new String[] { " X ", " Y ", " Z " });
		recipe.setIngredient('X', Material.CARROT_ITEM);
		recipe.setIngredient('Y', Material.BOWL);
		recipe.setIngredient('Z', Material.WHEAT);
		PetBukkit.pb.getServer().addRecipe(recipe);
	}

	@SuppressWarnings("deprecation")
	public void sheepFood(){
		ItemStack sheepFood = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta meta = sheepFood.getItemMeta();
		meta.setDisplayName("Sheep Food");
		List<String> lore = new ArrayList<String>();
		lore.add("Right-click on a Sheep to tame it!");
		meta.setLore(lore);
		
		sheepFood.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(sheepFood));
		recipe.shape(new String[] { " X ", " Y ", " Z " });
		recipe.setIngredient('X', new MaterialData(Material.INK_SACK, (byte)3));
		recipe.setIngredient('Y', Material.BOWL);
		recipe.setIngredient('Z', Material.WHEAT);
		PetBukkit.pb.getServer().addRecipe(recipe);
	}

	
}
