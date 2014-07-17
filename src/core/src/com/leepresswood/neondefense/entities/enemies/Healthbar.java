//This is the enemy's health bar.
//There will be a black background and a colored foreground
//The foreground will shrink as health is lost.

//Foreground might change colors as it gets lower.
//Go from green to yellow to red?
package com.leepresswood.neondefense.entities.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.generators.Assets;

public class Healthbar
{
	private float start;
	private float current;	
	private boolean dead;
	
	private Sprite background;
	private Sprite foreground;
	private float initial_width;
	
	public Healthbar(float start, float x, float y, float width, float height, Assets assets)
	{//Set the starting health bar.
		this.start = start;
		this.current = start;
		this.dead = false;
		this.initial_width = width;
		
		//Set the visuals
		this.background = new Sprite();
		this.background.setBounds(x, y, width, height);
		this.foreground = new Sprite();
		this.foreground.setBounds(x, y, width, height);
	}
	
	public void update(float damage, Vector2 position)
	{//Do the passed amount of damage
		//Only have to update if the enemy isn't dead.
		if(!this.isDead())
		{
			this.start -= damage;
			
			//Shrink the foreground in relation to what percentage of the health is lost
			float percent = this.current / this.start;
			float new_width = this.initial_width * percent;
			this.foreground.setSize(new_width, this.foreground.getHeight());
			
			//Readjust the positioning for the enemy's movement.
			this.background.setPosition(position.x, position.y);
			this.foreground.setPosition(position.x, position.y);			
		}		
	}
	
	public void render(SpriteBatch batch)
	{//Draw the bar
		//Only have to render if the enemy isn't dead.
		if(!this.isDead())
		{
			this.background.draw(batch);
			this.foreground.draw(batch);
		}
	}
	
	public boolean isDead()
	{//Is the enemy dead?
		return this.dead;
	}
}
