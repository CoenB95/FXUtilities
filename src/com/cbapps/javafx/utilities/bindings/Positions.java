package com.cbapps.javafx.utilities.bindings;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;

/**
 * Utility class containing functions to bind a position to a (side of) area.<p/>
 * Sample: shape1.bind(Positions.bottomOf(area1));
 *
 * @author Coen Boelhouwers
 */
public final class Positions {

	private Positions() {

	}

	public static PositionBinding bottomLeftOf(Region area) {
		return new PositionBinding(area, new SimpleDoubleProperty(), area.heightProperty());
	}

	public static PositionBinding bottomRightOf(Region area) {
		return new PositionBinding(area, area.widthProperty(), area.heightProperty());
	}

	public static PositionBinding centerOf(Region area) {
		return new PositionBinding(area, area.widthProperty().divide(2), area.heightProperty().divide(2));
	}

	public static PositionBinding centerBottomOf(Region area) {
		return new PositionBinding(area, area.widthProperty().divide(2), area.heightProperty());
	}

	public static PositionBinding centerLeftOf(Region area) {
		return new PositionBinding(area, new SimpleDoubleProperty(), area.heightProperty().divide(2));
	}

	public static PositionBinding centerRightOf(Region area) {
		return new PositionBinding(area, area.widthProperty(), area.heightProperty().divide(2));
	}

	public static PositionBinding centerTopOf(Region area) {
		return new PositionBinding(area, area.widthProperty().divide(2), new SimpleDoubleProperty());
	}

	public static PositionBinding topLeftOf(Region area) {
		return new PositionBinding(area, new SimpleDoubleProperty(), new SimpleDoubleProperty());
	}

	public static PositionBinding topRightOf(Region area) {
		return new PositionBinding(area, area.widthProperty(), new SimpleDoubleProperty());
	}
}
