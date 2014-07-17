//This is the superclass to the update and shop classes.
package com.leepresswood.neondefense.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Other
{
	public abstract void update(float delta);

	public abstract void render(float delta, SpriteBatch batch);

	public abstract boolean checkTouch(float x, float y);

	public abstract void doTouch(float x, float y);
}
