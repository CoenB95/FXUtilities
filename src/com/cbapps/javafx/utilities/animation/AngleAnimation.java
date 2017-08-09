package com.cbapps.javafx.utilities.animation;

import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Region;

public class AngleAnimation {

	public static void bindAngle(Region node, Region origin, 
			ObservableValue<? extends Number> angle,
			ObservableValue<? extends Number> distance) {
		node.layoutXProperty().bind(origin.layoutXProperty());
	}
}
