//Main tower class. Extend from this.
//Some tower attributes: radius of attack, strength, attack speed, cost, level.
package com.leepresswood.neondefense.entities.towers;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tower implements TowerInterface
{
	protected static final float XP_TO_NEXT_LEVEL = 100;
	
	protected float radius;						//In terms of blocks.
	protected float strength;					//Damage removed from enemy
	protected float attack_speed;				//Attacks per second
	protected float cost;						//Gold cost
	protected float level = 1;					//Current upgrade level. Should always start at one
	protected float xp = 0;						//Current experience.
	
	protected float upgrade_multiplier;		//The cost per level increase
	protected float upgrade_radius;			//How much radius changes
	protected float upgrade_strength;		//How much strength changes
	protected float upgrade_attack_speed;	//How much attack speed changes
	
	public Tower(HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		//Get the properties
		this.radius = Float.parseFloat(properties.get("radius"));
		this.strength = Float.parseFloat(properties.get("strength"));
		this.attack_speed = Float.parseFloat(properties.get("attack_speed"));
		this.cost = Float.parseFloat(properties.get("cost"));
		
		
	}
	
	public float getRadius()
	{
		return radius;
	}
	public void setRadius(float radius)
	{
		this.radius = radius;
	}
	public float getStrength()
	{
		return strength;
	}
	public void setStrength(float strength)
	{
		this.strength = strength;
	}
	public float getAttack_speed()
	{
		return attack_speed;
	}
	public void setAttack_speed(float attack_speed)
	{
		this.attack_speed = attack_speed;
	}
	public float getCost()
	{
		return cost;
	}
	public void setCost(float cost)
	{
		this.cost = cost;
	}
	public float getLevel()
	{
		return level;
	}
	public void setLevel(float level)
	{
		this.level = level;
	}
	
	@Override
	public void update(float delta)
	{
		
	}
	
	@Override
	public void render(float delta, SpriteBatch batch)
	{
		
	}
	
	@Override
	public void dispose()
	{
		
	}
	@Override
	public void levelUp()
	{
		
	}
}
