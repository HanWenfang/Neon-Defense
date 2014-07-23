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
	private int tiles_across = 200;
	private Direction direction;
	
	public Background(Assets assets, Shapes shape, Direction direction)
	{
		this.direction = direction;
		
		//Initialize sprites
		this.initGrid(this.getTextureFromShape(assets, shape));
	}

	public void update(float delta)
	{//Move background components based upon direction.
		float translate_x = 0;
		float translate_y = 0;
		
		switch(direction)
		{
			case DOWN:
				translate_y = -1;
				break;
			case DOWNLEFT:
				translate_x = -1;
				translate_y = -1;
				break;
			case DOWNRIGHT:
				translate_x = 1;
				translate_y = -1;
				break;
			case LEFT:
				translate_x = 1;
				break;
			case NONE:
				break;
			case RIGHT:
				translate_x = 1;
				break;
			case UP:
				translate_y = 1;
				break;
			case UPLEFT:
				translate_x = -1;
				translate_y = 1;
				break;
			case UPRIGHT:
				translate_x = 1;
				translate_y = 1;
				break;
			default:
				break;
		}
		
		for(Shape s : grid)
			s.translate(translate_x, translate_y);
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
	
	private void initGrid(Texture t)
	{//Initialize the background for the grid shape.
		//Use the above size to determine how large the grid squares are.
		//The idea is that there will be two extra squares on either side, both top and bottom and left and right
		//After each movement, check to see if any tile is off-screen.
		//If it is, move it to the other side.
		float block_size = Gdx.graphics.getWidth() /  ((float) this.tiles_across);
		
		//Determine how many vertical blocks there should be from this size
		int vertical = (int) (Gdx.graphics.getHeight() / block_size);
		
		//From here, start X and Y values off-screen. Increment with each square.
		float x = -block_size;
		float y = -block_size;
		
		//Initialize grid.
		this.grid = new ArrayList<Shape>();
		for(int i = 0; i < vertical + 2; i++)
		{
			for(int j = 0; j < this.tiles_across + 2; j++)		
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
