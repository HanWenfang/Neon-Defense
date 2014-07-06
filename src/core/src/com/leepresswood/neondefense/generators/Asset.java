package com.leepresswood.neondefense.generators;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Asset extends AssetManager
{
	//File path to tile textures
	private final String TILE_FILE_PATH = "tiles/";
	
	//These strings will be used to call a specific texture from another screen.
	public final String TILE_BLANK = TILE_FILE_PATH + "blank.png";
	
	public Asset()
	{
		//Load all the assets
		this.load(TILE_BLANK, Texture.class);
	}
	
	public Texture getTexture(String texture)
	{
		return this.get(texture, Texture.class);
	}
}
