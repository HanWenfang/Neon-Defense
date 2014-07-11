package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.generators.TowerGenerator;

public class GUIShop extends Other
{
	private Sprite background;				//Main GUI bar
	private Sprite[] towers;					//The buyable towers
	
	private boolean buy_ready;
	
	public GUIShop(int x, int y, float width, float height, GUI gui)
	{
		//Initialize variables
		this.buy_ready = false;
		this.towers = new Sprite[TowerGenerator.Towers.values().length];
		
		this.background = new Sprite(gui.asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(x, y, width, height);
		
		//Display every tower at the bottom. Might need to be horizontally scrollable if enough towers exist.
		int count = 0;
		for(Sprite s : towers)
			s = new Sprite(TowerGenerator.getTowerTexture(gui.asset_manager, count++));
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
