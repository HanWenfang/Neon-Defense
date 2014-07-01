package com.leepresswood.neondefense;

import com.badlogic.gdx.Game;
import com.leepresswood.neondefense.screens.ScreenTDGame;

public class NeonDefense extends Game
{;
	public static final String FILE_EXTENSION_LEVEL = ".lvl";

	public static final String FILE_PATH_START_TOWER = "towers/";
	public static final String FILE_PATH_START_TILE = "tiles/";
	public static final String FILE_PATH_START_LEVEL = "levels/";
	
	public static final int NUMBER_OF_TILES = 2;
	
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
