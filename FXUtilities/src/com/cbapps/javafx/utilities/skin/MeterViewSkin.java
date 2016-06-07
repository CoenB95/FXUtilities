package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.control.MeterView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class MeterViewSkin extends SkinBase<MeterView> {

	public MeterViewSkin(MeterView control) {
		super(control);
		control.setId("clockview");
		control.getStylesheets().add("/com/cbapps/javafx/"
				+ "utilities/styles/CleanTheme.css");
		Line arrow = new Line();
		Arc back = new Arc(50, 50, 50, 50, -45, 270);
		back.setType(ArcType.ROUND);
		back.setFill(Color.WHITE);
		back.setStroke(Color.RED);
		back.startAngleProperty().bind(Bindings.subtract(90, 
				control.angleProperty().multiply(0.5)));
		back.lengthProperty().bind(control.angleProperty());
		arrow.startXProperty().bind(back.layoutXProperty().add(
				back.radiusXProperty()));
		arrow.startYProperty().bind(back.layoutYProperty().add(
				back.radiusYProperty()));
		arrow.endXProperty().bind(arrow.startXProperty().add(40));
		arrow.endYProperty().bind(arrow.startYProperty());
		Rotate rotate = new Rotate();
		Timeline rotanim = new Timeline(
				new KeyFrame(Duration.ZERO, 
						new KeyValue(rotate.angleProperty(), 1)),
				new KeyFrame(Duration.millis(400), 
						new KeyValue(rotate.angleProperty(), 0)));
		control.valueProperty().addListener((a,o,n) -> {
			rotanim.getKeyFrames().set(0, new KeyFrame(Duration.millis(400),
					new KeyValue(rotate.angleProperty(), 
							control.valueProperty().divide(
							control.maxProperty().subtract(
							control.minProperty())).multiply(
							control.angleProperty()).subtract(90).subtract(
							control.angleProperty().divide(2)).get())));
			rotanim.playFromStart();
		});
		rotate.pivotXProperty().bind(arrow.startXProperty());
		rotate.pivotYProperty().bind(arrow.startYProperty());
		arrow.getTransforms().add(rotate);
		Label text = new Label();
		text.layoutXProperty().bind(back.centerXProperty().subtract(
				text.widthProperty().divide(2)));
		text.layoutYProperty().bind(back.centerYProperty().subtract(
				back.radiusYProperty().multiply(0.8)));
		text.textProperty().bind(Bindings.concat(control.valueProperty()
				.asString("%.0f"), " ", control.unitProperty()));
		Pane group = new Pane(back, text, arrow);
		group.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		getChildren().add(group);
	}
}