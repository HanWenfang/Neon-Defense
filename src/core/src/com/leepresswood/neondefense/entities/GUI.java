package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.leepresswood.neondefense.generators.Asset;

public class GUI implements GameEntityInterface
{
	private Tower tower_spotlight;	//Which tower was selected.
	private int money;					//Current cash amount.
	private int money_change;
	
	private Sprite background;			//Background image
	
	public GUI(Asset asset_manager)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		this.background = new Sprite(asset_manager.getTexture(asset_manager.GUI_BACKGROUND));
		this.background.setBounds(0, 0, Gdx.graphics.getWidth(), 40);
	}

	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		this.background.draw(batch);
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
