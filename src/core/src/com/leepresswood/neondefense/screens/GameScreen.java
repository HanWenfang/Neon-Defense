//In previous projects, I would just implement Screen on each new screen.
//To save myself from clutter, it's all being held here. The other screens should extend this.
package com.leepresswood.neondefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.leepresswood.neondefense.NeonDefense;

public class GameScreen implements Screen
{
	protected NeonDefense game;
	
	public GameScreen(NeonDefense game)
	{
		this.game = game;
	}

	@Override
	public void render(float delta)
	{
		//Clear screen with black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void update(float delta)
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void show()
	{
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
	}
}
