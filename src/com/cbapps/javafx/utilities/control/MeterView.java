package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.MeterSkin;
import com.cbapps.javafx.utilities.skin.MeterViewSkin;

import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MeterView extends Control {

	private StringProperty name = new SimpleStringProperty();

	/**The format used to display the textual representation of this
	 * Meter's {@link #valueProperty()}*/
	private final StringProperty format = new SimpleStringProperty("%.0f");
	
	/**The angle of the meter's base, from 0-360.*/
	private final DoubleProperty angle = new SimpleDoubleProperty(270);
	
	/**
     * The minimum value represented by this Meter. This must be a
     * value less than {@link #maxProperty() max}.
     */
	private final DoubleProperty min = new DoublePropertyBase() {
		@Override
		protected void invalidated() {
			if (getMax() < get()) setMax(get());
			checkValueBounds();
		}

		@Override
		public Object getBean() {
			return MeterView.this;
		}

		@Override
		public String getName() {
			return "min";
		}
	};
	
	/**
     * The maximum value represented by this Meter. This must be a
     * value greater than {@link #minProperty() max}.
     */
	private final DoubleProperty max = new DoublePropertyBase() {
		@Override
		protected void invalidated() {
			if (getMin() > get()) setMin(get());
			checkValueBounds();
		}

		@Override
		public Object getBean() {
			return MeterView.this;
		}

		@Override
		public String getName() {
			return "max";
		}
	};
	
	/**
     * The current value represented by this Meter.
     */
	private final DoubleProperty value = new DoublePropertyBase() {
		@Override
		protected void invalidated() {
			checkValueBounds();
		}

		@Override
		public Object getBean() {
			return MeterView.this;
		}

		@Override
		public String getName() {
			return "value";
		}
	};
	
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

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty textFormatProperty() {
		return format;
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

	private void checkValueBounds() {
		if (getValue() < getMin()) setValue(getMin());
		else if (getValue() > getMax()) setValue(getMax());
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new MeterSkin(this);
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

	public String getName() {
		return name.get();
	}

	public String getTextFormat() {
		return textFormatProperty().get();
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

	public void setName(String name) {
		this.name.set(name);
	}

	public void setTextFormat(String u) {
		textFormatProperty().set(u);
	}
	
	public void setValue(double d) {
		valueProperty().set(d);
	}
}
