//
package com.leepresswood.neondefense.helpers;

public class TimeKeeper
{
	private float timing = 0;
	private float time_to_shoot;
	
	public TimeKeeper(float time_to_shoot)
	{		
		this.time_to_shoot = time_to_shoot;
	}
	
	public void update(float delta)
	{//Update timing
		
		
	}
	
	public void update(float delta, float new_time_to_shoot)
	{//This method changes the time_to_shoot variable. You would do this during a level up.
		this.time_to_shoot = new_time_to_shoot;
		this.update(delta);
	}
}
