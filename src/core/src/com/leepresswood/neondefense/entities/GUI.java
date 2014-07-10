package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.leepresswood.neondefense.generators.Assets;

public class GUI implements GameEntityInterface
{
	private Tower tower_spotlight;		//Which tower was selected.
	private int money;						//Current cash amount.
	private int money_change;				//Received from field. Bounty from killing enemies.
	private boolean new_panel_requested;//Has a new panel been requested?
	private boolean new_panel_shop;		//Is the new panel the shop? True. Upgrade? False.
	private boolean open_panel_shop;		//Is the open panel the shop? True. Upgrade? False;
	private Sprite background;				//Background image
	
	public GUI(Assets asset_manager, Field field)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		this.background = new Sprite(asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(0, 0, Gdx.graphics.getWidth(), 40);
	}

	@Override
	public void update(float delta)
	{//Using information passed from field, update.
		//Change current cash value.
		this.money += money_change;
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{//Draw all components.
		this.background.draw(batch);
	}

	public void getUpdatesFromField(Field field)
	{//Check field for necessary updates. Set them in variable form.
		this.money_change = field.getMoneyChange();
		
		//If a tower is selected, get its ID and open the update panel.
		if(field.isTowerSelected() && this.open_panel_shop)
		{
			int tower_id = field.getSelectedTower();
			this.tower_spotlight = field.getTowerFromID(tower_id);
		}
		
		//Otherwise, if a blank panel is selected, open the shop.
		else if(field.isShopOpen() && !this.open_panel_shop)
		{
			
		}
	}
}
