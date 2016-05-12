package com.cbapps.javafx.utilities.skin;

import java.util.Calendar;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator;
import com.cbapps.javafx.utilities.animation.SmoothInterpolator.AnimType;
import com.cbapps.javafx.utilities.control.ClockView;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class ClockViewSkin extends SkinBase<ClockView> {
	
	public ClockViewSkin(ClockView control) {
		super(control);
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle();
		rec.widthProperty().bind(control.sizeProperty());
		rec.heightProperty().bind(control.sizeProperty());
		rec.setFill(Color.TRANSPARENT);
		Arc arc = new Arc();
		arc.centerXProperty().bind(control.sizeProperty().multiply(0.5));
		arc.centerYProperty().bind(control.sizeProperty().multiply(0.5));
		arc.radiusXProperty().bind(control.sizeProperty().multiply(0.4));
		arc.radiusYProperty().bind(control.sizeProperty().multiply(0.4));
		arc.setStartAngle(90);
		arc.setStrokeWidth(3);
		arc.setFill(Color.TRANSPARENT);
		arc.strokeProperty().bind(control.colorProperty());
		Timeline animation = new Timeline(
				new KeyFrame(Duration.ZERO),
				new KeyFrame(Duration.millis(2000), 
						new KeyValue(animProperty(), 1, 
								new SmoothInterpolator(
										AnimType.ACCELDECEL))));
		animation.play();
		arc.lengthProperty().bind(animProperty().multiply(-360));
		Timeline time = new Timeline(
				new KeyFrame(Duration.ZERO, 
						new KeyValue(secondProperty(), 0)),
				new KeyFrame(Duration.seconds(60), event -> {
					int m = minuteProperty().intValue() + 1;
					int h, hn;
					h = hn = hourProperty().get();
					while (m >= 60) {
						m -= 60;
						hn++;
						if (hn >= 24) hn -= 24;
					}
					minuteProperty().set(m);
					if (h != hn) hourProperty().set(hn);
				}, new KeyValue(secondProperty(), 60)));
		time.setCycleCount(Animation.INDEFINITE);
		Calendar calendar = Calendar.getInstance();
		time.playFrom(Duration.seconds(calendar.get(Calendar.SECOND)));
		minuteProperty().set(calendar.get(Calendar.MINUTE));
		hourProperty().set(calendar.get(Calendar.HOUR));
		Line sec_line = createLine(control, secondProperty(), 
				animProperty().multiply(0.85), 60);
		Line min_line = createLine(control, minuteProperty(), 
				animProperty().multiply(0.80), 60);
		Line hour_line = createLine(control, hourProperty().add(
				minuteProperty().divide(60)), 
				animProperty().multiply(0.65), 12);
		sec_line.setStroke(Color.RED);
		min_line.strokeProperty().bind(control.colorProperty());
		hour_line.strokeProperty().bind(control.colorProperty());
		pane.getChildren().addAll(new Group(rec, arc, sec_line, min_line,
				hour_line));
		getChildren().add(pane);
	}
	
	private Line createLine(ClockView control, NumberExpression 
			property, NumberExpression arm_length, int divide) {
		Line line = new Line();
		line.setStrokeWidth(3);
		line.startXProperty().bind(control.sizeProperty()
				.multiply(0.5));
		line.startYProperty().bind(control.sizeProperty()
				.multiply(0.5));
		line.endXProperty().bind(control.sizeProperty().multiply(0.5));
		line.endYProperty().bind(control.sizeProperty().multiply(
				arm_length.multiply(-0.4).add(0.5)));
		Rotate rotation = new Rotate();
		rotation.pivotXProperty().bind(control.sizeProperty().multiply(0.5));
		rotation.pivotYProperty().bind(control.sizeProperty().multiply(0.5));
		rotation.angleProperty().bind(property.multiply(360/divide)
				.multiply(animProperty()));
		line.getTransforms().add(rotation);
		return line;
	}
	
	private DoubleProperty anim = new SimpleDoubleProperty(0);
	public DoubleProperty animProperty() {
		return anim;
	}
	
	private IntegerProperty hour = new SimpleIntegerProperty(0);
	public IntegerProperty hourProperty() {
		return hour;
	}
	
	private DoubleProperty minute = new SimpleDoubleProperty(0);
	public DoubleProperty minuteProperty() {
		return minute;
	}
	
	private DoubleProperty second = new SimpleDoubleProperty(0);
	public DoubleProperty secondProperty() {
		return second;
	}
}
