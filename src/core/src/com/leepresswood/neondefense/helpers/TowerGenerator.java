/* Similar to the LevelGenerator, all the towers' textures will be loaded here.
 * This will be a continuously working class that can be called at any point
 * to create a new tower.
 */
package com.leepresswood.neondefense.helpers;

import java.security.KeyPair;
import java.util.HashMap;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.leepresswood.neondefense.NeonDefense;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.sun.java.util.jar.pack.Package.Class;

public class TowerGenerator
{
	private float tile_size;
	private static final String tower_xml = NeonDefense.FILE_PATH_START_TOWER + "tower.xml";
	private Array<Element> tower_properties;
	private Array<Element> tower_upgrades;
	
	public TowerGenerator(float tile_size)
	{
		//Every tower must fit to the tile width and height.
		this.tile_size = tile_size;
		
		//Read the XML file for the properties
		XmlReader reader = new XmlReader();
		Element root = reader.parse(new FileHandle(tower_xml).read().toString());
		this.tower_properties = root.getChildrenByName("tower");
		this.tower_upgrades = root.getChildrenByName("upgrade");
	}
	
	public Tower spawn(Towers t)
	{//Spawn the passed tower and return it.
		int id;
		switch(t)
		{
			case BLASTER:
				id = 0;
				HashMap<String, String> attribute_pairs = this.get(this.tower_properties.get(id));
				HashMap<String, String> upgrade_pairs = this.get(this.tower_upgrades.get(id));
				return new Tower();
			case BOLT:
				break;
			case BOMB:
				break;
			case BUFF:
				break;
			default:
				break;
		}
	}

	private HashMap<String, String> get(Element e)
	{//Get the data here and package it into a NameValuePair array. This goes into the tower and is parsed there.		
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < e.getChildCount(); i++)
			map.put(e.getChild(i).getName(), e.getChild(i).getAttribute("value"));
		return map;
	}

	public static enum Towers
	{//Tower names to be called during spawning.
		BLASTER, BOMB, BOLT, BUFF
	}
}
