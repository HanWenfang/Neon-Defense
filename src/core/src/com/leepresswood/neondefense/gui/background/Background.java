//Allow for a moving background of various shapes.
package com.leepresswood.neondefense.gui.background;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.leepresswood.neondefense.generators.Assets;

public class Background
{
	private ArrayList<Sprite> grid;
	
	public Background(Assets assets, Shapes shape, Color foreground, Color background, Direction direction)
	{
		//Get the texture for the sprite
		Texture t = this.getTextureFromShape(assets, shape);
		
		//Initialize grid sprites
		this.initializeGridFromShape(shape, t);
		
	}
	
	private Texture getTextureFromShape(Assets assets, Shapes shape)
	{//Determine which shape to use
		switch(shape)
		{
			case CIRCLE:
				return null;
			case SQUARE:
				return null;
			case GRID:
				return assets.TEXTURE_BACKGROUND_GRID;
			default:
				return null;
		}
	}
	
	private void initializeGridFromShape(Shapes shape, Texture t)
	{
		
	}
}
