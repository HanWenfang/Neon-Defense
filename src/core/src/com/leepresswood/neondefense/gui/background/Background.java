//Allow for a moving background of various shapes.
package com.leepresswood.neondefense.gui.background;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.generators.Assets;

public class Background
{
	private ArrayList<Shape> grid;
	private int tiles_across = 12;
	private Direction direction;
	
	public Background(Assets assets, Shapes shape, Direction direction)
	{
		this.direction = direction;
		
		//Initialize sprites
		this.initializeGridFromShape(shape, this.getTextureFromShape(assets, shape));
	}

	public void update(float delta)
	{//Move background components based upon direction.
		switch(direction)
		{
			case DOWN:
				break;
			case DOWNLEFT:
				break;
			case DOWNRIGHT:
				break;
			case LEFT:
				break;
			case NONE:
				break;
			case RIGHT:
				break;
			case UP:
				break;
			case UPLEFT:
				break;
			case UPRIGHT:
				break;
			default:
				break;
		}
	}
	
	public void render(SpriteBatch batch)
	{//Draw all components of the background
		for(Shape s : grid)
			s.draw(batch);
	}
	
	private Texture getTextureFromShape(Assets assets, Shapes shape)
	{//Determine which shape to use
		switch(shape)
		{
			case CIRCLE:
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
			case GRID:
				this.initGrid(t);
			default:
				System.out.println("Error: No shape passed.");
		}
	}
	
	private void initGrid(Texture t)
	{//Initialize the background for the grid shape.
		//Use the above size to determine how large the grid squares are.
		//The idea is that there will be two extra squares on either side, both top and bottom and left and right
		//After each movement, check to see if any tile is off-screen.
		//If it is, move it to the other side.
		float block_size = Gdx.graphics.getWidth() / (float) (this.tiles_across - 2);
		
		//From here, start X and Y values off-screen. Increment with each square.
		float x = -block_size;
		float y = -block_size;
		
		//Initialize grid.
		this.grid = new ArrayList<Shape>();
		for(int i = 0; i < this.tiles_across; i++)
		{
			for(int j = 0; j < this.tiles_across; j++)		
			{
				this.grid.add(new Shape(t, new Vector2(x, y), block_size));
				
				
				//Increment x
				x += block_size;
			}
			
			//Increment y. Reset x;
			x = -block_size;
			y += block_size;
		}
	}
}
