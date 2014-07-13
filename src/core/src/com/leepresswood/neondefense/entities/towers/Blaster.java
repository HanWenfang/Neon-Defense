package com.leepresswood.neondefense.entities.towers;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.generators.Assets;

public class Blaster extends Tower
{

	public Blaster(int id, Vector2 location, Assets assets, HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		super(id, location, assets, properties, upgrades);
	}

	@Override
	public void setTexture()
	{
			this.base_sprite = new Sprite(super.assets.TEXTURE_TOWER_BLASTER);
	}
	
	@Override
	public void update(float delta, Field field)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		
	}
}
