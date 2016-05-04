package com.cbapps.javafx.utilities.resources;

import java.io.InputStream;

/**Util class for easy using Roboto Fonts.
 * Example usage:<p>
 * {@code Font.loadFont(Roboto.REGULAR, 14);}*/
public class RobotoFont {

	public static final InputStream BOLD = RobotoFont.class
			.getResourceAsStream("/com/cbapps/javafx/utilities/"
					+ "resources/Roboto-Bold.ttf");
	
	public static final InputStream MEDIUM = RobotoFont.class
			.getResourceAsStream("/com/cbapps/javafx/utilities/"
					+ "resources/Roboto-Medium.ttf");
	
	public static final InputStream REGULAR = RobotoFont.class
			.getResourceAsStream("/com/cbapps/javafx/utilities/"
					+ "resources/Roboto-Regular.ttf");
	
	public static final InputStream THIN = RobotoFont.class
			.getResourceAsStream("/com/cbapps/javafx/utilities/"
					+ "resources/Roboto-Thin.ttf");
}
