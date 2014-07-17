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
	protected int cost;							//Gold cost
	protected int level = 1;					//Current upgrade level. Should always start at one
	
	protected float upgrade_multiplier;		//The cost per level increase
	protected float upgrade_radius;			//How much radius changes
	protected float upgrade_strength;		//How much strength changes
	protected float upgrade_attack_speed;	//How much attack speed changes
	
	protected Assets assets;					//Holds the towers' textures
	protected Sprite base_sprite;				//The base sprite of the tower

	private ShapeRenderer shapes;
	private boolean is_selected;
	
	public Tower(int id, Vector2 xy, float tile_size, Vector2 location, Assets assets, HashMap<String, String> properties, HashMap<String, String> upgrades)
	{
		this.id = id;
		this.tile_size = tile_size;
		this.tile_location = location;
		this.assets = assets;
		this.shapes = new ShapeRenderer();
		
		//Get the properties
		this.radius = Float.parseFloat(properties.get("radius"));
		this.strength = Float.parseFloat(properties.get("strength"));
		this.attack_speed = Float.parseFloat(properties.get("attack_speed"));
		this.cost = Integer.parseInt(properties.get("cost"));
		
		//Get the upgrades
		this.upgrade_multiplier = Float.parseFloat(upgrades.get("multiplier"));
		this.upgrade_radius = Float.parseFloat(upgrades.get("radius"));
		this.upgrade_strength = Float.parseFloat(upgrades.get("strength"));
		this.upgrade_attack_speed = Float.parseFloat(upgrades.get("attack_speed"));
		
		this.setTexture(xy);
	}
	
	public void allocate(Texture t)
	{
		this.base_sprite = new Sprite(t);
	}
	
	public void setTexture(Vector2 xy)
	{
		//Make the sprite smaller than the actual box so there isn't overlap on rotation
		float size = this.tile_size * 0.8f;
		float position_x = xy.x + 0.1f * this.tile_size;
		float position_y = xy.y + 0.1f * this.tile_size;
		this.base_sprite.setBounds(position_x, position_y,	size, size);
		this.base_sprite.setOriginCenter();
	}
	
	public float getRadius()
	{
		return radius;
	}

	public float getStrength()
	{
		return strength;
	}
	
	public float getAttack_speed()
	{
		return attack_speed;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public float getLevel()
	{
		return level;
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
		//Enemy closest_enemy
		this.lookAt(30, 30);
		this.is_selected = field.checkSelected(this.getTileLocation());
	}
	
	private void lookAt(float x, float y)
	{//Look at the given point.
		float v2x = this.base_sprite.getWidth() / 2f + this.base_sprite.getX();
		float v2y = Gdx.graphics.getHeight() + this.base_sprite.getHeight() / 2f - this.base_sprite.getY();
		float angle = new Vector2(x, y).sub(v2x, v2y).angle() + 90f;
		this.base_sprite.setRotation(-angle);
	}
	
	public void render(float delta, SpriteBatch batch)
	{
		//Render the radius of the tower if selected
		if(this.is_selected)
		{
			float v2x = this.base_sprite.getWidth() / 2f + this.base_sprite.getX();
			float v2y = -this.base_sprite.getHeight() + this.base_sprite.getY();
			
			batch.end();
			shapes.begin(ShapeType.Line);
				shapes.setColor(0.2f, 0.5f, 0.7f, 0.125f);
				shapes.circle(v2x, v2y, this.tile_size * this.radius, 30);
			shapes.end();
			batch.begin();
		}
		
		this.base_sprite.draw(batch);
	}

	public int levelUp(int money)
	{//A level up has been requested. 
		//Level cannot be above the maximum.
		if(this.level < this.MAX_LEVEL)
		{
			//Formula: Cost to level up = Initial tower cost * Multiplier * Current level
			int cost = (int) (this.cost * this.upgrade_multiplier * this.level);
			
			//Do we have the cash for this level up?
			if(money >= cost)
			{
				//Raise level and skills.
				this.level++;
				this.radius += this.upgrade_radius;
				this.strength += this.upgrade_strength;
				this.attack_speed += this.upgrade_attack_speed;
				
				//Pay for it.
				money -= cost;
			}
		}		
		
		//Return the money amount the player has now.
		return money;	
	}

	public Sprite getSprite()
	{
		return this.base_sprite;		
	}

	public Vector2 getTileLocation()
	{
		return this.tile_location;
	}
}
