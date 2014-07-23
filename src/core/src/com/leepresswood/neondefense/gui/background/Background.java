//Allow for a moving background of various shapes.
package com.leepresswood.neondefense.gui.background;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.generators.Assets;

public class Background
{
	private ArrayList<Sprite> grid;
	private Shapes shape;
	private Direction direction;
	
	public Background(Assets assets, Shapes shape, Color foreground, Color background, Direction direction)
	{		
		this.shape = shape;
		this.direction = direction;
		
		//Initialize sprites
		this.initializeGridFromShape(shape, this.getTextureFromShape(assets, shape));
		this.setColors(foreground, background);
	}

	public void update(float delta)
	{//Move background components
		
	}
	
	public void render(SpriteBatch batch)
	{//Draw all components of the background
		for(Sprite s : grid)
			s.draw(batch);
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
	{//Initialize the grid based upon the passed shape
		switch(shape)
		{
			case CIRCLE:
				;
			case SQUARE:
				;
			case GRID:
				this.initGrid(t);
			default:
				;
		}
	}
	
	private void setColors(Color foreground, Color background)
	{
		
	}
	
	private void initGrid(Texture t)
	{//Initialize the background for the grid shape.
		//Size of the ArrayList should be a constant
		int SIZE = 12;
		
		//Use the above size to determine how large the grid squares are.
		this.grid = new ArrayList<Sprite>();
	}
}
