//Enemy super class. Extend from here.
//Enemy attributes: Speed, damage, health, bounty.
//Possible attributes: Invisibility, level, type (such as creep, boss, healer)
package com.leepresswood.neondefense.entities.enemies;

public class Enemy
{
	private float speed;
	private float damage;
	private float health;
	private float bounty;
	
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
}
