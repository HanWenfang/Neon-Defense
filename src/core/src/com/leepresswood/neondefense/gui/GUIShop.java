package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.generators.TowerGenerator;

public class GUIShop extends Other
{
	private Sprite background;				//Main GUI bar
	private Sprite[] towers;				//The buyable towers
	private float gap = Gdx.graphics.getWidth() * 0.01f;
	
	public boolean buy_ready;
	public int buy_id;
	private GUI gui;
	
	public GUIShop(int x, int y, float width, float height, GUI gui)
	{
		//Initialize variables
		this.buy_ready = false;
		this.buy_id = -1;
		this.gui = gui;
		this.towers = new Sprite[TowerGenerator.Towers.values().length];		
		this.background = new Sprite(gui.asset_manager.TEXTURE_GUI_BACKGROUND);
		this.background.setBounds(x, y, width, height);
		
		//Display every tower at the bottom. Might need to be horizontally scrollable if enough towers exist.
		for(int i = 0; i < towers.length; i++)
		{
			//Get the tower's x and set bounds.
			float tower_x = x + i * height + i * this.gap;
			this.towers[i] = new Sprite(TowerGenerator.getTowerTexture(gui.asset_manager, i));
			this.towers[i].setBounds(tower_x, y, height, height);
		}
	}

	@Override
	public void update(float delta)
	{
		
	}

	@Override
	public void render(float delta, SpriteBatch batch)
	{
		this.background.draw(batch);
		for(Sprite s : towers)
			s.draw(batch);
	}
	
	@Override
	public boolean checkTouch(float x, float y)
	{//If within the bounds, return true
		return this.background.getBoundingRectangle().contains(new Vector2(x, y));
	}
	
	@Override
	public void doTouch(float x, float y)
	{//Check every sprite to see if it was tapped
		for(int i = 0; i < towers.length; i++)
			if(towers[i].getBoundingRectangle().contains(x, y))
			{//It was tapped. Buy if enough money is available.
				this.buy_id = i;
				this.buy_ready = true;
				i += towers.length;
			}
	}
}
