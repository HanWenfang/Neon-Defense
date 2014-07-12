package com.leepresswood.neondefense.entities.towers;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Field;

public class Blaster extends Tower
{

	public Blaster(int id, Vector2 location, HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		super(id, location, properties, upgrades);
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
