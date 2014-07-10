//Game field. All towers and enemies placed here.
//Logic: Build a grid. Let the towers be placed on the grid. Read in text file for this level to generate enemy paths.
package com.leepresswood.neondefense.entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.leepresswood.neondefense.entities.enemies.Enemy;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.leepresswood.neondefense.generators.Assets;
import com.leepresswood.neondefense.generators.LevelGenerator;

public class Field implements GameEntityInterface
{
	private LevelGenerator generator;
	private Tile[][] tiles;
	
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	
	//GUI variables
	private int money_change;
	private boolean open_shop;
	private boolean tower_is_selected;
	private int selected_tower_id;
	
	public Field(Assets assets, int level)
	{//Collect the level and generate it.
		/* The width of the field should be the width of the screen.
		 * Divide the width evenly by the number of blocks across to 
		 * get the block size.
		 * The height of the field can then be determined from the 
		 * previously found width. Because all tiles are square,
		 * it is a simple matter of finding one side to find the total 
		 * height and width.
		 * Because we want a user interface at the bottom of the screen
		 * to display a shop and any menu bars, it is possible the
		 * field height will be larger than the screen. Thus, we will need
		 * a vertical scroll option.
		 */
		this.generator = new LevelGenerator(assets, level);
		this.tiles = this.generator.getTiles();
		
		//Initialize the variables.		
		this.towers = new ArrayList<Tower>();
		this.enemies = new ArrayList<Enemy>();
		this.open_shop = false;
		this.tower_is_selected = false;
		this.selected_tower_id = -1;
	}
	
	@Override
	public void update(float delta)
	{//Every tower, enemy, and projectile should be updated here.
		//Every tic should reset the money change
		this.money_change = 0;
		
		//Tiles updated first
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				this.tiles[y][x].update(delta);
		
		//Towers and enemies next
		for(Tower t : this.towers)
			t.update(delta);
		for(Enemy e : this.enemies)
			e.update(delta);
		
		//Projectiles
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{//Every tower, enemy, and projectile should be rendered here.
		//Tiles drawn first
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				this.tiles[y][x].render(delta, batch);
		
		//Towers and enemies next
		for(Tower t : this.towers)
			t.render(delta, batch);		
		for(Enemy e : this.enemies)
			e.render(delta, batch);
		
		//Projectiles
		
	}
	
	public float getFieldHeight()
	{//The height of the field will be the combined heights of all the tiles
		int array_height = tiles.length;
		
		float bot_y = this.tiles[0][0].getSprite().getY();
		float top_y = this.tiles[array_height][0].getSprite().getY() + this.tiles[array_height][0].getSprite().getHeight();
		return top_y - bot_y;
	}
	
	public float getFieldWidth()
	{//The width of the field will be the combined widths of all the tiles
		int array_height = tiles[0].length;
		
		float bot_x = this.tiles[0][0].getSprite().getX();
		float top_x = this.tiles[array_height][0].getSprite().getX() + this.tiles[0][array_height].getSprite().getWidth();
		return top_x - bot_x;
	}
	
	//Positions of the corners
	public Vector3 getTopLeft()
	{
		float x = this.tiles[0][0].getSprite().getX();
		float y = this.tiles[0][0].getSprite().getY() + this.tiles[0][0].getSprite().getHeight();
		
		return new Vector3(x, y, 0);
	}
	
	public Vector3 getTopRight()
	{
		int array_width = tiles[0].length;
		
		float x = this.tiles[0][array_width].getSprite().getX() + this.tiles[0][0].getSprite().getWidth();
		float y = this.tiles[0][0].getSprite().getY() + this.tiles[0][0].getSprite().getHeight();
		
		return new Vector3(x, y, 0);
	}
	
	public Vector3 getBottomLeft()
	{
		int array_height = tiles.length;
		
		float x = this.tiles[array_height - 1][0].getSprite().getX();
		float y = this.tiles[array_height - 1][0].getSprite().getY();
		
		return new Vector3(x, y, 0);
	}
	
	public Vector3 getBottomRight()
	{
		int array_height = tiles.length;
		int array_width = tiles[0].length;
		
		float x = this.tiles[array_height - 1][array_width - 1].getSprite().getX() + this.tiles[0][0].getSprite().getWidth();
		float y = this.tiles[array_height - 1][array_width - 1].getSprite().getY();
		
		return new Vector3(x, y, 0);
	}

	public int getMoneyChange()
	{
		return this.money_change;		
	}

	public Vector2 isOnField(float x, float y)
	{//If the given x and y values are on of the tiles, we will be focusing on that one.
		for(int i = 0; i < this.tiles.length; i++)
			for(int j = 0; j < this.tiles[0].length; j++)
				if(this.tiles[i][j].getSprite().getBoundingRectangle().contains(x, y))
					return new Vector2(i, j);
		
		return null;
	}
	
	public boolean isShopOpen()
	{//Did we click on an empty tile?
		return this.open_shop;
	}
	
	public boolean isTowerSelected()
	{//Did we click on a tower?
		return this.tower_is_selected;
	}
	
	public int getSelectedTower()
	{//The selected tower should be gathered by the ID.
		return this.selected_tower_id;
	}

	public void doInput(Vector2 location)
	{
		this.doTileAction(this.tiles[(int) location.x][(int) location.y]);
	}

	private void doTileAction(Tile tile)
	{//Do the action of the particular tile.
		//If we clicked on a path, nothing needs to happen
		if(tile.isWalkable() || !tile.isPlaceable())
			return;
		else if(tile.isOccupied())	//Otherwise, we're doing something with towers.
		{//This tile is occupied. Allow user to upgrade or sell tower.
			this.open_shop = false;
			this.tower_is_selected = true;
			this.selected_tower_id = tile.getTower().getID();
		}
		else
		{//This tile is empty. Open the tower shop.
			this.open_shop = true;
			this.tower_is_selected = false;
			this.selected_tower_id = -1;
		}
	}

	public Tower getTowerFromID(int tower_id)
	{
		return null;		
	}
}
