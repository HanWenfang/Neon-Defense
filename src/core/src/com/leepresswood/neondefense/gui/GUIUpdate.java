package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.towers.Tower;

public class GUIUpdate extends Other
{
	private Sprite background;				//Main GUI bar
	
	public GUIUpdate(int x, int y, float width, float height, Tower tower, GUI gui)
	{
		this.background = new Sprite(gui.asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(x, y, width, height);
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
