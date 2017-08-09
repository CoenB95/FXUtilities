package com.cbapps.javafx.utilities.bindings;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.layout.Region;

/**
 * Utility class containing functions to bind a position to a (side of) area.<p/>
 * Sample: shape1.bind(Positions.bottomOf(area1));
 *
 * @author Coen Boelhouwers
 */
public final class PositionBinding {

	private Region child;
	private final DoubleExpression x;
	private final DoubleExpression y;

	PositionBinding(Region Region, DoubleExpression x, DoubleExpression y) {
		this.child = Region;
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x.get();
	}

	public double getY() {
		return y.get();
	}

	public void toBottomLeft(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().add(other.heightProperty()).subtract(y));
	}

	public void toBottomRight(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().add(other.widthProperty()).subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().add(other.heightProperty()).subtract(y));
	}

	public void toCenter(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().add(other.widthProperty().divide(2)).subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().add(other.heightProperty().divide(2)).subtract(y));
	}

	public void toCenterBottom(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().add(other.widthProperty().divide(2)).subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().add(other.heightProperty()).subtract(y));
	}

	public void toCenterLeft(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().add(other.heightProperty().divide(2)).subtract(y));
	}

	public void toCenterRight(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().add(other.widthProperty()).subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().add(other.heightProperty().divide(2)).subtract(y));
	}

	public void toCenterTop(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().add(other.widthProperty().divide(2)).subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().subtract(y));
	}

	public void toTopLeft(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().subtract(y));
	}

	public void toTopRight(Region other) {
		child.layoutXProperty().bind(other.layoutXProperty().add(other.widthProperty()).subtract(x));
		child.layoutYProperty().bind(other.layoutYProperty().subtract(y));
	}
}
