//A Sprite extension that has specialized qualities for the background
package com.leepresswood.neondefense.gui.background;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Shape extends Sprite
{
	private boolean visible;
	
	public Shape(Texture t, Vector2 position, float size)
	{
		super(t);
		this.visible = true;
		this.setPosition(position.x, position.y);
		this.setSize(size, size);
		this.setColor(Color.RED);
	}
	
	public Shape(Texture t, float size, Vector2 position, boolean visible, boolean flipped)
	{
		super(t);
		this.visible = visible;		
		this.setPosition(position.x, position.y);
		this.setSize(size, size);
		
		//If the sprite is flipped, flip it.
		if(flipped)
		{
			this.setOriginCenter();
			this.rotate(180);
		}
	}
	
	@Override
	public void draw(Batch batch)
	{//Only have to draw if visible.
		if(this.visible)
			super.draw(batch);
	}
}
