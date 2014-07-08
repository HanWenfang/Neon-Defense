package com.leepresswood.neondefense.generators;

public class TileDecoder
{	
	//Pass in tile type. Get the qualities.
	public static boolean getWalkable(int type)
	{
		switch(type)
		{
			case 0:
				return false;
			case 1:
				return true;
			default: //This shouldn't happen.
				return false;
		}
	}
	
	public static boolean getPlaceable(int type)
	{
		switch(type)
		{
			case 0:
				return true;
			case 1:
				return false;
			default: //This shouldn't happen.
				return false;
		}
	}
}
