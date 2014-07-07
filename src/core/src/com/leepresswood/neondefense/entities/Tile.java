package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile implements GameEntityInterface
{//The tiles for the game board. Towers placed on these, and enemies walk on these.
	private Texture texture;
	private float tile_size;
	private boolean walkable;
	private boolean occupied;
	
	private Sprite sprite;
	private Color color;
	
	public Tile(Texture texture, float tile_size, boolean walkable, float pos_x, float pos_y, Color color)
	{
		this.texture = texture;
		this.tile_size = tile_size;
		this.walkable = walkable;
		
		//Set the sprite
		this.sprite = new Sprite(this.texture);
		this.sprite.setPosition(pos_x, pos_y);
		this.sprite.setSize(tile_size, tile_size);
		this.color = color;
	}

	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		//Walkable textures will have a tint.
		Color old = batch.getColor();
		if(this.walkable)
			batch.setColor(color);
		
		batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		
		if(this.walkable)
			batch.setColor(old);
	}

	public boolean isWalkable()
	{//True = can't place tower. This will also be useful for the enemies finding the next place to walk.
		return walkable;
	}

	public boolean isOccupied()
	{//Tile is occupied if there is a tower on it.
		return occupied;
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

	public Sprite getSprite()
	{
		return this.sprite;		
	}	
}
