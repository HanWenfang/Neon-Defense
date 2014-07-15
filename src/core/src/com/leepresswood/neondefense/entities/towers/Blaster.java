package com.leepresswood.neondefense.entities.towers;

import java.util.HashMap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.generators.Assets;

public class Blaster extends Tower
{

	public Blaster(int id, Vector2 xy, float tile_size, Vector2 location, Assets assets, HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		super(id, xy, tile_size, location, assets, properties, upgrades);
	}

	@Override
	public void setTexture(Vector2 xy)
	{
		super.allocate(super.assets.TEXTURE_TOWER_BLASTER);
		super.setTexture(xy);
	}
	
	@Override
	public void update(float delta, Field field)
	{
		super.update(delta, field);
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		super.render(delta, batch);
	}
}
