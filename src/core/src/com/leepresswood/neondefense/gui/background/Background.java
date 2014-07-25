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
	private int tiles_across = 8;
	private float tile_size;
	private Direction direction;
	private boolean usedHorizontal = true;
	private float move_speed;
	
	public Background(Assets assets, Shapes shape, Direction direction, Speed speed)
	{	
		this.direction = direction;
		this.getSize(direction);
		this.initGrid(this.getTextureFromShape(assets, shape));
		
		switch(speed)
		{
			case EXTREME:
				this.move_speed = 2f;
				break;
			case FAST:
				this.move_speed = 1f;
				break;
			case MEDIUM:
				this.move_speed = 0.5f;
				break;
			case NONE:
				this.move_speed = 0f;
				break;
			case SLOW:
				this.move_speed = 0.25f;
				break;
		}
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
		float move_speed = this.tile_size * this.move_speed * delta;
		
		switch(direction)
		{
			case DOWN:
				translate_y = -move_speed;
				break;
			case DOWNLEFT:
				translate_x = -move_speed;
				translate_y = -move_speed;
				break;
			case DOWNRIGHT:
				translate_x = move_speed;
				translate_y = -move_speed;
				break;
			case LEFT:
				translate_x = move_speed;
				break;
			case NONE:
				break;
			case RIGHT:
				translate_x = move_speed;
				break;
			case UP:
				translate_y = move_speed;
				break;
			case UPLEFT:
				translate_x = -move_speed;
				translate_y = move_speed;
				break;
			case UPRIGHT:
				translate_x = move_speed;
				translate_y = move_speed;
				break;
			default:
				break;
		}		
		
		for(Shape s : this.grid)
		{
			s.translate(translate_x, translate_y);			
		}
		
		float far_d = this.farthestDown();
		float far_u = this.farthestUp();
		float far_l = this.farthestLeft();
		float far_r = this.farthestRight();
		
		for(Shape s : this.grid)
		{
			this.checkPosition(s, far_d, far_u, far_l, far_r);
		}
	}	

	public void render(SpriteBatch batch)
	{//Draw all components of the background
		for(Shape s : this.grid)
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
	
	private void checkPosition(Shape s, float far_d, float far_u, float far_l, float far_r)
	{//Check to see if the the given shape is offscreen.
		//Check X
		Rectangle rect = s.getBoundingRectangle();
		if(rect.x <= -this.tile_size - 1)
			s.setX(far_r);
		else if(rect.x >= Gdx.graphics.getWidth())
			s.setX(far_l - this.tile_size + 1);
		
		//Check Y
		if(rect.y <= -this.tile_size)
			s.setY(far_u - 1);
		else if(rect.y >= Gdx.graphics.getHeight())
			s.setY(far_d - this.tile_size + 1);
	}
	
	private float farthestLeft()
	{//Get the farthest left point on the grid.
		float extreme = 999;
		for(Shape s : this.grid)
			if(s.getX() < extreme)
				extreme = s.getX();
		return extreme;
	}
	
	private float farthestRight()
	{//Get the farthest left point on the grid.
		float extreme = -1;
		for(Shape s : this.grid)
			if(s.getX() + s.getWidth() > extreme)
				extreme = s.getX() + s.getWidth();
		return extreme;
	}
	
	private float farthestUp()
	{//Get the farthest left point on the grid.
		float extreme = -1;
		for(Shape s : this.grid)
			if(s.getY() + s.getHeight() > extreme)
				extreme = s.getY() + s.getHeight();
		return extreme;
	}
	
	private float farthestDown()
	{//Get the farthest left point on the grid.
		float extreme = 999;
		for(Shape s : this.grid)
			if(s.getY() < extreme)
				extreme = s.getY();
		return extreme;
	}
}
