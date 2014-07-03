//Game field. All towers and enemies placed here.
//Logic: Build a grid. Let the towers be placed on the grid. Read in text file for this level to generate enemy paths.
package com.leepresswood.neondefense.entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.enemies.Enemy;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.leepresswood.neondefense.generators.LevelGenerator;

public class Field implements GameEntityInterface
{
	private Tile[][] tiles;
	
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	
	public Field(int level)
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
		this.tiles = new LevelGenerator(level).getTiles();
		
		//Initialize the variables.		
		this.towers = new ArrayList<Tower>();
		this.enemies = new ArrayList<Enemy>();
	}
	
	@Override
	public void update(float delta)
	{//Every tower, enemy, and projectile should be updated here.
		//Tiles drawn first
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				this.tiles[y][x].update(delta);
		
		//Towers and enemies next
		for(Tower t : this.towers)
			t.update(delta);
		for(Enemy e : this.enemies)
			e.update(delta);
		
		//Projectiles
		
		
		//GUI
		
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
		
		
		//GUI
		
	}

	@Override
	public void dispose()
	{//Every tower, enemy, and projectile should be disposed here.
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				this.tiles[y][x].dispose();
		for(Tower t : this.towers)
			t.dispose();		
		for(Enemy e : this.enemies)
			e.dispose();
		/*Projectiles here*/		
	}
}
