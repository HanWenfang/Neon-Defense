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
		switch(theme*99)
		{
			case ORANGE:
				return colorGetter(255, 165, 0);
			case LIGHT_BLUE:
				return Color.CYAN;
			case BLUE:
				return colorGetter(70, 130, 180);
			case GRAY:
				return Color.GRAY;
			case WHITE:
				return Color.WHITE;
			case GREEN:
				return colorGetter(46, 139, 87);
			case PURPLE:
				return colorGetter(70, 130, 180);
			default:							//You shouldn't be here.
				return Color.BLACK;
		}		
	}
	
	private static Color colorGetter(float r, float g, float b, float a)
	{//Using the passed in floats, return a color in LibGDX's correct form.
		float new_r = r / 255f;
		float new_g = g / 255f;
		float new_b = b / 255f;
		float new_a = a / 255f;
		
		return new Color(new_r, new_g, new_b, new_a);
	}
	
	private static Color colorGetter(float r, float g, float b)
	{//Return the colorGetter() method with a perfectly opaque alpha.
		return colorGetter(r, g, b, 255);
	}
}
