package com.leepresswood.neondefense;

import com.badlogic.gdx.Game;
import com.leepresswood.neondefense.screens.ScreenTDGame;

public class NeonDefense extends Game
{
	@Override
	public void create()
	{
		this.setScreen(new ScreenTDGame(this));
	}	
	
	@Override
	public void render()
	{
		super.render();	
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
	}
	
	@Override
	public void resume()
	{
		super.resume();	
	}
}
