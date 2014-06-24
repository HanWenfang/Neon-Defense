//All game drawing done here. Game logic should be done in the Field class.
package com.leepresswood.neondefense.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.NeonDefense;
import com.leepresswood.neondefense.entities.Field;

public class ScreenTDGame extends GameScreen
{
	private SpriteBatch batch;
	private Field field;
	
	public ScreenTDGame(NeonDefense game)
	{
		super(game);
		field = new Field();
	}
	
	@Override
	public void update(float delta)
	{//Update method. Update game logic.
		
	}
	
	@Override
	public void render(float delta)
	{//Draw method. Call update first.
		super.render(delta);
		this.update(delta);
		
		//Begin drawing
		this.batch.begin();
		
		this.batch.end();
	}
}

/*

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () 
	{
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
