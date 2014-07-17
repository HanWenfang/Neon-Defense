package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.generators.Assets;
import com.leepresswood.neondefense.generators.TowerGenerator;
import com.leepresswood.neondefense.generators.TowerGenerator.Towers;

public class GUI
{
	private final float GUI_WIDTH = Gdx.graphics.getWidth();
	private final float GUI_HEIGHT = Gdx.graphics.getHeight() * 0.065f;
	
	//Money variables
	private int money = 400;
	
	//Graphical items
	public Assets asset_manager;
	private Other other;						
	private BitmapFont font;
	
	public GUI(Assets asset_manager)
	{//GUI will have the money amount and a quit button. Can be expanded to include tower upgrades later.
		this.asset_manager = asset_manager;
		this.other = null;
		font = asset_manager.FONT;
	}

	public void update(float delta)
	{//Using information passed from field, update.		
		//Only update the other panel if it's not null.
		if(this.other != null)
			this.other.update(delta);
	}

	public void render(float delta, SpriteBatch batch)
	{//Draw all components.
		//Only draw the other panel if it's not null.
		if(this.other != null)
			this.other.render(delta, batch);
		
		this.font.draw(batch, ((Integer) this.money).toString(), 0, Gdx.graphics.getHeight());
	}

	public void getUpdatesFromField(Field field)
	{//Check field for necessary updates. Set them in variable form.
		this.money += field.getMoneyChange();

		//Only check for a shop/upgrade open request if other is null
		if(this.other == null)
		{
			//If a tower is selected, get its ID and open the update panel.
			if(field.isTowerSelected())		//Upgrade
				this.other = new GUIUpdate(0, 0, GUI_WIDTH, GUI_HEIGHT, field.getTowerFromID(field.getSelectedTower()), this);
			
			//Otherwise, if a blank tile is selected, open the shop.
			else if(field.isShopOpen())		//Shop
				this.other = new GUIShop(0, 0, GUI_WIDTH, GUI_HEIGHT, this);
		}
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
	
	public Other getOther()
	{
		return this.other;
	}
	
	public Towers buyTowerCheck()
	{//Was a buy requested? If so, buy and send it to the field.
		if(this.other != null && this.other.getClass() == GUIShop.class && ((GUIShop) this.other).buy_id != -1)
		{//If a buy is queued, get the queued tower and put it on the field.
			Towers t = TowerGenerator.Towers.values()[((GUIShop) this.other).buy_id];
			this.closeExtraScreens();
			return t;
		}
		
		return null;
	}

	public void closeExtraScreens()
	{
		this.other = null;
	}
}
