package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;

public class GUI implements GameEntityInterface
{
	private Field field;
	private Tower tower_spotlight;	//Which tower was selected.
	
	public GUI(Field field)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		//We will read from the field every update tick to determine any changes to money.
		this.field = field;
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
