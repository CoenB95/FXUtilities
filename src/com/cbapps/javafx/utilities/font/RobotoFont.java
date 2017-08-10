package com.cbapps.javafx.utilities.font;

import javafx.scene.text.Font;

/**Util class for easy using Roboto Fonts.
 * Example usage:<p>
 * {@code Font.loadFont(RobotoFont.regular(), 14);}*/
public class RobotoFont {

	private static boolean loaded;

	private RobotoFont() {
		Font.loadFont(getClass().getResourceAsStream("/Roboto-Bold.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/Roboto-Medium.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/Roboto-Regular.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/Roboto-Thin.ttf"), 14);
		loaded = true;
	}

	private static void checkLoaded() {
		if (!loaded) load();
	}

	public static RobotoFont load() {
		return new RobotoFont();
	}

	public static Font bold(double size) {
		checkLoaded();
		return Font.font("Roboto Bold", size);
	}

	public static Font medium(double size) {
		checkLoaded();
		return Font.font("Roboto Medium", size);
	}

	public static Font regular(double size) {
		checkLoaded();
		return Font.font("Roboto", size);
	}

	public static Font thin(double size) {
		checkLoaded();
		return Font.font("Roboto Thin", size);
	}
}
