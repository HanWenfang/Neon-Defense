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
	
	private Sprite background;				//Background image
	
	//These are the field-dependent 
	private Other other;						//This is the other panel. This will be either the update or the shop panel.
	
	public GUI(Assets asset_manager)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		this.background = new Sprite(asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(0, 0, Gdx.graphics.getWidth(), 40);
		
		//Set the other GUI elements.
		this.other = null;
		this.tower_spotlight = null;
		this.other = null;
		this.new_panel_requested = false;
	}

	@Override
	public void update(float delta)
	{//Using information passed from field, update.
		//Change current cash value.
		this.money += money_change;
		
		//Only update the other panel if it's not null.
		if(other != null)
			this.other.update(delta);
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{//Draw all components.
		this.background.draw(batch);
		
		//Only draw the other panel if it's not null.
		if(other != null)
			this.other.render(delta, batch);
	}

	public void getUpdatesFromField(Field field)
	{//Check field for necessary updates. Set them in variable form.
		this.money_change = field.getMoneyChange();
		
		//If a tower is selected, get its ID and open the update panel.
		if(field.isTowerSelected())
		{			
			//Set the flags
			this.new_panel_shop = false;
			this.new_panel_requested = true;
		}
		
		//Otherwise, if a blank panel is selected, open the shop.
		else if(field.isShopOpen())
		{			
			//Set the flags
			this.new_panel_shop = true;
			this.new_panel_requested = true;
		}
		
		//Is there a new panel requested?
		if(new_panel_requested)
		{
			//Reset variable to avoid infinite panel changes
			this.new_panel_requested = false;
			
			//Which panel is it?
			if(new_panel_shop) 	
			{//Shop
				this.tower_spotlight = null;
				this.other = new GUIShop();
			}
			else						
			{//Upgrade
				this.tower_spotlight = field.getTowerFromID(field.getSelectedTower());
				this.other = new GUIUpdate();
			}
		}
	}

	public void closeExtraScreens()
	{//The other panel needs to be closed.
		this.tower_spotlight = null;
		this.new_panel_requested = false;
		this.other = null;
	}
}
