//Enemy super class. Extend from here.
//Enemy attributes: Speed, damage, health, bounty.
//Possible attributes: Invisibility, level, type (such as creep, boss, healer)
package com.leepresswood.neondefense.entities.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.Field;

public class Enemy
{
	private float health;							//How many hitpoints the enemy has.
	private float speed;								//How fast the enemy is going in tiles per second
	private float damage;							//Damage to your life if they reach the end. Should usually be 1. Bosses may be a special case.
	private float bounty;							//Gold reward on death.
	private float distance = 0f;					//Total distance traveled.
	
	public float getSpeed()
	{
		return speed;
	}
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	public float getDamage()
	{
		return damage;
	}
	public void setDamage(float damage)
	{
		this.damage = damage;
	}
	public float getHealth()
	{
		return health;
	}
	public void setHealth(float health)
	{
		this.health = health;
	}
	public float getBounty()
	{
		return bounty;
	}
	public void setBounty(float bounty)
	{
		this.bounty = bounty;
	}
	
	public float getDistance()
	{
		return distance;
	}
	
	public void setDistance(float distance)
	{
		this.distance = distance;
	}
	
	public void update(float delta, Field field)
	{
		//Keep the distance traveled in a distance variable
		
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		
	}
	
	private enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
}
