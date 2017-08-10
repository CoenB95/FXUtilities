package com.cbapps.javafx.utilities.skin;

import com.cbapps.javafx.utilities.animation.SmoothInterpolator;
import com.cbapps.javafx.utilities.animation.SmoothInterpolator.AnimType;
import com.cbapps.javafx.utilities.control.ClockView;
import com.cbapps.javafx.utilities.font.RobotoFont;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class ClockViewSkin extends SkinBase<ClockView> {

	private static final Duration CLOCK_ANIM_DURATION = Duration.millis(2000);

	public ClockViewSkin(ClockView control) {
		super(control);
		control.getStylesheets().setAll("/styles/CleanTheme.css");
		control.getStyleClass().setAll("clockview");
		StackPane pane = new StackPane();
		pane.setOnMouseClicked(event -> control.setMode(control.getMode() == ClockView.Mode.DIGITAL ?
				ClockView.Mode.ANALOG : ClockView.Mode.DIGITAL));
		Pane clockPane = new Pane();
		Arc arc = new Arc();
		arc.centerXProperty().bind(pane.widthProperty().divide(2));
		arc.centerYProperty().bind(pane.heightProperty().divide(2));
		arc.radiusXProperty().bind(arc.radiusYProperty());
		arc.radiusYProperty().bind(Bindings.min(pane.widthProperty(), pane.heightProperty()).multiply(0.45));
		arc.setStartAngle(90);
		arc.strokeWidthProperty().bind(arc.radiusYProperty().multiply(0.02));
		arc.fillProperty().bind(control.arcFillProperty());
		arc.strokeProperty().bind(control.arcStrokeProperty());

		Arrow secondArrow = new Arrow(0.008)
				.bindAngle(control.secondProperty().divide(60.0).multiply(360.0))
				.bindLength(arc.radiusYProperty().multiply(0.8));
		secondArrow.strokeProperty().bind(control.secondArrowStrokeProperty());
		secondArrow.layoutXProperty().bind(pane.widthProperty().divide(2));
		secondArrow.layoutYProperty().bind(pane.heightProperty().divide(2));
		Arrow minuteArrow = new Arrow(0.015)
				.bindAngle(control.minuteProperty().divide(60.0).multiply(360.0))
				.bindLength(arc.radiusYProperty().multiply(0.8));
		minuteArrow.strokeProperty().bind(control.minuteArrowStrokeProperty());
		minuteArrow.layoutXProperty().bind(pane.widthProperty().divide(2));
		minuteArrow.layoutYProperty().bind(pane.heightProperty().divide(2));
		Arrow hourArrow = new Arrow(0.015)
				.bindAngle(control.hourProperty().divide(24.0).multiply(360.0))
				.bindLength(arc.radiusYProperty().multiply(0.8));
		hourArrow.strokeProperty().bind(control.hourArrowStrokeProperty());
		hourArrow.layoutXProperty().bind(pane.widthProperty().divide(2));
		hourArrow.layoutYProperty().bind(pane.heightProperty().divide(2));

		clockPane.getChildren().setAll(arc, hourArrow, minuteArrow, secondArrow);
		Label clockText = new Label();
		clockText.setFont(RobotoFont.thin(10));
		clockText.textProperty().bind(Bindings.format("%d:%02d:%02d",
				control.hourProperty(), control.minuteProperty(),
				Bindings.createIntegerBinding(control::getSecond, control.secondProperty())));
		clockText.textFillProperty().bind(control.textFillProperty());
		clockText.scaleXProperty().bind(clockText.scaleYProperty());
		clockText.scaleYProperty().bind(arc.radiusYProperty().multiply(0.05));
		clockText.setOpacity(0);

		Interpolator interpolator = new SmoothInterpolator(AnimType.ACCELDECEL);
		Timeline clockEnterAnimation = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(arc.lengthProperty(), 0),
						new KeyValue(hourArrow.scale.yProperty(), 0),
						new KeyValue(minuteArrow.scale.yProperty(), 0),
						new KeyValue(secondArrow.scale.yProperty(), 0)),
				new KeyFrame(CLOCK_ANIM_DURATION,
						new KeyValue(arc.lengthProperty(), -360, interpolator),
						new KeyValue(hourArrow.scale.yProperty(), 1, interpolator),
						new KeyValue(minuteArrow.scale.yProperty(), 1, interpolator),
						new KeyValue(secondArrow.scale.yProperty(), 1, interpolator)));

		FadeTransition ftClock = new FadeTransition(Duration.millis(400), clockPane);
		FadeTransition ftText = new FadeTransition(Duration.millis(400), clockText);
		ChangeListener<ClockView.Mode> modeListener = (a, b, c) -> {
			switch (c) {
				case ANALOG:
					if (control.animateModeChanges()) {
						ftClock.setFromValue(0);
						ftClock.setToValue(1);
						ftClock.setDelay(Duration.ZERO);
						ftText.setFromValue(1);
						ftText.setToValue(0);
						ftText.setDelay(Duration.ZERO);
						clockEnterAnimation.playFromStart();
						ftClock.playFromStart();
						ftText.playFromStart();
					} else {
						clockPane.setOpacity(1);
						clockText.setOpacity(0);
					}
					break;
				case DIGITAL:
					if (control.animateModeChanges()) {
						ftClock.setFromValue(1);
						ftClock.setToValue(0);
						ftClock.setDelay(CLOCK_ANIM_DURATION);
						ftText.setFromValue(0);
						ftText.setToValue(1);
						ftText.setDelay(CLOCK_ANIM_DURATION);
						clockEnterAnimation.setRate(-Math.abs(clockEnterAnimation.getRate()));
						clockEnterAnimation.jumpTo(CLOCK_ANIM_DURATION);
						clockEnterAnimation.setDelay(Duration.ZERO);
						clockEnterAnimation.play();
						ftClock.playFromStart();
						ftText.playFromStart();
					} else {
						clockPane.setOpacity(0);
						clockText.setOpacity(1);
					}
					break;
			}
		};
		modeListener.changed(null, null, control.getMode());
		control.modeProperty().addListener(modeListener);
		pane.getChildren().addAll(clockPane, clockText);
		pane.setPrefSize(50, 50);
		getChildren().add(pane);
	}

	private static class Arrow extends Line {

		private DoubleProperty length = new SimpleDoubleProperty();
		private DoubleProperty angle = new SimpleDoubleProperty();
		private Scale scale = new Scale(1, 1, 0, 0);

		public Arrow(double widthToLengthRatio) {
			strokeWidthProperty().bind(length.multiply(widthToLengthRatio));
			setStartX(0);
			startYProperty().bind(length.negate());
			setEndX(0);
			endYProperty().bind(length.multiply(0.1));
			setStrokeLineCap(StrokeLineCap.ROUND);
			Rotate rotation = new Rotate(0, 0, 0);
			rotation.angleProperty().bind(scale.yProperty().multiply(angle));
			getTransforms().addAll(rotation, scale);
		}

		public Arrow bindAngle(NumberExpression value) {
			angle.bind(value);
			return this;
		}

		public Arrow bindLength(NumberExpression value) {
			length.bind(value);
			return this;
		}
	}
}
