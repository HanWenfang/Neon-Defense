//The projectile for the Blaster tower.
package com.leepresswood.neondefense.entities.towers.projectiles;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.enemies.Enemy;

public class Bullet
{	
	private Sprite sprite;
	private float direction;
	private float speed;
	private boolean decayed;

	public Bullet(Texture t, Vector2 position, float width, float height, float speed)
	{
		this.sprite = new Sprite(t);
		this.sprite.setBounds(position.x, position.y, width, height);
		this.speed = speed;
	}
	
	public void update(float delta, float direction)
	{//Do the trigonometry and move the bullet.
		this.direction = direction;
		
		//Only need to do this if not decayed
		if(!this.decayed)
			this.doTrigonometry(delta);
	}
	
	private void doTrigonometry(float delta)
	{//Do the trigonometry math and update the position of the sprite.
		float distance = delta * this.speed;
		float dx = (float) (distance * Math.cos(Math.toRadians(this.direction)));
		float dy = (float) (distance * Math.sin(Math.toRadians(this.direction)));
		
		this.sprite.translate(dx, dy);
	}
	
	public void update(float delta, float direction, float new_speed)
	{//Allows you to change the speed at which the bullet travels. For instance: A buff tower nearby.
		this.speed = new_speed;
		this.update(delta, direction);
	}
	
	public void render(SpriteBatch batch)
	{
		//Only need to do this if not decayed
		if(!this.decayed)
			this.sprite.draw(batch);		
	}
	
	public Rectangle getBounds()
	{//Get the bounds of the sprite. Good for collision detection (Overlaps)
		return this.sprite.getBoundingRectangle();
	}
	

	public boolean isDecayed()
	{//Is the bullet decayed? This happens after a collision or the bullet is off the screen.
		return this.decayed;
	}

	public void decay()
	{//Decay the bullet. 
		this.decayed = true;
	}
	
	public void checkCollision(ArrayList<Enemy> enemies)
	{//Check to see if this sprite has hit one of the enemies
		for(Enemy e : enemies)
			this.decayed = this.sprite.getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle()) ? true : this.decayed;
	}
}
