package com.leepresswood.neondefense;

import com.badlogic.gdx.Game;
import com.leepresswood.neondefense.generators.Assets;
import com.leepresswood.neondefense.screens.ScreenTDGame;

public class NeonDefense extends Game
{;
	public static final String FILE_EXTENSION_LEVEL = ".lvl";

	public static final String FILE_PATH_START_TOWER = "towers/";
	public static final String FILE_PATH_START_TILE = "tiles/";
	public static final String FILE_PATH_START_LEVEL = "levels/";
	
	public Assets asset_manager;
	
	@Override
	public void create()
	{
		this.asset_manager = new Assets();
		this.setScreen(new ScreenTDGame(this));
	}	
	
	@Override
	public void render()
	{
		super.render();	
	}
	
	@Override
	public void dispose()
	{//End the game and clear the remaining textures from video memory.
		super.dispose();
		this.asset_manager.clear();
	}
	
	@Override
	public void resume()
	{
		super.resume();	
	}
}
