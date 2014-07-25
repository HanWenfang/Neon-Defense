//Allow for a moving background of various shapes.
package com.leepresswood.neondefense.gui.background;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.generators.Assets;

public class Background
{
	private ArrayList<Shape> grid;
	private int tiles_across = 12;
	private float tile_size;
	private Direction direction;
	private boolean usedHorizontal = true;
	private float move_speed = 1;
	private float total_moved = 0;
	
	public Background(Assets assets, Shapes shape, Direction direction)
	{	
		this.direction = direction;
		this.getSize(direction);
		this.initGrid(this.getTextureFromShape(assets, shape));
	}
	
	private void getSize(Direction direction)
	{//Get the size of the grid shapes based on their direction.
		//Left-right determined by Vertical height. Opposite for Up-down. Diagonal just determined by horizontal width.
		switch(direction)
		{
			case DOWN:
			case DOWNLEFT:
			case UP:				
			case DOWNRIGHT:			
			case UPLEFT:
			case UPRIGHT:	
				this.tile_size = Gdx.graphics.getWidth() /  ((float) this.tiles_across);
				this.usedHorizontal = true;
				break;
			case LEFT:
			case NONE:
			case RIGHT:
				this.tile_size = Gdx.graphics.getHeight() /  ((float) this.tiles_across);
				this.usedHorizontal = false;
				break;
		}
	}

	public void update(float delta)
	{//Move background components based upon direction.
		float translate_x = 0;
		float translate_y = 0;
		
		switch(direction)
		{
			case DOWN:
				translate_y = -this.move_speed;
				break;
			case DOWNLEFT:
				translate_x = -this.move_speed;
				translate_y = -this.move_speed;
				break;
			case DOWNRIGHT:
				translate_x = this.move_speed;
				translate_y = -this.move_speed;
				break;
			case LEFT:
				translate_x = this.move_speed;
				break;
			case NONE:
				break;
			case RIGHT:
				translate_x = this.move_speed;
				break;
			case UP:
				translate_y = this.move_speed;
				break;
			case UPLEFT:
				translate_x = -this.move_speed;
				translate_y = this.move_speed;
				break;
			case UPRIGHT:
				translate_x = this.move_speed;
				translate_y = this.move_speed;
				break;
			default:
				break;
		}
		
		this.total_moved += this.move_speed;
		
		for(Shape s : grid)
		{
			s.translate(translate_x, translate_y);
			
			if(this.total_moved % this.tile_size == 0)
				this.checkPosition(s);
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
	
	private void initGrid(Texture t)
	{//Initialize the background for the grid shape.
		//Use the above size to determine how large the grid squares are.
		//The idea is that there will be two extra squares on either side, both top and bottom and left and right
		//After each movement, check to see if any tile is off-screen.
		//If it is, move it to the other side.		
		
		//Depending upon whether we used horizontal or vertical, determine the other shape numbers.
		int other = 1;
		if(this.usedHorizontal)
			other = (int) (Gdx.graphics.getHeight() / this.tile_size);
		else
			other = (int) (Gdx.graphics.getWidth() / this.tile_size);

		//From here, start X and Y values off-screen. Increment with each square.
		float x = -this.tile_size;
		float y = -this.tile_size;
		
		//Initialize grid.
		this.grid = new ArrayList<Shape>();
		for(int i = 0; i < other + 2; i++)
		{
			for(int j = 0; j < this.tiles_across + 2; j++)		
			{
				this.grid.add(new Shape(t, new Vector2(x, y), this.tile_size));
				
				//Increment x or y
				if(this.usedHorizontal)
					x += this.tile_size;
				else
					y += this.tile_size;
			}
			
			//Increment x or y. Reset other;
			if(this.usedHorizontal)
			{
				x = -this.tile_size;
				y += this.tile_size;
			}
			else
			{
				y = -this.tile_size;
				x += this.tile_size;
			}
		}
	}
	
	private void checkPosition(Shape s)
	{//Check to see if the the given shape is offscreen.
		//Check X
		Rectangle rect = s.getBoundingRectangle();
		if(rect.x < -this.tile_size)
			s.setX(Gdx.graphics.getWidth());
		else if(rect.x > Gdx.graphics.getWidth())
			s.setX(-this.tile_size);
		
		//Check Y
		if(rect.y <= -this.tile_size)
			s.setY(Gdx.graphics.getHeight());
		else if(rect.y >= Gdx.graphics.getHeight())
			s.setY(-this.tile_size);
	}
}
