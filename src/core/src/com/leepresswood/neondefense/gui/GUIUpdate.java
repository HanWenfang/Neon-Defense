package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;

public class GUIUpdate extends Other
{
	private Sprite background;
	private Sprite button_sell;			
	private Sprite button_upgrade;		
	private Sprite tower;
	
	public GUIUpdate(int x, int y, float width, float height, Tower tower, GUI gui)
	{
		this.background = new Sprite(gui.asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(x, y, width, height);
		
		//Show a picture of this tower centered. Sell button on left. Update on right.
		this.tower = new Sprite(tower.getSprite().getTexture());
		float tower_x = x + width / 2f - height / 2f;
		this.tower.setBounds(tower_x, y, height, height);
		
		//Buttons
		this.button_sell = new Sprite(gui.asset_manager.TEXTURE_BUTTON);
		this.button_sell.setBounds(x, y, tower_x - x, height);
		
		this.button_upgrade = new Sprite(gui.asset_manager.TEXTURE_BUTTON);
		this.button_upgrade.setBounds(tower_x + this.tower.getWidth(), y, tower_x - x, height);
	}
	
	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		
	}
}
