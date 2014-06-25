package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile implements GameEntityInterface
{//The tiles for the game board. Towers placed on these, and enemies walk on these.
	private Texture texture;
	private float tile_size;
	private boolean walkable;	

	public Tile(Texture texture, float tile_size, boolean walkable)
	{
		this.texture = texture;
		this.tile_size = tile_size;
		this.walkable = walkable;		
	}

	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		
	}

	public boolean isWalkable()
	{//True = can't place tower. This will also be useful for the enemies finding the next place to walk.
		return walkable;
	}

	public float getTile_size()
	{
		return tile_size;
	}

	@Override
	public void dispose()
	{
		if(this.texture != null)
			this.texture.dispose();
	}	
}
