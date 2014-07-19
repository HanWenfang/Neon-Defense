//
package com.leepresswood.neondefense.helpers;

public class TimeKeeper
{
	private float current_time = 0;
	private float time_to_meet;
	
	public TimeKeeper(float time_to_meet)
	{		
		this.time_to_meet = time_to_meet;
	}
	
	public void update(float delta)
	{//Update timing
		
		
	}
	
	public void update(float delta, float new_time)
	{//This method changes the time_to_meet variable. You would do this during a level up for a tower, for instance.
		this.time_to_meet = new_time;
		this.update(delta);
	}
}
