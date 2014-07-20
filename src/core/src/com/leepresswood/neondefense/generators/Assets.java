package com.leepresswood.neondefense.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets
{
	//File path to tile textures
	private static final String TILE_FILE_PATH = "tiles/";
	private static final String TILE_UNOCCUPIED = TILE_FILE_PATH + "placeable.png";
	private static final String TILE_PATH = TILE_FILE_PATH + "path.png";
	
	//GUI components
	private static final String GUI_FILE_PATH = "gui/";
	private static final String GUI_BACKGROUND = GUI_FILE_PATH + "background.png";
	private static final String GUI_BUTTON = GUI_FILE_PATH + "button.png";
	private static final String TILE_GUI_SELECTED = GUI_FILE_PATH + "selected.png";
	
	//Towers
	private static final String TOWER_FILE_PATH = "towers/";
	private static final String TOWER_BLASTER = TOWER_FILE_PATH + "blaster.png";
	
	//Preload all the textures for the game.
	//GUI
	public Texture TEXTURE_GUI_BACKGROUND = new Texture(GUI_BACKGROUND); 
	public Texture TEXTURE_BUTTON = new Texture(GUI_BUTTON);
	
	//Tiles
	public Texture TEXTURE_UNOCCUPIED = new Texture(TILE_UNOCCUPIED); 
	public Texture TEXTURE_PATH = new Texture(TILE_PATH);
	public Texture TEXTURE_SELECTED = new Texture(TILE_GUI_SELECTED);
	
	//Towers
	public Texture TEXTURE_TOWER_BLASTER = new Texture(TOWER_BLASTER);
	
	//Preload all the fonts for the game
	public BitmapFont FONT = new BitmapFont(Gdx.files.internal("gui/white.fnt"));
	
	public void clear()
	{//Call this at the end of the program to clear textures from video memory.
		TEXTURE_UNOCCUPIED.dispose();
		TEXTURE_PATH.dispose();
		TEXTURE_GUI_BACKGROUND.dispose();
		TEXTURE_BUTTON.dispose();
		TEXTURE_SELECTED.dispose();
		TEXTURE_TOWER_BLASTER.dispose();
		
		FONT.dispose();
	}
}
