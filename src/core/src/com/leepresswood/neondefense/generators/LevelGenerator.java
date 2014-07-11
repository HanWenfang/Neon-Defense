//Generate the level from the passed in number
package com.leepresswood.neondefense.generators;

import java.io.InputStream;
import java.util.Scanner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.leepresswood.neondefense.entities.Tile;

public class LevelGenerator
{
	//Path to file that holds levels.
	public static final String FILE_PATH_START_LEVEL = "levels/";
	public static final String FILE_EXTENSION_LEVEL = ".lvl";
	
	//Preloaded textures
	private Assets assets;
	
	//Tile holder
	private Tile[][] tiles;
	
	//The first two numbers in the level file will determine the number of tiles across and down.
	private int tiles_across;
	private int tiles_down;
	
	//Using the above, we can then determine the tile size
	private float tile_size;
	
	//Theme of the level. (Color of the path)
	private int theme;
	
	public LevelGenerator(Assets assets, int level)
	{//Fill the tiles array
		this.assets = assets;
		this.readFromFile(level);		
	}

	private void readFromFile(Integer level)
	{//Open level file, fill array of tiles and tiles across and down, and close level file
		FileHandle file = Gdx.files.internal(this.FILE_PATH_START_LEVEL + level.toString() + this.FILE_EXTENSION_LEVEL);
		InputStream level_string = file.read();
		Scanner scanner = new Scanner(level_string);
		
		//First two numbers determine tiles_across and tiles_down
		this.tiles_across = scanner.nextInt();
		this.tiles_down = scanner.nextInt();
		
		//Determine the tile size
		this.tile_size = Gdx.graphics.getWidth() / (float) tiles_across;
		
		//Next number is the theme number. Get this theme's color
		Color color = Tinter.getColor(scanner.nextInt());
		
		//Every other number is a tile. Make the tiles
		this.tiles = new Tile[this.tiles_down][this.tiles_across];
		TileNumberDecoder tnd = new TileNumberDecoder();
		for(int y = 0; y < this.tiles_down; y++)
		{
			this.tiles[y] = new Tile[this.tiles_across];
			for(int x = 0; x < this.tiles_across; x++)
			{
				//Get the tile image
				int tile_type = scanner.nextInt();
				
				//Get the tile location
				float pos_x = x * tile_size;
				float pos_y = Gdx.graphics.getHeight() - y * tile_size;
				
				//Set tile				
				this.tiles[y][x] = new Tile(tile_type, tnd.getTileTexture(tile_type), this.tile_size, pos_x, pos_y, color);	
			}
		}
		scanner.close();
	}
	
	public float getHeight()
	{
		return this.tile_size * this.tiles_down;
	}

	public Tile[][] getTiles()
	{
		return tiles;
	}

	public int getTiles_across()
	{
		return tiles_across;
	}

	public int getTiles_down()
	{
		return tiles_down;
	}

	public float getTile_size()
	{
		return tile_size;
	}
	
	public int getTheme()
	{
		return theme;
	}

	private class TileNumberDecoder
	{
		private Texture[] textures;					
		
		public TileNumberDecoder()
		{
			//Preload the texture
			textures = new Texture[2];			
			textures[0] = assets.TEXTURE_UNOCCUPIED;
			textures[1] =	assets.TEXTURE_PATH;
		}
		
		public Texture getTileTexture(int tile_type)
		{
			return this.textures[tile_type];
		}
	}
}
