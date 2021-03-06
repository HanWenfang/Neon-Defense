//Main tower class. Extend from this.
//Some tower attributes: radius of attack, strength, attack speed, cost, level.
package com.leepresswood.neondefense.entities.towers;

import java.util.ArrayList;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.entities.enemies.Enemy;
import com.leepresswood.neondefense.generators.Assets;

public abstract class Tower
{	
	protected final int MAX_LEVEL = 5;
	
	protected int id;
	protected Vector2 tile_location;
	protected float tile_size;
	
	protected float radius;						//In terms of blocks.
	protected float strength;					//Damage removed from enemy
	protected float attack_speed;				//Attacks per second
	protected int cost_to_upgrade;			//Cost per upgrade
	protected int total_invested;				//Gold invested in tower.
	protected int level = 1;					//Current upgrade level. Should always start at one
	
	protected float upgrade_multiplier;		//The cost per level increase
	protected float upgrade_radius;			//How much radius changes
	protected float upgrade_strength;		//How much strength changes
	protected float upgrade_attack_speed;	//How much attack speed changes
	
	protected Assets assets;					//Holds the towers' textures
	protected Sprite sprite;					//The base sprite of the tower

	private ShapeRenderer shapes;
	private boolean is_selected;
	
	public Tower(int id, Vector2 xy, float tile_size, Vector2 location, Assets assets, HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		//Get the properties
		this.radius = Float.parseFloat(properties.get("radius"));
		this.strength = Float.parseFloat(properties.get("strength"));
		this.attack_speed = Float.parseFloat(properties.get("attack_speed"));
		this.cost_to_upgrade = Integer.parseInt(properties.get("cost"));
		
		//Get the upgrades
		this.upgrade_multiplier = Float.parseFloat(upgrades.get("multiplier"));
		this.upgrade_radius = Float.parseFloat(upgrades.get("radius"));
		this.upgrade_strength = Float.parseFloat(upgrades.get("strength"));
		this.upgrade_attack_speed = Float.parseFloat(upgrades.get("attack_speed"));
		
		//Initialize
		this.id = id;
		this.tile_size = tile_size;
		this.tile_location = location;
		this.assets = assets;
		this.shapes = new ShapeRenderer();
		this.total_invested = this.cost_to_upgrade;
		
		//Set the sprite
		this.setTexture(xy);
	}
	
	public void allocate(Texture t)
	{//This is called in subclasses to initialize the sprite.
		this.sprite = new Sprite(t);
	}
	
	public void setTexture(Vector2 xy)
	{
		//Make the sprite smaller than the actual box so there isn't overlap on rotation
		float size = this.tile_size * 0.8f;
		float position_x = xy.x + 0.1f * this.tile_size;
		float position_y = xy.y + 0.1f * this.tile_size;
		this.sprite.setBounds(position_x, position_y,	size, size);
		this.sprite.setOriginCenter();
	}
	
	public float getRadius()
	{
		return this.radius;
	}

	public float getStrength()
	{
		return this.strength;
	}
	
	public float getAttack_speed()
	{
		return this.attack_speed;
	}
	
	public int getCost()
	{
		return this.total_invested;
	}
	
	public float getLevel()
	{
		return this.level;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void update(float delta, Field field)
	{//Every tower should have a look-at method. This is where it will be housed.
		//Scan through every enemy to determine:
		//1) Is it in the range
		//2) How long has it traveled.
		//The tower will aim at the enemy within range that has traveled the longest distance.
		ArrayList<Enemy> enemies = field.getEnemies();
		ArrayList<Enemy> close_enemies = new ArrayList<Enemy>();
		Enemy enemy = null;
		
		//Find the close enemies.
		for(Enemy e : enemies)
			if(inRange(e.getCenter()))
				close_enemies.add(e);
		
		//Find the farthest traveled of those enemies.
		enemy = this.getLongestDistance(close_enemies);				

		//If the above enemy is empty, just aim at the enemy that traveled the farthest.
		if(enemy == null)
		{
			enemy = this.getLongestDistance(enemies);
			if(enemy != null)
				this.lookAt(enemy.getCenter());
			else			//No enemies on screen. Just look straight down.
				this.lookAt(this.getCenter().x, Gdx.graphics.getHeight());
		}
		else
			this.lookAt(enemy.getCenter());
	}
	
	private Enemy getLongestDistance(ArrayList<Enemy> enemies)
	{//Find the farthest traveled of the enemies.
		float longest_travel = -1;
		Enemy enemy = null;
		for(Enemy e : enemies)
			if(e.getDistance() > longest_travel)
			{
				longest_travel = e.getDistance();
				enemy = e;
			}	
		
		return enemy;
	}
	
	private boolean inRange(Vector2 point)
	{//Is the passed point in-range of the tower?
		float range_squared = this.radius * this.tile_size * this.radius * this.tile_size;
		Vector2 center = this.getCenter();
		float x_squared = (point.x - center.x) * (point.x - center.x);
		float y_squared = (point.y - center.y) * (point.y - center.y);
		
		return range_squared > x_squared + y_squared;
	}
	
	private void lookAt(float x, float y)
	{//Look at the given point.
		float v2x = this.sprite.getWidth() / 2f + this.sprite.getX();
		float v2y = Gdx.graphics.getHeight() + this.sprite.getHeight() / 2f - this.sprite.getY();
		float angle = new Vector2(x, y).sub(v2x, v2y).angle() + 90f;
		this.sprite.setRotation(-angle);
	}
	
	private void lookAt(Vector2 v)
	{
		this.lookAt(v.x, v.y);
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		//Render the radius of the tower if selected
		if(this.is_selected)
		{
			float radius = this.tile_size * this.radius;
			int segments = 30;
			float v2x = this.sprite.getWidth() / 2f + this.sprite.getX();
			float v2y = -this.sprite.getHeight() + this.sprite.getY();
			
			//Have to end batch to draw the shape.
			batch.end();			
			shapes.begin(ShapeType.Line);
				shapes.setColor(0.4f, 0.9f, 0.7f, 0.125f);
				shapes.circle(v2x, v2y, radius, segments);
				shapes.circle(v2x, v2y, radius - 1, segments);
				shapes.circle(v2x, v2y, radius - 1.5f, segments);
				shapes.circle(v2x, v2y, radius - 2, segments);
			shapes.end();
			batch.begin();
		}
		
		this.sprite.draw(batch);
	}

	public int levelUp(int money)
	{//A level up has been requested. 
		int cost = 0;
		
		//Level cannot be above the maximum.
		if(this.level < this.MAX_LEVEL)
		{
			//Formula: Cost to level up = Initial tower cost * Multiplier * Current level
			cost = (int) (this.cost_to_upgrade * this.upgrade_multiplier * this.level);
			
			//Do we have the cash for this level up?
			if(money >= cost)
			{
				//Raise level and skills.
				this.level++;
				this.radius += this.upgrade_radius;
				this.strength += this.upgrade_strength;
				this.attack_speed += this.upgrade_attack_speed;
			}
			else
				cost = 0;
		}		
		
		//Add the cost of this upgrade to the total investment into the tower
				this.total_invested += cost;
		
		//Return cost of the upgrade.
		return cost;
	}

	public Sprite getSprite()
	{
		return this.sprite;		
	}

	public Vector2 getTileLocation()
	{
		return this.tile_location;
	}
	
	public Vector2 getCenter()
	{//Get the center of the enemy. This is where the towers should aim.
		return new Vector2(this.sprite.getX() + this.sprite.getWidth() / 2f, this.sprite.getY() + this.sprite.getHeight() / 2f);
	}

	public void setSelected()
	{
		this.is_selected = true;
	}

	public void removeSelection()
	{
		this.is_selected = false;
	}
}
