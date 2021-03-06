//All game drawing done here. Game logic should be done in the Field class.
package com.leepresswood.neondefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.leepresswood.neondefense.NeonDefense;
import com.leepresswood.neondefense.entities.Field;
import com.leepresswood.neondefense.gui.GUI;
import com.leepresswood.neondefense.gui.GUIShop;
import com.leepresswood.neondefense.gui.background.Background;
import com.leepresswood.neondefense.gui.background.Direction;
import com.leepresswood.neondefense.gui.background.Shapes;
import com.leepresswood.neondefense.gui.background.Speed;

public class ScreenTDGame extends GameScreen implements GestureListener
{
	//Background
	private Background background;
	
	//Field variables
	private SpriteBatch field_batch;
	private OrthographicCamera camera;
	private Field field;
	
	//GUI variables
	private SpriteBatch gui_batch;
	private GUI gui;
	
	public ScreenTDGame(NeonDefense game)
	{
		super(game);
		
		this.background = new Background(game.asset_manager, Shapes.GRID, Direction.UP, Speed.SLOW);
		
		//Set up camera
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.x = camera.viewportWidth / 2f;
		this.camera.position.y = Gdx.graphics.getWidth();
		this.camera.update();
		
		//Initialize spritebatches, GUI, and field
		this.field_batch = new SpriteBatch();
		this.gui_batch = new SpriteBatch();				
		this.field = new Field(game.asset_manager, 1);	//The passed in number is the level number.
		this.gui = new GUI(game.asset_manager);
		
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}
	
	@Override
	public void update(float delta)
	{//Update method. Update game logic.
		//Background
		this.background.update(delta);
		
		//Field
		this.field.update(delta);
		
		//Scan the field for a GUI update request.
		this.gui.getUpdatesFromField(this.field);			
		this.gui.update(delta);
		
		//Reset money change after every tick.
		this.field.resetMoneyChange();
	}
	
	@Override
	public void render(float delta)
	{//Draw method. Call update first.
		//Clear screen and update.
		super.render(delta);
		this.update(delta);

		//Begin drawing
		this.gui_batch.begin();
			this.background.render(gui_batch);
		this.gui_batch.end();
		
		this.field_batch.setProjectionMatrix(camera.combined);
		this.field_batch.begin();
			this.field.render(delta, field_batch);
		this.field_batch.end();
		
		//Second batch is necessary to avoid camera projections
		this.gui_batch.begin();
			this.gui.render(delta, gui_batch);
		this.gui_batch.end();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button)
	{//Player will tap on square to place/upgrade tower there.
		//We need to unproject the screen coordinates to world coordinates
		Vector3 unprojected = this.camera.unproject(new Vector3(x, y, 0));
		
		//Y will also be flipped. Flip it back.
		y = Gdx.graphics.getHeight() - y;
		
		//Send the location of the tap to the field and the GUI.
		Vector2 location = this.field.getTileCoordinatesByPoint(unprojected.x, unprojected.y);
		if(location != null)
		{
			//Close previous GUIs to avoid overlap.
			this.gui.closeExtraScreens();
			this.field.deselectTile();	
			
			//Find the requested action.
			this.field.doTileAction(this.field.getTiles()[(int) location.x][(int) location.y]);
		}
		else			
		{//This tap wasn't on the field. Check GUI.
			if(this.gui.checkTouch(x, y))	//Touch is on GUI.
			{
				//Close previous GUIs to avoid overlap.		
				this.gui.doTouch(x, y);
				
				//Is the Other GUI open?
				if(this.gui.getOther() != null)
				{
					boolean done;
					if(this.gui.getOther().getClass() == GUIShop.class)		//Shop
						done = this.field.spawn(this.gui);	
					else																		//Update
						done = this.field.upgradeTower(this.gui);
					
					//If we're done with these Other screens, close.
					if(done || this.gui.getOther() == null)
					{
						this.field.deselectTile();
						this.gui.closeExtraScreens();
					}
				}				
			}
			else	//Touch not on GUI. Clear any open extra GUI screens.
			{
				this.gui.closeExtraScreens();
				this.field.deselectTile();
				this.field.clearTowerRadius();
			}
		}
			
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
		//if(deltaY < 0 && !this.camera.frustum.pointInFrustum(this.field.getTopLeft()))

		this.gui.closeExtraScreens();
		this.field.deselectTile();
		this.field.clearTowerRadius();
		
		//this.camera.translate(-deltaX, deltaY);
		//this.camera.update();	
		
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
