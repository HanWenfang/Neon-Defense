//The projectile for the Blaster tower.
package com.leepresswood.neondefense.entities.towers.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet
{	
	private Sprite sprite;
	
	public Bullet(Texture t, Vector2 position, float width, float height, float direction)
	{
		this.sprite = new Sprite(t);
		this.sprite.setBounds(position.x, position.y, width, height);
	}
	
	
}
