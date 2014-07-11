//Main tower class. Extend from this.
//Some tower attributes: radius of attack, strength, attack speed, cost, level.
package com.leepresswood.neondefense.entities.towers;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leepresswood.neondefense.entities.Field;

public abstract class Tower
{	
	protected final int MAX_LEVEL = 5;
	
	protected int id;
	
	protected float radius;						//In terms of blocks.
	protected float strength;					//Damage removed from enemy
	protected float attack_speed;				//Attacks per second
	protected int cost;							//Gold cost
	protected int level = 1;					//Current upgrade level. Should always start at one
	
	protected float upgrade_multiplier;		//The cost per level increase
	protected float upgrade_radius;			//How much radius changes
	protected float upgrade_strength;		//How much strength changes
	protected float upgrade_attack_speed;	//How much attack speed changes
	
	protected String base_texture_file;		//The file for the base sprite's texture.
	protected Sprite base_sprite;				//The base sprite of the tower
	
	public Tower(int id, HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		this.id = id;
		
		//Get the properties
		this.radius = Float.parseFloat(properties.get("radius"));
		this.strength = Float.parseFloat(properties.get("strength"));
		this.attack_speed = Float.parseFloat(properties.get("attack_speed"));
		this.cost = Integer.parseInt(properties.get("cost"));
		
		//Get the upgrades
		this.upgrade_multiplier = Float.parseFloat(upgrades.get("multiplier"));
		this.upgrade_radius = Float.parseFloat(upgrades.get("radius"));
		this.upgrade_strength = Float.parseFloat(upgrades.get("strength"));
		this.upgrade_attack_speed = Float.parseFloat(upgrades.get("attack_speed"));
	
		//Set the base image.
		this.base_texture_file = properties.get("file");
		this.base_sprite = new Sprite(new Texture(Gdx.files.internal(this.base_texture_file)));
	}
	
	public float getRadius()
	{
		return radius;
	}

	public float getStrength()
	{
		return strength;
	}
	
	public float getAttack_speed()
	{
		return attack_speed;
	}
	
	public float getCost()
	{
		return cost;
	}
	
	public float getLevel()
	{
		return level;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void update(float delta, Field field)
	{
		
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		
	}

	public int levelUp(int money)
	{//A level up has been requested. 
		//Level cannot be above the maximum.
		if(this.level < this.MAX_LEVEL)
		{
			//Formula: Cost to level up = Initial tower cost * Multiplier * Current level
			int cost = (int) (this.cost * this.upgrade_multiplier * this.level);
			
			//Do we have the cash for this level up?
			if(money >= cost)
			{
				//Raise level and skills.
				this.level++;
				this.radius += this.upgrade_radius;
				this.strength += this.upgrade_strength;
				this.attack_speed += this.upgrade_attack_speed;
				
				//Pay for it.
				money -= cost;
			}
		}		
		
		//Return the money amount the player has now.
		return money;	
	}

	public Sprite getSprite()
	{
		return this.base_sprite;		
	}
}
