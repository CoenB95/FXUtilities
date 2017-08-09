package com.cbapps.javafx.utilities.skin;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator;
import com.cbapps.javafx.utilities.animation.SmoothInterpolator.AnimType;
import com.cbapps.javafx.utilities.control.ClockView;
import com.cbapps.javafx.utilities.font.RobotoFont;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringBinding;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class ClockViewSkin extends SkinBase<ClockView> {

	public ClockViewSkin(ClockView control) {
		super(control);
		control.setId("clockview");
		control.getStylesheets().add("/com/cbapps/javafx/"
				+ "utilities/styles/CleanTheme.css");
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
		arc.strokeWidthProperty().bind(control.sizeProperty()
				.multiply(0.02));
		arc.fillProperty().bind(control.backgroundColorProperty());
		arc.strokeProperty().bind(control.colorProperty());
		Timeline animation = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(control.animProperty(), 0)),
				new KeyFrame(Duration.millis(2000), 
						new KeyValue(control.animProperty(), 1, 
								new SmoothInterpolator(
										AnimType.ACCELDECEL))));
		animation.playFromStart();
		arc.lengthProperty().bind(control.animProperty().multiply(-360));
		Timeline time = new Timeline(
				new KeyFrame(Duration.ZERO, 
						new KeyValue(control.secondProperty(), 0)),
				new KeyFrame(Duration.seconds(60), event -> {
					int m = control.minuteProperty().intValue() + 1;
					int h, hn;
					h = hn = control.hourProperty().get();
					while (m >= 60) {
						m -= 60;
						hn++;
						if (hn >= 24) hn -= 24;
					}
					control.minuteProperty().set(m);
					if (h != hn) control.hourProperty().set(hn);
				}, new KeyValue(control.secondProperty(), 60)));
		time.setCycleCount(Animation.INDEFINITE);
		Calendar calendar = Calendar.getInstance();
		time.playFrom(Duration.millis(calendar.get(Calendar.SECOND)
				*1000 + calendar.get(Calendar.MILLISECOND)));
		control.minuteProperty().set(calendar.get(Calendar.MINUTE));
		control.hourProperty().set(calendar.get(Calendar.HOUR_OF_DAY));
		Line sec_line = createLine(control, control.secondProperty(),
				0.8, 0.3, 0.008, 60);
				//animProperty().multiply(0.85), 60);
		Line min_line = createLine(control, control.minuteProperty(),
				0.8, 0.1, 0.015, 60);
				//animProperty().multiply(0.80), 60);
		Line hour_line = createLine(control, control.hourProperty().add(
				control.minuteProperty().divide(60)),
				0.5, 0.1, 0.015, 12);
				//animProperty().multiply(0.65), 12);
		sec_line.strokeProperty().bind(control.accentColorProperty());
		min_line.strokeProperty().bind(control.colorProperty());
		hour_line.strokeProperty().bind(control.colorProperty());
		Group clock = new Group(rec, arc, hour_line,
				min_line, sec_line);
		Label clock_text = new Label();
		clock_text.setFont(RobotoFont.thin(70));
		clock_text.textFillProperty().bind(control.textColorProperty());
		clock_text.scaleXProperty().bind(control.sizeProperty()
				.divide(400));
		clock_text.scaleYProperty().bind(control.sizeProperty()
				.divide(400));
		clock_text.setOpacity(0);
		FadeTransition ft_clock = new FadeTransition();
		FadeTransition ft_text = new FadeTransition();
		ft_clock.setNode(clock);
		ft_text.setNode(clock_text);
		control.modeProperty().addListener((a,b,c) -> {
			switch (c.intValue()) {
			case ClockView.MODE_ANALOG:
				if (control.animate_mode_change) {
					ft_clock.setFromValue(clock.getOpacity());
					ft_clock.setToValue(1);
					ft_text.setFromValue(clock_text.getOpacity());
					ft_text.setToValue(0);
					animation.playFromStart();
					ft_clock.playFromStart();
					ft_text.playFromStart();
				} else {
					clock.setOpacity(1);
					clock_text.setOpacity(0);
					control.animProperty().set(0);
					animation.playFromStart();
				}
				break;
			case ClockView.MODE_DIGITAL:
				if (control.animate_mode_change) {
					ft_clock.setFromValue(clock.getOpacity());
					ft_clock.setToValue(0);
					ft_text.setFromValue(clock_text.getOpacity());
					ft_text.setToValue(1);
					ft_clock.playFromStart();
					ft_text.playFromStart();
				} else {
					clock.setOpacity(0);
					clock_text.setOpacity(1);
				}
				break;
			}
		});
		DecimalFormat df = new DecimalFormat();
		df.setMinimumIntegerDigits(2);
		df.setMaximumFractionDigits(0);
		df.setRoundingMode(RoundingMode.DOWN);
		StringBinding sec_bind = Bindings.createStringBinding(() -> 
		df.format(control.secondProperty().doubleValue())
		, control.secondProperty());
		StringBinding min_bind = Bindings.createStringBinding(() -> 
		df.format(control.minuteProperty().doubleValue())
		, control.minuteProperty());
		clock_text.textProperty().bind(Bindings.concat(
				control.hourProperty(), ":",min_bind,":", sec_bind));
		pane.getChildren().addAll(clock, clock_text);
		getChildren().add(pane);
	}

	private Line createLine(ClockView control, NumberExpression property,
			double arm_length, double back_length, 
			double stroke_weigth, int divide) {
		Line line = new Line();
		line.strokeWidthProperty().bind(control.sizeProperty()
				.multiply(stroke_weigth));
		line.startXProperty().bind(control.sizeProperty()
				.multiply(0.5));
		line.startYProperty().bind(control.sizeProperty().multiply(
				Bindings.multiply(0.4, Bindings.multiply(back_length,
						control.animProperty())).add(0.5)));
		line.endXProperty().bind(control.sizeProperty().multiply(0.5));
		line.endYProperty().bind(control.sizeProperty().multiply(
				Bindings.multiply(-0.4, Bindings.multiply(arm_length,
						control.animProperty())).add(0.5)));
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		Rotate rotation = new Rotate();
		rotation.pivotXProperty().bind(control.sizeProperty().multiply(0.5));
		rotation.pivotYProperty().bind(control.sizeProperty().multiply(0.5));
		rotation.angleProperty().bind(property.multiply(360/divide)
				.multiply(control.animProperty()));
		line.getTransforms().add(rotation);
		return line;
	}
}
