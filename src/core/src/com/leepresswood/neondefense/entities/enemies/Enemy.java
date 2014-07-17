//Enemy super class. Extend from here.
//Enemy attributes: Speed, damage, health, bounty.
//Possible attributes: Invisibility, level, type (such as creep, boss, healer)
package com.leepresswood.neondefense.entities.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.Field;

public class Enemy
{
	protected float health;							//How many hitpoints the enemy has.
	protected float speed;								//How fast the enemy is going in tiles per second
	protected float damage;							//Damage to your life if they reach the end. Should usually be 1. Bosses may be a special case.
	protected float bounty;							//Gold reward on death.
	protected float distance = 0f;					//Total distance traveled.
	
	protected Sprite sprite;
	protected Direction direction;
	
	public Enemy()
	{
		//Initialize
		this.sprite = new Sprite();
		this.direction = Direction.UP;
	}
	
	public float getSpeed()
	{
		return speed;
	}

	public float getDamage()
	{
		return damage;
	}

	public float getHealth()
	{
		return health;
	}

	public float getBounty()
	{
		return bounty;
	}
	
	public float getDistance()
	{
		return distance;
	}
	
	public void update(float delta, Field field)
	{
		//Collect any changes from the field.
		
		
		//Keep the distance traveled in a distance variable
		this.distance += delta * this.speed;
		
		//Get the change
		switch(this.direction)
		{
			case DOWN:
				this.sprite.translateY(-delta * this.speed);
				break;
			case LEFT:
				this.sprite.translateX(-delta * this.speed);
				break;
			case RIGHT:
				this.sprite.translateX(delta * this.speed);
				break;
			case UP:
				this.sprite.translateY(delta * this.speed);
				break;			
		}
		
		//Update health bar
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		this.sprite.draw(batch);
		//Draw health bar above
		
	}
	
	public Vector2 getCenter()
	{//Get the center of the enemy. This is where the towers should aim.
		
	}
	
	private enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
}
