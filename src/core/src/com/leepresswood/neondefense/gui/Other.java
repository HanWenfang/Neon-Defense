//This is the superclass to the update and shop classes.
package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leepresswood.neondefense.entities.GameEntityInterface;

public abstract class Other implements GameEntityInterface
{

	@Override
	public abstract void update(float delta);

	@Override
	public abstract void render(float delta, SpriteBatch batch);

	public boolean checkTouch(float x, float y)
	{
		return false;
	}

	public void doTouch(float x, float y)
	{
		
	}
}
