//
package com.leepresswood.neondefense.helpers;

public class TimeKeeper
{
	private float current_time = 0;
	private float time_to_meet;
	private boolean ready;
	
	public TimeKeeper(float time_to_meet)
	{		
		this.time_to_meet = time_to_meet;
		this.ready = false;
	}
	
	public void update(float delta)
	{//Update timing
		this.current_time += delta;
		
		//If we've hit the time_to_meet, set the flag
		if(this.current_time >= this.time_to_meet)
		{
			this.current_time -= this.time_to_meet;
			this.ready = true;
		}
	}
	
	public void update(float delta, float new_time)
	{//This method changes the time_to_meet variable. You would do this during a level up for a tower, for instance.
		this.time_to_meet = new_time;
		this.update(delta);
	}
	
	public boolean isReady()
	{//Is the state of the clock read for the time tick?
		if(this.ready)
		{//Ready to do whatever timed action is necessary.
			//Only change the ready flag after it's been examined.
			this.ready = false;
			return true;
		}
		
		return false;
	}
}
