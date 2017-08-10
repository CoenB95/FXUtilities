package com.cbapps.javafx.utilities.control;

import com.cbapps.javafx.utilities.skin.MeterSkin;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

import java.util.IllegalFormatException;

public class MeterView extends Control {

	public static final String DEFAULT_TEXT_FORMAT = "%.0f";

	private StringProperty name = new SimpleStringProperty();
	
	/**The angle of the meter's base, from 0-360.*/
	private DoubleProperty angle;

	private ObjectProperty<Paint> arcFill;

	private ObjectProperty<Paint> arcStroke;

	private ObjectProperty<ArcType> arcType;

	private ObjectProperty<Paint> arrowFill;

	private ObjectProperty<Paint> arrowStroke;
	
	/**
     * The minimum value represented by this Meter. This must be a
     * value less than {@link #maxProperty() max}.
     */
	private DoubleProperty min;
	
	/**
     * The maximum value represented by this Meter. This must be a
     * value greater than {@link #minProperty() max}.
     */
	private DoubleProperty max;


	/**
	 * The format used to display the textual representation of this Meter's {@link #valueProperty()}.
	 * This format should contain space for at least 1 double (the value).
	 * If the format is illegal, the default format will be used.
	 * If the format is null, no text will be visible.
	 */
	private StringProperty textFormat;

	/**
     * The current value represented by this Meter.
     */
	private DoubleProperty value;

	public MeterView() {

	}

	public MeterView(double min, double max, double value) {
		setMin(min);
		setMax(max);
		setValue(value);
	}

	public final DoubleProperty angleProperty() {
		if (angle == null) {
			angle = new SimpleDoubleProperty(270);
		}
		return angle;
	}

	public final ObjectProperty<Paint> arcFillProperty() {
		if (arcFill == null) {
			arcFill = new SimpleObjectProperty<>(Color.TRANSPARENT);
		}
		return arcFill;
	}

	public final ObjectProperty<Paint> arcStrokeProperty() {
		if (arcStroke == null) {
			arcStroke = new SimpleObjectProperty<>(Color.BLACK);
		}
		return arcStroke;
	}

	public final ObjectProperty<ArcType> arcTypeProperty() {
		if (arcType == null) {
			arcType = new SimpleObjectProperty<>(ArcType.OPEN);
		}
		return arcType;
	}

	public final ObjectProperty<Paint> arrowFillProperty() {
		if (arrowFill == null) {
			arrowFill = new SimpleObjectProperty<>(Color.GRAY);
		}
		return arrowFill;
	}

	public final ObjectProperty<Paint> arrowStrokeProperty() {
		if (arrowStroke == null) {
			arrowStroke = new SimpleObjectProperty<>(Color.TRANSPARENT);
		}
		return arrowStroke;
	}

	public final DoubleProperty maxProperty() {
		if (max == null) {
			max = new DoublePropertyBase() {
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
		}
		return max;
	}
	
	public final DoubleProperty minProperty() {
		if (min == null) {
			min = new DoublePropertyBase() {
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
		}
		return min;
	}

	public final StringProperty nameProperty() {
		return name;
	}

	public final StringProperty textFormatProperty() {
		if (textFormat == null) {
			textFormat = new StringPropertyBase() {
				@Override
				protected void invalidated() {
					try {
						if (get() != null) {
							String test = String.format(get(), 12.34);
						}
					} catch (IllegalFormatException e) {
						set(DEFAULT_TEXT_FORMAT);
					}
				}

				@Override
				public Object getBean() {
					return MeterView.class;
				}

				@Override
				public String getName() {
					return "textFormat";
				}
			};
		}
		return textFormat;
	}
	
	public final DoubleProperty valueProperty() {
		if (value == null) {
			value = new DoublePropertyBase() {
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
		}
		return value;
	}

	private void checkValueBounds() {
		if (getValue() < getMin()) setValue(getMin());
		else if (getValue() > getMax()) setValue(getMax());
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new MeterSkin(this);
	}
	
	public final double getAngle() {
		return angleProperty().get();
	}

	public final Paint getArcFill() {
		return arcFillProperty().get();
	}

	public final Paint getArcStroke() {
		return arcStrokeProperty().get();
	}

	public final ArcType getArcType() {
		return arcTypeProperty().get();
	}

	public final Paint getArrowFill() {
		return arrowFillProperty().get();
	}

	public final Paint getArrowStroke() {
		return arrowStrokeProperty().get();
	}

	public final double getMax() {
		return maxProperty().get();
	}
	
	public final double getMin() {
		return minProperty().get();
	}

	public final String getName() {
		return nameProperty().get();
	}

	public final String getTextFormat() {
		return textFormatProperty().get();
	}
	
	public final double getValue() {
		return valueProperty().get();
	}
	
	public final void setAngle(double d) {
		angleProperty().set(d);
	}

	public final void setArcFill(Paint color) {
		arcFillProperty().set(color);
	}

	public final void setArcStroke(Paint c) {
		arcStrokeProperty().set(c);
	}

	public final void setArcType(ArcType arcType) {
		this.arcTypeProperty().set(arcType);
	}

	public final void setArrowFill(Paint arrowFill) {
		arrowFillProperty().set(arrowFill);
	}

	public final void setArrowStroke(Paint arrowStroke) {
		arrowStrokeProperty().set(arrowStroke);
	}

	public final void setMax(double d) {
		maxProperty().set(d);
	}
	
	public final void setMin(double d) {
		minProperty().set(d);
	}

	public final void setName(String name) {
		nameProperty().set(name);
	}

	public final void setTextFormat(String u) {
		textFormatProperty().set(u);
	}
	
	public final void setValue(double d) {
		valueProperty().set(d);
	}
}
