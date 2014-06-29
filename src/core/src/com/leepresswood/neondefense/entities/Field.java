//Game field. All towers and enemies placed here.
//Logic: Build a grid. Let the towers be placed on the grid. Read in text file for this level to generate enemy paths.
package com.leepresswood.neondefense.entities;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.enemies.Enemy;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.leepresswood.neondefense.helpers.LevelGenerator;

public class Field implements GameEntityInterface
{
	private LevelGenerator generator;
	private float field_width;
	private Tile[][] tiles;
	
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	
	public Field(int level)
	{//Collect the level and generate it.
		/* For the width of the field, the screen size should normally be good enough
		 * The last 40% of the height of the screen should be left for the control box.
		 * Therefore, if the screen width is greater than 60% of the height of the screen,
		 * just use that 60% height as the width.
		 * 
		 * In either case, this will create a square field.
		 */
		this.field_width = Gdx.graphics.getWidth() > 0.6f * Gdx.graphics.getHeight() ? Gdx.graphics.getWidth() : 0.6f * Gdx.graphics.getHeight();
		this.generator = new LevelGenerator(level, this.field_width);
		this.tiles = this.generator.getTiles();
		
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
		
		//Projectiles last
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{//Every tower, enemy, and projectile should be rendered here.
		//Tiles drawn first
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				System.out.println(this.tiles.length);//this.tiles[y][x].render(delta, batch);
		
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
