package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile implements GameEntityInterface
{//The tiles for the game board. Towers placed on these, and enemies walk on these.
	private Texture texture;
	private float tile_size;
	private boolean walkable;	
	
	private Sprite sprite;
	
	public Tile(Texture texture, float tile_size, boolean walkable, int x, int y)
	{
		this.texture = texture;
		this.tile_size = tile_size;
		this.walkable = walkable;
		
		//Set the sprite
		this.sprite = new Sprite(this.texture);
		float position_x = Gdx.graphics.getWidth() - x * tile_size;
		float position_y = Gdx.graphics.getHeight() - y * tile_size;

		this.sprite.setPosition(position_x, position_y);
		this.sprite.setSize(tile_size, tile_size);
	}

	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		this.sprite.draw(batch);
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
