package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.entities.GameEntityInterface;
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
	private Sprite other;					//This is the other panel. This will be either the update or the shop panel.
	
	public GUI(Assets asset_manager)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		this.background = new Sprite(asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(0, 0, Gdx.graphics.getWidth(), 40);
	}

	@Override
	public void update(float delta)
	{//Using information passed from field, update.
		//Change current cash value.
		this.money += money_change;
		
		//Is there a new panel requested?
		if(new_panel_requested)
		{
			//Reset variable to avoid infinite panel changes
			this.new_panel_requested = false;
			
			//Which panel is it?
			if(new_panel_shop) 	//Shop
			{
				
			}
			else						//Upgrade
			{
				
			}
		}
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{//Draw all components.
		this.background.draw(batch);
		
		//Only draw the other panel if it's not null.
		if(other != null)
		{
			this.other.draw(batch);				//Other's background.
			
			//Get the type of other we have.
			if(tower_spotlight != null)
			{//Upgrade selected.
				
			}
			else
			{//Shop selected.
				
			}
		}
	}

	public void getUpdatesFromField(Field field)
	{//Check field for necessary updates. Set them in variable form.
		this.money_change = field.getMoneyChange();
		
		//If a tower is selected, get its ID and open the update panel.
		if(field.isTowerSelected() && this.open_panel_shop)
		{
			this.tower_spotlight = field.getTowerFromID(field.getSelectedTower());
			this.open_panel_shop = false;
			this.new_panel_shop = false;
			this.new_panel_requested = true;
		}
		
		//Otherwise, if a blank panel is selected, open the shop.
		else if(field.isShopOpen() && !this.open_panel_shop)
		{
			this.tower_spotlight = null;
			this.open_panel_shop = true;
			this.new_panel_shop = true;
			this.new_panel_requested = true;
		}
	}
}
