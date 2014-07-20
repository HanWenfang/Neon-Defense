package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.generators.TileDecoder;

public class Tile
{//The tiles for the game board. Towers placed on these, and enemies walk on these.	
	protected Vector2 location;
	protected boolean walkable;
	protected boolean occupied;
	protected boolean placeable;
	protected boolean selected;
	
	protected Sprite sprite;
	protected Color color;
	
	public Tile(Vector2 location, int tile_type, Texture texture, float tile_size, float pos_x, float pos_y, Color color)
	{	
		//Set tile properties.
		this.location = location;
		this.walkable = TileDecoder.getWalkable(tile_type);
		this.placeable = TileDecoder.getPlaceable(tile_type);
		this.occupied = false;
		this.selected = false;
		
		//Set the sprite.
		this.sprite = new Sprite(texture);
		this.sprite.setPosition(pos_x, pos_y);
		this.sprite.setSize(tile_size, tile_size);
		this.color = color;
	}

	public void update(float delta)
	{
		
	}
	
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
		return this.walkable;
	}

	public boolean isOccupied()
	{//Tile is occupied if there is a tower on it.
		return this.occupied;
	}
	
	public boolean isPlaceable()
	{
		return this.placeable;
	}
	
	public boolean isSelected()
	{
		return this.selected;
	}

	public Sprite getSprite()
	{
		return this.sprite;		
	}
	
	public Vector2 getLocation()
	{
		return this.location;
	}
	
	public Vector2 getPosition()
	{
		return new Vector2(this.getSprite().getX(), this.getSprite().getY());
	}
	
	public void occupy()
	{//A tower wants to be placed. Occupy this tile.
		this.occupied = true;
		this.walkable = false;
		this.selected = false;
	}
	
	public void clear()
	{//We sold this tile's tower. Clear this tile.
		this.occupied = false;
		this.walkable = false;
		this.selected = false;
	}
}