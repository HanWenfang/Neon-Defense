//Main tower class. Extend from this.
//Some tower attributes: radius of attack, strength, attack speed, cost, level.
package com.leepresswood.neondefense.entities.towers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.GameEntityInterface;

public class Tower implements GameEntityInterface
{
	private float radius;
	private float strength;
	private float attack_speed;
	private float cost;
	private float level;
	
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
}
