package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.ClockViewSkin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ClockView extends Control {

	public static final int MODE_ANALOG = 1;
	public static final int MODE_DIGITAL = 2;
	
	public boolean animate_mode_change = true;
	
	private final IntegerProperty mode = new SimpleIntegerProperty(
			MODE_ANALOG);
	private final DoubleProperty size = new SimpleDoubleProperty(35);
	private final ObjectProperty<Paint> color = 
			new SimpleObjectProperty<Paint>(Color.BLACK);
	
	public DoubleProperty sizeProperty() {
		return size;
	}
	
	public ObjectProperty<Paint> colorProperty() {
		return color;
	}
	
	public IntegerProperty modeProperty() {
		return mode;
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
	
	public int getMode() {
		return mode.get();
	}
	
	public double getSize() {
		return sizeProperty().get();
	}
	
	public void setColor(Paint c) {
		colorProperty().set(c);
	}
	
	public void setMode(int m, boolean animate) {
		animate_mode_change = animate;
		mode.set(m);
	}
	
	public void setSize(double d) {
		sizeProperty().set(d);
	}
	
	/**Switch to the next mode.*/
	public void toggleMode() {
		int m = mode.get();
		if (m == MODE_ANALOG) m = MODE_DIGITAL;
		else m = MODE_ANALOG;
		mode.set(m);
	}
}
