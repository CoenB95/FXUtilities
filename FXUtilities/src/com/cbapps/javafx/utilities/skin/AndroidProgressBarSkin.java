package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.animation.ArcTransition;
import com.cbapps.javafx.utilities.animation.SmoothInterpolator;
import com.cbapps.javafx.utilities.animation.SmoothInterpolator.AnimType;
import com.cbapps.javafx.utilities.control.AndroidProgressBar;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AndroidProgressBarSkin extends SkinBase<AndroidProgressBar> {
	
	public AndroidProgressBarSkin(AndroidProgressBar control) {
		super(control);
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle();
		rec.widthProperty().bind(control.sizeProperty());
		rec.heightProperty().bind(control.sizeProperty());
		rec.setFill(Color.TRANSPARENT);
		Arc arc = new Arc();
		arc.centerXProperty().bind(control.sizeProperty().multiply(0.4));
		arc.centerYProperty().bind(control.sizeProperty().multiply(0.4));
		arc.radiusXProperty().bind(control.sizeProperty().multiply(0.25));
		arc.radiusYProperty().bind(control.sizeProperty().multiply(0.25));
		arc.setStartAngle(0);
		arc.setLength(270);
		arc.setStrokeWidth(2);
		arc.setFill(Color.TRANSPARENT);
		arc.strokeProperty().bind(control.colorProperty());
		Rectangle arc_rec = new Rectangle();
		arc_rec.widthProperty().bind(control.sizeProperty().multiply(0.8));
		arc_rec.heightProperty().bind(control.sizeProperty().multiply(0.8));
		arc_rec.setFill(Color.TRANSPARENT);
		Group arc_grp = new Group();
		arc_grp.getChildren().addAll(arc_rec, arc);
		pane.getChildren().addAll(rec,arc_grp);
		ArcTransition at = new ArcTransition(Duration.millis(800), arc);
		at.setCycleCount(Transition.INDEFINITE);
		at.setAutoReverse(true);
		at.setInterpolator(new SmoothInterpolator(AnimType.ACCELDECEL));
		at.play();
		RotateTransition art = new RotateTransition(Duration.millis(2000), 
				arc_grp);
		art.setFromAngle(0);
		art.setInterpolator(Interpolator.LINEAR);
		art.setToAngle(-360);
		art.setCycleCount(Transition.INDEFINITE);
		art.play();
		getChildren().add(pane);
	}
}
