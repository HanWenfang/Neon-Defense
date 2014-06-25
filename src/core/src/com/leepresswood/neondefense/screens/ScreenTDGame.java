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
		this.field = new Field(1);	//The passed in number is the level number.
	}
	
	@Override
	public void update(float delta)
	{//Update method. Update game logic.
		this.field.update(delta);
	}
	
	@Override
	public void render(float delta)
	{//Draw method. Call update first.
		//Clear screen and update.
		super.render(delta);
		this.update(delta);
		
		//Begin drawing
		this.batch.begin();
			this.field.render(delta, batch);
		this.batch.end();
	}
}
