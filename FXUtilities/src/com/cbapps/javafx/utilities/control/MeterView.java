package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.MeterViewSkin;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MeterView extends Control {
	
	private final StringProperty unit = new SimpleStringProperty("");
	
	/**The angle of the meter's base, from 0-360.*/
	private final DoubleProperty angle = new SimpleDoubleProperty(270);
	
	/**
     * The minimum value represented by this Meter. This must be a
     * value less than {@link #maxProperty() max}.
     */
	private final DoubleProperty min = new SimpleDoubleProperty(0);
	
	/**
     * The maximum value represented by this Meter. This must be a
     * value greater than {@link #minProperty() max}.
     */
	private final DoubleProperty max = new SimpleDoubleProperty(100);
	
	/**
     * The current value represented by this Meter.
     */
	private final DoubleProperty value = new SimpleDoubleProperty(0);
	
	private final ObjectProperty<Paint> color = 
			new SimpleObjectProperty<Paint>(Color.BLACK);
	
	public DoubleProperty angleProperty() {
		return angle;
	}
	
	public DoubleProperty maxProperty() {
		return max;
	}
	
	public DoubleProperty minProperty() {
		return min;
	}
	
	public StringProperty unitProperty() {
		return unit;
	}
	
	public DoubleProperty valueProperty() {
		return value;
	}
	
	public ObjectProperty<Paint> colorProperty() {
		return color;
	}
	
	public MeterView() {
		setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
	}
	
	public MeterView(double min, double max, double value) {
		setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		setMin(min);
		setMax(max);
		setValue(value);
	}
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new MeterViewSkin(this);
	}
	
	public Paint getColor() {
		return colorProperty().get();
	}
	
	public double getAngle() {
		return angleProperty().get();
	}
	
	public double getMax() {
		return maxProperty().get();
	}
	
	public double getMin() {
		return minProperty().get();
	}
	
	public String getUnit() {
		return unitProperty().get();
	}
	
	public double getValue() {
		return valueProperty().get();
	}
	
	public void setColor(Paint c) {
		colorProperty().set(c);
	}
	
	public void setAngle(double d) {
		angleProperty().set(d);
	}
	
	public void setMax(double d) {
		maxProperty().set(d);
	}
	
	public void setMin(double d) {
		minProperty().set(d);
	}
	
	public void setUnit(String u) {
		unitProperty().set(u);
	}
	
	public void setValue(double d) {
		valueProperty().set(d);
	}
}
