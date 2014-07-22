//Allow for a moving background of various shapes.
package com.leepresswood.neondefense.gui.background;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.leepresswood.neondefense.generators.Assets;

public class Background
{
	private final int NUMBER_SHAPES = 12;
	private Sprite[][] grid;
	
	public Background(Assets assets, Shapes shape, Color foreground, Color background, Direction direction)
	{
		//Get the texture for the sprite
		Texture t = this.getTextureFromShape(assets, shape);
		
		//Initialize grid sprites
		this.grid = new Sprite[NUMBER_SHAPES][];
		for(int i = 0; i < NUMBER_SHAPES; i++)
		{
			
		}
		
	}
	
	public Texture getTextureFromShape(Assets assets, Shapes shape)
	{//Determine which shape to use
		switch(shape)
		{
			case CIRCLE:
				break;
			case SQUARE:
				break;
		}
	}
}
