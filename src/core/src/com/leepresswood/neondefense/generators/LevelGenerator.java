//Generate the level from the passed in number
package com.leepresswood.neondefense.generators;

import java.io.InputStream;
import java.util.Scanner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Tile;

public class LevelGenerator
{
	//Path to file that holds levels.
	private final String FILE_PATH_START_LEVEL = "levels/";
	private final String FILE_EXTENSION_LEVEL = ".lvl";
	
	//Preloaded textures
	private Assets assets;
	
	//The first two numbers in the level file will determine the number of tiles across and down.
	private Tile[][] tiles;
	private int tiles_across;
	private int tiles_down;
	private float tile_size;
	
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
		
		//First two numbers determine tiles_across and tiles_down. Determine the tile size
		this.tiles_across = scanner.nextInt();
		this.tiles_down = scanner.nextInt();
		this.tile_size = Gdx.graphics.getWidth() / (float) tiles_across;
		
		//Next number is the theme number. Get this theme's color
		Color color = Tinter.getColor(scanner.nextInt());
		
		//Every other number is a tile. Make the tiles
		this.tiles = new Tile[this.tiles_down][this.tiles_across];
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
				this.tiles[y][x] = new Tile(new Vector2(x, y), tile_type, this.getTileTexture(tile_type), this.tile_size, pos_x, pos_y, color);	
			}
		}
		scanner.close();
	}
	
	private Texture getTileTexture(int tile_type)
	{
		switch(tile_type)
		{
			case 0:
				return assets.TEXTURE_UNOCCUPIED;
			case 1:
				return assets.TEXTURE_PATH;
			default:
				return null;
		}
	}

	public Tile[][] getTiles()
	{
		return this.tiles;
	}
}
