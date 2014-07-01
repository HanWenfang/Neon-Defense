//Generate the level from the passed in number
package com.leepresswood.neondefense.helpers;

import java.io.InputStream;
import java.util.Scanner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.leepresswood.neondefense.NeonDefense;
import com.leepresswood.neondefense.entities.Tile;

public class LevelGenerator
{
	//Tile holder
	private Tile[][] tiles;
	
	//The first two numbers in the level file will determine the number of tiles across and down.
	private int tiles_across;
	private int tiles_down;
	
	//Using the above, we can then determine the tile size
	private float tile_size;
	
	public LevelGenerator(int level)
	{//Fill the tiles array
		this.readFromFile(level, Gdx.graphics.getWidth());		
	}

	private void readFromFile(Integer level, float field_width)
	{//Open level file, fill array of tiles and tiles across and down, and close level file
		FileHandle file = Gdx.files.internal(NeonDefense.FILE_PATH_START_LEVEL + level.toString() + NeonDefense.FILE_EXTENSION_LEVEL);
		InputStream level_string = file.read();
		Scanner scanner = new Scanner(level_string);
		
		//First two numbers determine tiles_across and tiles_down
		this.tiles_across = scanner.nextInt();
		this.tiles_down = scanner.nextInt();
		
		//Determine the tile size
		this.tile_size = field_width / tiles_across;
		
		//Every other number is a tile. Make the tiles
		tiles = new Tile[this.tiles_down][this.tiles_across];
		TileNumberDecoder tnd = new TileNumberDecoder();
		for(int y = 0; y < this.tiles_down; y++)
		{
			tiles[y] = new Tile[this.tiles_across];
			for(int x = 0; x < this.tiles_across; x++)
			{
				//Get the tile image
				int tile_type = scanner.nextInt();
				
				//Get the tile location
				float pos_x = Gdx.graphics.getWidth() - x * tile_size;
				float pos_y = Gdx.graphics.getHeight() - y * tile_size;
				
				//Set tile				
				tiles[y][x] = new Tile(tnd.getTileTexture(tile_type), this.tile_size, tnd.isWalkable(tile_type), pos_x, pos_y);	
			}
		}
		scanner.close();
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
	
	private class TileNumberDecoder
	{
		private Texture[] textures;
		private static final String file_opener = NeonDefense.FILE_PATH_START_TILE;
		private boolean[] walkables;		
		
		public TileNumberDecoder()
		{
			//Preload all the textures
			textures = new Texture[NeonDefense.NUMBER_OF_TILES];			
			textures[0] = new Texture(file_opener + "normal.png");
			//textures[1] = new Texture(file_opener + "path.png");
			
			//Also preload whether the tile is walkable or not.
			walkables = new boolean[NeonDefense.NUMBER_OF_TILES];
			walkables[0] = false;
			walkables[1] = true;
		}
		
		public Texture getTileTexture(int tile_type)
		{
			return this.textures[tile_type];
		}
		
		public boolean isWalkable(int tile_type)
		{
			return this.walkables[tile_type];
		}
	}
}
