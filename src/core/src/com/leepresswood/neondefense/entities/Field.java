//Game field. All towers and enemies placed here.
//Logic: Build a grid. Let the towers be placed on the grid. Read in text file for this level to generate enemy paths.
package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.helpers.LevelGenerator;

public class Field implements GameEntityInterface
{
	private LevelGenerator generator;
	private float field_width;
	private Tile[][] tiles;
	
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
	}
	
	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		
	}

	@Override
	public void dispose()
	{
		
	}
}
