package com.leepresswood.neondefense.generators;

import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	//File path to tile textures
	private static final String TILE_FILE_PATH = "tiles/";
	private static final String TILE_UNOCCUPIED = TILE_FILE_PATH + "unoccupied.png";
	private static final String TILE_PATH = TILE_FILE_PATH + "tint.png";
	
	//GUI components
	private static final String GUI_FILE_PATH = "gui/";
	
	//GUI file locations.
	private static final String GUI_BACKGROUND = GUI_FILE_PATH + "background.png";

	//Preload all the textures for the game.
	public Texture TEXTURE_UNOCCUPIED = new Texture(TILE_UNOCCUPIED); 
	public Texture TEXTURE_PATH = new Texture(TILE_PATH);
	public Texture TEXTURE_GUI_BACKGROUND = new Texture(GUI_BACKGROUND); 
}
