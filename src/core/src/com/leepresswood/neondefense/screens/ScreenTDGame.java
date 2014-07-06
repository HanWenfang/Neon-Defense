//All game drawing done here. Game logic should be done in the Field class.
package com.leepresswood.neondefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.leepresswood.neondefense.NeonDefense;
import com.leepresswood.neondefense.entities.Field;

public class ScreenTDGame extends GameScreen implements GestureListener
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Field field;
	
	public ScreenTDGame(NeonDefense game)
	{
		super(game);
		
		//Set up camera
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.x = camera.viewportWidth / 2f;
		this.camera.position.y = Gdx.graphics.getWidth();
		this.camera.update();
		
		//Initialize spritebatch and field
		this.batch = new SpriteBatch();
		this.field = new Field(1);	//The passed in number is the level number.
		
		Gdx.input.setInputProcessor(new GestureDetector(this));
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
		this.batch.setProjectionMatrix(camera.combined);
		this.batch.begin();
			this.field.render(delta, batch);
		this.batch.end();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button)
	{//Player will tap on square to place/upgrade tower there.
		
		return true;		
	}

	@Override
	public boolean longPress(float x, float y)
	{
		return false;		
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button)
	{
		return false;		
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY)
	{
		//We need to limit the camera.
		//If deltaY > 0 and cameraY >= field.y, don't scroll up.
		
		if(deltaY < 0 && !this.camera.frustum.pointInFrustum(this.field.getTopLeft()))
			;
		else if(deltaY > 0 && !this.camera.frustum.pointInFrustum(this.field.getBottomLeft()))
			;
		else	
		{
			//Because of the way the maps are rendered, if there needs to be a
			//vertical scroll, use -deltaX.
			this.camera.translate(0, deltaY);
			this.camera.update();
		}
		
		
		return true;	
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button)
	{
		return false;		
	}

	@Override
	public boolean zoom(float initialDistance, float distance)
	{
		return false;		
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
	{
		return false;		
	}
}
