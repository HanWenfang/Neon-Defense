package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.generators.TileDecoder;

public class Tile implements GameEntityInterface
{//The tiles for the game board. Towers placed on these, and enemies walk on these.	
	protected boolean walkable;
	protected boolean occupied;
	protected boolean placeable;
	
	protected Sprite sprite;
	protected Color color;
	
	public Tile(int tile_type, Texture texture, float tile_size, float pos_x, float pos_y, Color color)
	{	
		//Set tile properties.
		this.walkable = TileDecoder.getWalkable(tile_type);
		this.placeable = TileDecoder.getPlaceable(tile_type);
		this.occupied = false;
		
		//Set the sprite.
		this.sprite = new Sprite(texture);
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
	
	public boolean isPlaceable()
	{
		return placeable;
	}

	public Sprite getSprite()
	{
		return this.sprite;		
	}
}