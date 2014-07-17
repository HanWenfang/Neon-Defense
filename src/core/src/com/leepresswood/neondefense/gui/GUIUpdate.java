package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;

public class GUIUpdate extends Other
{
	private Sprite background;
	private Sprite button_sell;			
	private Sprite button_upgrade;		
	private Sprite tower_sprite;
	
	private Tower tower;
	private GUI gui;
	
	private boolean upgrade_request;
	private boolean sell_request;
	
	public GUIUpdate(int x, int y, float width, float height, Tower tower, GUI gui)
	{
		this.tower = tower;
		this.gui = gui;
		
		this.background = new Sprite(gui.asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(x, y, width, height);
		
		//Show a picture of this tower centered. Sell button on left. Update on right.
		float tower_x = x + width / 2f - height / 2f;
		this.tower_sprite = new Sprite(tower.getSprite().getTexture());
		this.tower_sprite.setBounds(tower_x, y, height, height);
		
		//Buttons
		this.button_sell = new Sprite(gui.asset_manager.TEXTURE_BUTTON);
		this.button_sell.setBounds(x, y, tower_x - x, height);
		
		this.button_upgrade = new Sprite(gui.asset_manager.TEXTURE_BUTTON);
		this.button_upgrade.setBounds(tower_x + this.tower_sprite.getWidth(), y, tower_x - x, height);
	}
	
	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		this.background.draw(batch);
		this.button_sell.draw(batch);
		this.tower_sprite.draw(batch);
		this.button_upgrade.draw(batch);
	}

	@Override
	public boolean checkTouch(float x, float y)
	{//If within the bounds, return true
		return this.background.getBoundingRectangle().contains(x, y);
	}

	@Override
	public void doTouch(float x, float y)
	{//Check to see if either of the buttons were pressed
		if(this.button_sell.getBoundingRectangle().contains(x, y))
		{
			this.sell_request = true;
			this.upgrade_request = false;
		}
		else if(this.button_upgrade.getBoundingRectangle().contains(x, y))
		{
			this.sell_request = false;
			this.upgrade_request = true;
		}
	}
	
	public boolean getUpgradeRequested()
	{
		return this.upgrade_request;
	}
	
	public boolean getSellRequested()
	{
		return this.sell_request;
	}
	
	public int getTowerID()
	{
		return this.tower.getID();
	}
}
