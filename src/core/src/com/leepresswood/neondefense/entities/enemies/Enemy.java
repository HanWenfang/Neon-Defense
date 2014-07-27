//Enemy super class. Extend from here.
//Enemy attributes: Speed, damage, health, bounty.
//Possible attributes: Invisibility, level, type (such as creep, boss, healer)
package com.leepresswood.neondefense.entities.enemies;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.entities.towers.projectiles.Projectile;
import com.leepresswood.neondefense.generators.Assets;

public class Enemy
{
	protected Healthbar health;					//How many hitpoints the enemy has.
	protected float speed;							//How fast the enemy is going in tiles per second
	protected float damage;							//Damage to your life if they reach the end. Should usually be 1. Bosses may be a special case.
	protected float bounty;							//Gold reward on death.
	protected float distance = 0f;				//Total distance traveled.
	
	protected Sprite sprite;
	protected Direction direction;
	
	public Enemy(Vector2 xy, float tile_size, Vector2 location, Assets assets, HashMap<String, String> properties)
	{
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

	public Healthbar getHealthbar()
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
		//Get the next direction.
		
		
		//Keep the distance traveled in a distance variable
		float move = delta * this.speed;
		this.distance += move;
		
		//Get the change in position based upon direction.
		switch(this.direction)
		{
			case DOWN:
				this.sprite.translateY(-move);
				break;
			case LEFT:
				this.sprite.translateX(-move);
				break;
			case RIGHT:
				this.sprite.translateX(move);
				break;
			case UP:
				this.sprite.translateY(move);
				break;			
		}
		
		//Update health bar by checking all projectiles.
		for(Projectile p : field.getProjectiles())
		{
			
		}
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
	
	public Sprite getSprite()
	{
		return this.sprite;
	}
	
	private enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}
}
