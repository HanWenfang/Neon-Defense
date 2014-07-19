//The projectile for the Blaster tower.
package com.leepresswood.neondefense.entities.towers.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet
{
	private float timing = 0;
	private float time_to_shoot;
	
	private Sprite sprite;
	
	public Bullet(Texture t, Vector2 position, float width, float height, float time_to_shoot)
	{
		this.sprite = new Sprite(t);
		this.sprite.setBounds(position.x, position.y, width, height);
		
		this.time_to_shoot = time_to_shoot;
	}
	
	public void update(float delta)
	{//Update timing and position
		
		
	}
	
	public void update(float delta, float new_time_to_shoot)
	{//This method changes the time_to_shoot variable. You would do this during a level up.
		this.time_to_shoot = new_time_to_shoot;
		this.update(delta);
	}
}
