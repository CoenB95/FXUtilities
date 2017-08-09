package com.cbapps.javafx.utilities.animation;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator.AnimType;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**This Transition animates the registered node between layout-position
 * changes.*/
public class LayoutTransition extends Transition {

	private Node node;
	private double deltaX;
	private double deltaY;

	public LayoutTransition(Node node) {
		this(Duration.millis(400), node);
	}

	public LayoutTransition(Duration duration, Node node) {
		this(duration, node, new SmoothInterpolator(AnimType.ACCELDECEL));
	}

	public LayoutTransition(Duration duration, Node node, Interpolator interpolator) {
		this.node = node;
		setCycleDuration(duration);
		setInterpolator(interpolator);
		node.layoutXProperty().addListener((a,b,c) -> {
			deltaX = node.getTranslateX() + b.doubleValue() - c.doubleValue();
			deltaY = node.getTranslateY();
			if (getStatus() != Status.RUNNING || Math.abs(b.doubleValue() - c.doubleValue()) > 25) playFromStart();
			node.setTranslateX(deltaX);
		});
		node.layoutYProperty().addListener((a,b,c) -> {
			deltaX = node.getTranslateX();
			deltaY = node.getTranslateY() + b.doubleValue() - c.doubleValue();
			if (getStatus() != Status.RUNNING || Math.abs(b.doubleValue() - c.doubleValue()) > 25) playFromStart();
			node.setTranslateY(deltaY);
		});
	}

	@Override
	protected void interpolate(double frac) {
		node.setTranslateX((1 - frac) * deltaX);
		node.setTranslateY((1 - frac) * deltaY);
	}
}
