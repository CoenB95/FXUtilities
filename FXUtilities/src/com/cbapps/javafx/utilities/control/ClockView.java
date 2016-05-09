package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.ClockViewSkin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ClockView extends Control {

	private final DoubleProperty size = new SimpleDoubleProperty(35);
	private final ObjectProperty<Paint> color = 
			new SimpleObjectProperty<Paint>(Color.BLACK);
	
	public DoubleProperty sizeProperty() {
		return size;
	}
	
	public ObjectProperty<Paint> colorProperty() {
		return color;
	}
	
	public ClockView() {
		
	}
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new ClockViewSkin(this);
	}
	
	public Paint getColor() {
		return colorProperty().get();
	}
	
	public double getSize() {
		return sizeProperty().get();
	}
	
	public void setColor(Paint c) {
		colorProperty().set(c);
	}
	public void setSize(double d) {
		sizeProperty().set(d);
	}
}
