//Game field. All towers and enemies placed here.
//Logic: Build a grid. Let the towers be placed on the grid. Read in text file for this level to generate enemy paths.
package com.leepresswood.neondefense.entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.enemies.Enemy;
import com.leepresswood.neondefense.entities.towers.Tower;
import com.leepresswood.neondefense.generators.Assets;
import com.leepresswood.neondefense.generators.LevelGenerator;
import com.leepresswood.neondefense.generators.TowerGenerator;
import com.leepresswood.neondefense.generators.TowerGenerator.Towers;
import com.leepresswood.neondefense.gui.GUI;
import com.leepresswood.neondefense.gui.GUIUpdate;

public class Field
{
	private TowerGenerator tower_generator;
	private Tile[][] tiles;
	
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	
	//GUI variables
	private int money_change;
	private boolean open_shop;
	private boolean tower_is_selected;
	private Vector2 selected_tile;
	private int selected_tile_id;
	private int selected_tower_id;
	
	public Field(Assets assets, int level)
	{//Collect the level and generate it.
		this.tiles = new LevelGenerator(assets, level).getTiles();	
		this.tower_generator = new TowerGenerator(this.getTileSize(), assets);
		this.towers = new ArrayList<Tower>();
		this.enemies = new ArrayList<Enemy>();
		this.open_shop = false;
		this.tower_is_selected = false;
		this.selected_tile_id = -1;
	}
	
	public void update(float delta)
	{//Every tower, enemy, and projectile should be updated here.		
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				this.tiles[y][x].update(delta);
		
		//Towers and enemies next
		for(Tower t : this.towers)
			t.update(delta, this);
		for(Enemy e : this.enemies)
			e.update(delta, this);
		
		//Projectiles
		
	}

	public void render(float delta, SpriteBatch batch)
	{//Every tower, enemy, and projectile should be rendered here.
		//Tiles drawn first
		for(int y = 0; y < this.tiles.length; y++)
			for(int x = 0; x < this.tiles[y].length; x++)
				this.tiles[y][x].render(delta, batch);
		
		//Towers and enemies next
		for(Tower t : this.towers)
			t.render(delta, batch);		
		for(Enemy e : this.enemies)
			e.render(delta, batch);
		
		//Projectiles
		
	}
	
	public Tile[][] getTiles()
	{
		return this.tiles;
	}

	public int getMoneyChange()
	{
		return this.money_change;		
	}
	
	public void resetMoneyChange()
	{//Every tick should reset the money change
		this.money_change = 0;
	}

	public Vector2 getTileCoordinatesByPoint(float x, float y)
	{//If the given x and y values are on of the tiles, we will be focusing on that one.
		for(int i = 0; i < this.tiles.length; i++)
			for(int j = 0; j < this.tiles[0].length; j++)
				if(this.tiles[i][j].getSprite().getBoundingRectangle().contains(x, y))
					return new Vector2(i, j);
		
		return null;
	}
	
	public boolean isShopOpen()
	{//Did we click on an empty tile?
		if(this.open_shop)
		{
			this.open_shop = false;
			return true;
		}
		return false;
	}
	
	public boolean isTowerSelected()
	{//Did we click on a tower?
		if(this.tower_is_selected)
		{
			this.tower_is_selected = false;
			return true;
		}
		return false;
	}
	
	public int getSelectedTower()
	{//The selected tower should be gathered by the ID.
		return this.selected_tower_id;
	}
	
	public float getTileSize()
	{
		return this.tiles[0][0].getSprite().getWidth();
	}

	public void doTileAction(Tile tile)
	{//Do the action of the particular tile.
		//If we clicked on a path, nothing needs to happen
		if(tile.isWalkable() || !tile.isPlaceable())
			return;
		else if(tile.isOccupied())	//Otherwise, we're doing something with towers.
		{//This tile is occupied. Allow user to upgrade or sell tower.
			this.open_shop = false;
			this.tower_is_selected = true;
			
			//Scan for the location of the selected tile
			for(Tower t : towers)
				if(tile.getLocation() == t.getTileLocation())
					this.selected_tower_id = t.getID();
		}
		else
		{//This tile is empty. Open the tower shop.
			this.open_shop = true;
			this.tower_is_selected = false;
			this.selected_tile = tile.getLocation();
		}
	}

	public Tower getTowerFromID(int tower_id)
	{//Get the tower by the given ID.
		for(Tower t : this.towers)
			if(t.getID() == tower_id)
				return t;
		return null;		
	}

	public void spawn(GUI gui)
	{//If the buying flag attribute isn't null, spawn the tower
		Towers tower_bought = gui.buyTowerCheck();
		if(tower_bought != null && this.tower_generator.checkMoney(gui.getMoney(), tower_bought))
		{
			//Buy the tower. Fill the given location.
			Tile tile = this.tiles[(int) selected_tile.y][(int) selected_tile.x];
			tile.occupy();
			
			Tower t = this.tower_generator.spawn(tower_bought, tile.getPosition(), tile.getLocation());
			this.towers.add(t);
			this.money_change -= t.getCost();
		}
	}
	
	public ArrayList<Tower> getTowers()
	{
		return this.towers;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return this.enemies;
	}

	public void upgradeTower(GUI gui)
	{//Upgrade the selected tower if enough funds are available.
		//Get the upgrade request type
		GUIUpdate holder = (GUIUpdate) gui.getOther();
		boolean type = holder.getSellRequested();
		
		//If the above is true, sell requested. False, upgrade requested.
		Tower t = this.getTowerFromID(holder.getTowerID());
		if(type)
		{			
			this.money_change += t.getCost();
			this.towers.remove(t);
		}
		else
		{
			this.money_change -= t.levelUp(gui.getMoney());
		}
	}
}
