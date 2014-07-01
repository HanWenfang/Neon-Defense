/* Similar to the LevelGenerator, all the towers' textures will be loaded here.
 * This will be a continuously working class that can be called at any point
 * to create a new tower.
 */
package com.leepresswood.neondefense.helpers;

import java.io.IOException;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.leepresswood.neondefense.NeonDefense;
import com.leepresswood.neondefense.entities.towers.Tower;

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
		switch(t)
		{
			case BLASTER:
				int id = 1;
				
				
				break;
		}
		Element e = tower_properties.get(1);
		String name = e.getChildByName("name").getAttribute("value");
		float radius = Float.parseFloat(e.getChildByName("radius").getAttribute("radius"));
		/* Get the rest of the data here.
		 * Afterward, you may want to make an Object array for the data
		 * Create a method that calls all this reading.
		 * Return the specific tower from the switch statement after reading.
		 */
		return tower;
	}
	
	public static enum Towers
	{//Tower names to be called during spawning.
		BLASTER
	}
}
