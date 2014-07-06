package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;

public class GUI implements GameEntityInterface
{
	private Tower tower_spotlight;	//Which tower was selected.
	private int money;					//Current cash amount.
	private int money_change;
	
	public GUI()
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		
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

	public void setUpdatesFromField(Field field)
	{//Check field for necessary updates.
		this.money_change = field.getMoneyChange();
	}
}
