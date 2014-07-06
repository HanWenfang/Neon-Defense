package com.leepresswood.neondefense.generators;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Asset extends AssetManager
{
	//These strings will be used to call a specific texture from another screen.
	
	
	public Asset()
	{
		//Load all the assets
		
	}
	
	public Texture getTexture(String texture)
	{
		return this.get(texture, Texture.class);
	}
}
