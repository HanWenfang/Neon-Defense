package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.entities.GameEntityInterface;
import com.leepresswood.neondefense.generators.Assets;
import com.leepresswood.neondefense.generators.TowerGenerator;
import com.leepresswood.neondefense.generators.TowerGenerator.Towers;

public class GUI implements GameEntityInterface
{
	private final float GUI_WIDTH = Gdx.graphics.getWidth();
	private final float GUI_HEIGHT = Gdx.graphics.getHeight() * .05f;
	
	private int money;						//Current cash amount.
	private int money_change;				//Received from field. Bounty from killing enemies.
	private boolean new_panel_requested;//Has a new panel been requested?
	private boolean new_panel_shop;		//Is the new panel the shop? True. Upgrade? False.
	
	public Assets asset_manager;
	private Other other;						//This is the other panel. This will be either the update or the shop panel.
	private BitmapFont font;
	
	public GUI(Assets asset_manager)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		this.asset_manager = asset_manager;
		
		//Set the other GUI panels.
		this.other = null;
		this.new_panel_requested = false;
		
		//Font locations
		font = asset_manager.FONT;
	}

	@Override
	public void update(float delta)
	{//Using information passed from field, update.
		//Change current cash value.
		this.money += money_change;
		
		//Only update the other panel if it's not null.
		if(this.other == null)
		{
			
		}
		else
		{
			this.other.update(delta);
		}
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{//Draw all components.
		//Only draw the other panel if it's not null.
		if(this.other != null)
			this.other.render(delta, batch);
		
		this.font.draw(batch, ((Integer) this.money).toString(), 0, Gdx.graphics.getHeight());
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
		
		//Otherwise, if a blank tile is selected, open the shop.
		else if(field.isShopOpen())
		{			
			//Set the flags
			this.new_panel_shop = true;
			this.new_panel_requested = true;
		}
		
		//Is there a new panel requested?
		if(this.new_panel_requested)
		{
			//Reset variable to avoid infinite panel changes
			this.new_panel_requested = false;
			
			//Which panel is it?
			if(this.new_panel_shop) 		//Shop
				this.other = new GUIShop(0, 0, GUI_WIDTH, GUI_HEIGHT, this);
			else									//Upgrade
				this.other = new GUIUpdate(0, 0, GUI_WIDTH, GUI_HEIGHT, field.getTowerFromID(field.getSelectedTower()), this);
		}
	}

	public void closeExtraScreens()
	{//The other panel needs to be closed.
		this.new_panel_requested = false;
		this.other = null;
	}
	
	public int getMoney()
	{//Return current money amount.
		return this.money;
	}

	public boolean checkTouch(float x, float y)
	{//Test all clickable GUI components for whether they were touched.
		//Fast-Forward button
		
		
		//Other bar (if open)
		if(this.other != null)
			return this.other.checkTouch(x, y);
		
		return false;
	}

	public void doTouch(float x, float y)
	{//Do the touch of the touched GUI component
		//Fast-Forward button
		
		//Other bar (if open)
		if(this.other != null)
			this.other.doTouch(x, y);		
	}
	
	public Towers buyTowerCheck()
	{//Was a buy requested? If so, buy and send it to the field.
		if(this.other != null && this.other.getClass() == GUIShop.class && ((GUIShop) this.other).buy_id != -1)
		{//If a buy is queued, get the queued tower and put it on the field.
			Towers t = TowerGenerator.Towers.values()[((GUIShop) this.other).buy_id];
			((GUIShop) this.other).reset();
			this.closeExtraScreens();
			return t;
		}
		
		return null;
	}
}
