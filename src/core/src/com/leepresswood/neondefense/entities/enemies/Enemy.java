//Enemy super class. Extend from here.
//Enemy attributes: Speed, damage, health, bounty.
//Possible attributes: Invisibility, level, type (such as creep, boss, healer)
package com.leepresswood.neondefense.entities.enemies;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.generators.Assets;

public class Enemy
{
	protected float speed;							//How fast the enemy is going in tiles per second
	protected float damage;							//Damage to your life if they reach the end. Should usually be 1. Bosses may be a special case.
	protected float bounty;							//Gold reward on death.
	protected float distance = 0f;				//Total distance traveled.
	
	protected Sprite sprite;
	protected Healthbar healthbar;				//How many hitpoints the enemy has.
	protected Direction direction;
	
	public Enemy(Vector2 xy, float size, Assets assets, HashMap<String, String> properties)
	{
		float health = Float.parseFloat(properties.get("health"));
		
		this.sprite = new Sprite();
		this.sprite.setBounds(xy.x, xy.y, size, size);
		this.direction = Direction.UP;
		this.healthbar = new Healthbar(health, xy.x, xy.y, size, size, assets);
	}
	
	public float getSpeed()
	{
		return speed;
	}

	public float getDamage()
	{
		return damage;
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
		
		//Update health bar's health amount and position
		
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		this.sprite.draw(batch);
		
		//Draw health bar above enemy.
		
	}
	
	public Vector2 getCenter()
	{//Get the center of the enemy. This is where the towers should aim.
		return new Vector2(this.sprite.getX() + this.sprite.getWidth() / 2f, this.sprite.getY() + this.sprite.getHeight() / 2f);
	}
	
	private enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
}
