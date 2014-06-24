//Generate the level from the passed in number
package com.leepresswood.neondefense.helpers;

import java.io.File;
import java.io.InputStream;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.leepresswood.neondefense.entities.Tile;

public class LevelGenerator
{
	//Tile holder
	private Tile[] tiles;
	
	//The first two numbers in the level file will determine the number of tiles across and down.
	private int tiles_across;
	private int tiles_down;
	
	//Using the above, we can then determine the tile size
	private float tile_size;
	
	public LevelGenerator(int level, float field_width, float field_height)
	{
		//Fill the 
		this.readFromFile(level);
		
	}

	private void readFromFile(Integer level)
	{//Open level file, fill array of tiles and tiles across and down, and close level file
		FileHandle file = Gdx.files.internal(level.toString());
		InputStream level_string = file.read();
		
	}
}
