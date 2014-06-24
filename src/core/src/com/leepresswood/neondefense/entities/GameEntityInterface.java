//Interface for all the game's entities. 
package com.leepresswood.neondefense.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameEntityInterface
{
	public void update(float delta);
	public void render(float delta, SpriteBatch batch);
	public void dispose();
}
