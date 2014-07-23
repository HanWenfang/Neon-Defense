//A Sprite extension that has specialized qualities for the background
package com.leepresswood.neondefense.gui.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shape extends Sprite
{
	private boolean visible;
	
	public Shape(Texture t, float size)
	{
		super(t);
		this.visible = true;
		this.setSize(size, size);
	}
	
	public Shape(Texture t, float size, boolean visible, boolean flipped)
	{
		super(t);
		this.visible = visible;		
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
