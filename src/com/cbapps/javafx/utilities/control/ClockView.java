package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.ClockViewSkin;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.time.LocalTime;

public class ClockView extends Control {

	private final BooleanProperty animateModeChange = new SimpleBooleanProperty(true);
	private final ObjectProperty<Paint> arcFill = new SimpleObjectProperty<Paint>(Color.TRANSPARENT);
	private final ObjectProperty<Paint> arcStroke = new SimpleObjectProperty<Paint>(Color.BLACK);
	private final IntegerProperty hour = new SimpleIntegerProperty(0);
	private final ObjectProperty<Paint> hourArrowStroke = new SimpleObjectProperty<Paint>(Color.BLACK);
	private final IntegerProperty minute = new SimpleIntegerProperty(0);
	private final ObjectProperty<Paint> minuteArrowStroke = new SimpleObjectProperty<Paint>(Color.BLACK);
	private final ObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.ANALOG);
	private final DoubleProperty second = new SimpleDoubleProperty(0);
	private final ObjectProperty<Paint> secondArrowStroke = new SimpleObjectProperty<Paint>(Color.BLACK);
	private final ObjectProperty<Paint> textFill = new SimpleObjectProperty<Paint>(Color.BLACK);

	private LocalTime time;
	private Timeline timeline;

	public BooleanProperty animateModeChangeProperty() {
		return animateModeChange;
	}

	public ObjectProperty<Paint> arcFillProperty() {
		return arcFill;
	}

	public ObjectProperty<Paint> arcStrokeProperty() {
		return arcStroke;
	}

	public IntegerProperty hourProperty() {
		return hour;
	}

	public ObjectProperty<Paint> hourArrowStrokeProperty() {
		return hourArrowStroke;
	}

	public IntegerProperty minuteProperty() {
		return minute;
	}

	public ObjectProperty<Paint> minuteArrowStrokeProperty() {
		return minuteArrowStroke;
	}

	public ObjectProperty<Mode> modeProperty() {
		return mode;
	}

	public DoubleProperty secondProperty() {
		return second;
	}

	public ObjectProperty<Paint> secondArrowStrokeProperty() {
		return secondArrowStroke;
	}

	public ObjectProperty<Paint> textFillProperty() {
		return textFill;
	}

	public ClockView() {
		timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(secondProperty(), 0)),
				new KeyFrame(Duration.seconds(60), event -> {
					setMinute((getMinute() + 1) % 60);
					if (getMinute() == 0) setHour((getHour() + 1) % 24);
				}, new KeyValue(secondProperty(), 60))
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		setTime(LocalTime.now());
		run(true);
	}
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new ClockViewSkin(this);
	}

	public boolean animateModeChanges() {
		return animateModeChangeProperty().get();
	}

	public Paint getArcFill() {
		return arcFillProperty().get();
	}

	public Paint getArcStroke() {
		return arcStrokeProperty().get();
	}

	public int getHour() {
		return hourProperty().get();
	}

	public Paint getHourArrowStroke() {
		return hourArrowStrokeProperty().get();
	}

	public int getMinute() {
		return minuteProperty().get();
	}

	public Paint getMinuteArrowStroke() {
		return minuteArrowStrokeProperty().get();
	}

	public Mode getMode() {
		return modeProperty().get();
	}

	public int getSecond() {
		return (int) Math.floor(secondProperty().get());
	}

	public Paint getSecondArrowStroke() {
		return secondArrowStrokeProperty().get();
	}

	public Paint getTextFill() {
		return textFillProperty().get();
	}

	public void run(boolean value) {
		if (value) timeline.playFrom(Duration.millis(time.getSecond() * 1000 + time.getNano() / 1_000_000));
		else timeline.pause();
	}

	public void setAnimateModeChange(boolean animateModeChange) {
		this.animateModeChangeProperty().set(animateModeChange);
	}

	public void setArcFill(Paint arcFill) {
		this.arcFillProperty().set(arcFill);
	}

	public void setArcStroke(Paint arcStroke) {
		this.arcStrokeProperty().set(arcStroke);
	}

	public void setHour(int hour) {
		this.hourProperty().set(hour);
	}

	public void setHourArrowStroke(Paint hourArrowStroke) {
		this.hourArrowStrokeProperty().set(hourArrowStroke);
	}

	public void setMinute(int minute) {
		this.minuteProperty().set(minute);
	}

	public void setMinuteArrowStroke(Paint minuteArrowStroke) {
		this.minuteArrowStrokeProperty().set(minuteArrowStroke);
	}

	public void setMode(Mode mode) {
		this.modeProperty().set(mode);
	}

	public void setSecond(int second) {
		this.secondProperty().set(second);
	}

	public void setSecondArrowStroke(Paint secondArrowStroke) {
		this.secondArrowStrokeProperty().set(secondArrowStroke);
	}

	public void setTextFill(Paint textFill) {
		this.textFillProperty().set(textFill);
	}

	public void setTime(LocalTime time) {
		this.time = time;
		setHour(time.getHour());
		setMinute(time.getMinute());
		setSecond(time.getSecond());
	}

	public enum Mode {
		ANALOG, DIGITAL
	}
}
