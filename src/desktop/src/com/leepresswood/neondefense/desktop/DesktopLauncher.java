package com.leepresswood.neondefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.leepresswood.neondefense.NeonDefense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 560;
		config.height = 800;
		new LwjglApplication(new NeonDefense(), config);
	}
}
