package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIShop extends Other
{
	private Sprite background;				//Main GUI bar
	
	public GUIShop(int x, int y, float width, float height, GUI gui)
	{
		this.background = new Sprite(gui.asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(x, y, width, height);
		
		//Display every tower at the bottom. Might need to be horizontally scrollable if enough towers exist.
		
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
