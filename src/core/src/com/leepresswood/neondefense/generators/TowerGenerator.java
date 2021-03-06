/* Similar to the LevelGenerator, all the towers' textures will be loaded here.
 * This will be a continuously working class that can be called at any point
 * to create a new tower.
 */
package com.leepresswood.neondefense.generators;

import java.io.IOException;
import java.util.HashMap;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.leepresswood.neondefense.entities.towers.Blaster;
import com.leepresswood.neondefense.entities.towers.Tower;

public class TowerGenerator
{
	private final String FILE_PATH_START_TOWER = "towers/";
	
	private float tile_size;
	private Assets assets;
	private int id = 0;				//Current tower being placed. This is the tower's "name".
	private final String tower_xml = this.FILE_PATH_START_TOWER + "tower.xml";
	private Array<Element> tower_properties;
	private Array<Element> tower_upgrades;
	
	public TowerGenerator(float tile_size, Assets assets)
	{
		//Every tower must fit to the tile width and height.
		this.tile_size = tile_size;		
		this.assets = assets;
		try
		{//Read the XML file for the properties
			XmlReader reader = new XmlReader();		
			Element root = reader.parse(new FileHandle(tower_xml));
			this.tower_properties = root.getChildrenByName("tower");
			this.tower_upgrades = root.getChildrenByName("upgrade");
		} catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	public Tower spawn(Towers t, Vector2 xy, Vector2 location)
	{//Spawn the passed tower and return it.
		switch(t)
		{
			case BLASTER:
				HashMap<String, String> attribute_pairs = this.get(this.tower_properties.get(0));
				HashMap<String, String> upgrade_pairs = this.get(this.tower_upgrades.get(0));
				return new Blaster(this.id++, xy, this.tile_size, location, assets, attribute_pairs, upgrade_pairs);
			/*case BOLT:
				break;
			case BOMB:
				break;
			case BUFF:
				break;*/
		}
		
		//Something went wrong if you are here.
		return null;
	}

	private HashMap<String, String> get(Element e)
	{//Get the data here and package it into a hashmap. This goes into the tower and is parsed there.		
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < e.getChildCount(); i++)
			map.put(e.getChild(i).getName(), e.getChild(i).getAttribute("value"));
		return map;
	}

	public boolean checkMoney(int money, Towers type)
	{//Check to see if you have enough money to buy the passed tower.
		if(money > this.spawn(type, new Vector2(), new Vector2()).getCost())
			return true;
		return false;		
	}
	
	public static Texture getTowerTexture(Assets assets, int id)
	{//The passed ID is the tower ID.
		switch(id)
		{
			case 0:		//Blaster
				return assets.TEXTURE_TOWER_BLASTER;
			default:
				return null;				
		}
	}
	
	public static enum Towers
	{//Tower names to be called during spawning.
		BLASTER//, BOMB, BOLT, BUFF
	}
}
