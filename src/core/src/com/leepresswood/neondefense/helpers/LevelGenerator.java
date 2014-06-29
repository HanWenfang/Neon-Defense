//Generate the level from the passed in number
package com.leepresswood.neondefense.helpers;

import java.io.InputStream;
import java.util.ArrayList;
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
	
	public LevelGenerator(int level, float field_width)
	{
		//Determine the tile size
		this.tile_size = field_width / tiles_across;
		
		//Fill the tiles array
		this.readFromFile(level);		
	}

	private void readFromFile(Integer level)
	{//Open level file, fill array of tiles and tiles across and down, and close level file
		FileHandle file = Gdx.files.internal(level.toString() + NeonDefense.FILE_EXTENSION_LEVEL);
		InputStream level_string = file.read();
		Scanner scanner = new Scanner(level_string);
		
		//First two numbers determine tiles_across and tiles_down
		this.tiles_across = scanner.nextInt();
		this.tiles_down = scanner.nextInt();
		
		//Every other number is a tile.
		ArrayList<Integer> tile_numbers = new ArrayList<Integer>();
		while(scanner.hasNext())
			tile_numbers.add(scanner.nextInt());
		
		//Set tiles and end
		this.setTiles(tile_numbers);
		scanner.close();
	}

	public Tile[][] getTiles()
	{
		return tiles;
	}

	private void setTiles(ArrayList<Integer> tile_numbers)
	{
		tiles = new Tile[this.tiles_down][this.tiles_across];
		TileNumberDecoder tnd = new TileNumberDecoder();
		
		//Count through all the tile_numbers
		int x = 0, y = 0;
		for(Integer tile_type : tile_numbers)
		{
			tiles[y][x++] = new Tile(tnd.getTileTexture(tile_type), this.tile_size, tnd.isWalkable(tile_type));
			if(x == tiles_across)
			{
				x = 0;
				y++;
			}
		}
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
		private static final int NUMBER_OF_TILES = 2;
		private Texture[] textures;
		private static final String file_opener = "tiles/";
		private boolean[] walkables;		
		
		public TileNumberDecoder()
		{//Preload all the textures
			textures = new Texture[NUMBER_OF_TILES];			
			textures[0] = new Texture(file_opener + "normal.png");
			textures[1] = new Texture(file_opener + "path.png");
			
			//Also preload whether the tile is walkable or not.
			walkables = new boolean[NUMBER_OF_TILES];
			walkables[0] = false;
			walkables[1] = false;
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
