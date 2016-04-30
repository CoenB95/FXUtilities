package com.cbapps.javafx.utilities.animation;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator.AnimType;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**This Transition animates the registered node between layout-position
 * changes.*/
public class LayoutTransition {

	private LayoutTransition() {
		
	}
	
	/**Register a new LayoutTransition to this Node.
	 * @param node the Node which should be animated.
	 */
	public static void register(Node node) {
		TranslateTransition ttx = new TranslateTransition(new Duration(400),
				node);
		TranslateTransition tty = new TranslateTransition(new Duration(400),
				node);
		ttx.setToX(0);
		tty.setToY(0);
		ttx.setInterpolator(new SmoothInterpolator(AnimType.DECELERATE));
		tty.setInterpolator(new SmoothInterpolator(AnimType.DECELERATE));
		node.layoutXProperty().addListener((a,b,c) -> {
			if (Math.abs(c.doubleValue()-b.doubleValue()) < 25) return;
			double delta = node.getTranslateX() + b.doubleValue() - 
					c.doubleValue();
			ttx.setFromX(delta);
			ttx.playFromStart();
			node.setTranslateX(delta);
		});
		node.layoutYProperty().addListener((a,b,c) -> {
			double delta = node.getTranslateY() + b.doubleValue() - 
					c.doubleValue();
			tty.setFromY(delta);
			tty.playFromStart();
			node.setTranslateY(delta);
		});
	}
}
