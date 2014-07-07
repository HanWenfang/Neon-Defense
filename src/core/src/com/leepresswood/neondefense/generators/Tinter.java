//Collects a number. This will be the theme of the level, and thus, the tint will be passed corresponding to this theme.
package com.leepresswood.neondefense.generators;

import com.badlogic.gdx.graphics.Color;

public class Tinter
{
	public final static int ORANGE = 1;
	public final static int LIGHT_BLUE = 2;
	public final static int BLUE = 3;
	public final static int GRAY = 4;
	public final static int WHITE = 5;
	public final static int GREEN = 6;
	public final static int PURPLE = 7;
	
	public static Color getColor(int theme)
	{
		switch(theme)
		{
			case ORANGE:
				return Color.ORANGE;
			case LIGHT_BLUE:
				return Color.CYAN;
			case BLUE:
				return Color.BLUE;
			case GRAY:
				return Color.GRAY;
			case WHITE:
				return Color.ORANGE;
			case GREEN:
				return Color.GREEN;
			case PURPLE:
				return Color.PURPLE;
			default:							//You shouldn't be here.
				return Color.BLACK;
		}		
	}
}
