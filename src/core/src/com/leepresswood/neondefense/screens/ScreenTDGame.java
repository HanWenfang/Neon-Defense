package com.leepresswood.neondefense.screens;

import com.leepresswood.neondefense.NeonDefense;

public class ScreenTDGame extends GameScreen
{
	public ScreenTDGame(NeonDefense game)
	{
		super(game);		
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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
