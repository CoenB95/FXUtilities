package com.cbapps.javafx.utilities.animation;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

public class ArcTransition extends Transition {

	public ArcTransition(Duration d, Arc a) {
		durationProperty().set(d);
		setInterpolator(Interpolator.LINEAR);
		arcProperty().set(a);
	}
	
	private ObjectProperty<Arc> arc = new 
			SimpleObjectProperty<Arc>();
	
	public final void setArc(Arc value) {
		arcProperty().set(value);
	}
	
	public final Arc getArc() {
		return arcProperty().get();
	}
	
	public final ObjectProperty<Arc> arcProperty() {
		return arc;
	}
	
	public static final Duration DEFAULT_DURATION = Duration.millis(400);
	private ObjectProperty<Duration> duration = new 
			ObjectPropertyBase<Duration>(DEFAULT_DURATION){

		@Override
		protected void invalidated() {
			setCycleDuration(get());
		};
		
		@Override
		public Object getBean() {
			return ArcTransition.this;
		}

		@Override
		public String getName() {
			return "duration";
		}
		
	};
	
	public final void setDuration(Duration value) {
		durationProperty().set(value);
	}
	
	public final Duration getDuration() {
		return durationProperty().get();
	}
	
	public final ObjectProperty<Duration> durationProperty() {
		return duration;
	}
	
	private DoubleProperty fromStartAngle;
    private static final double DEFAULT_FROM_START_ANGLE = Double.NaN;

    public final void setFromStartAngle(double value) {
        if (!Double.isNaN(value)) {
            fromStartAngleProperty().set(value);
        }
    }

    public final double getFromStartAngle() {
        return fromStartAngleProperty().get();
    }

    public final DoubleProperty fromStartAngleProperty() {
        if (fromStartAngle == null) {
            fromStartAngle = new SimpleDoubleProperty(this, 
            		"fromStartAngle", 
            		DEFAULT_FROM_START_ANGLE);
        }
        return fromStartAngle;
    }
    
    private DoubleProperty toStartAngle;
    private static final double DEFAULT_TO_START_ANGLE = Double.NaN;

    public final void setToStartAngle(double value) {
        if (!Double.isNaN(value)) {
            toStartAngleProperty().set(value);
        }
    }

    public final double getToStartAngle() {
        return toStartAngleProperty().get();
    }

    public final DoubleProperty toStartAngleProperty() {
        if (toStartAngle == null) {
            toStartAngle = new SimpleDoubleProperty(this, 
            		"toStartAngle", 
            		DEFAULT_TO_START_ANGLE);
        }
        return toStartAngle;
    }
    
    private DoubleProperty fromLength;
    private static final double DEFAULT_FROM_LENGTH = Double.NaN;

    public final void setFromLength(double value) {
        if (!Double.isNaN(value)) {
            fromLengthProperty().set(value);
        }
    }

    public final double getFromLength() {
        return fromLengthProperty().get();
    }

    public final DoubleProperty fromLengthProperty() {
        if (fromLength == null) {
            fromLength = new SimpleDoubleProperty(this, "fromLength", 
            		DEFAULT_FROM_LENGTH);
        }
        return fromLength;
    }
    
    private DoubleProperty toLength;
    private static final double DEFAULT_TO_LENGTH = Double.NaN;

    public final void setToLength(double value) {
        if (!Double.isNaN(value)) {
            toLengthProperty().set(value);
        }
    }

    public final double getToLength() {
        return toLengthProperty().get();
    }

    public final DoubleProperty toLengthProperty() {
        if (toLength == null) {
            toLength = new SimpleDoubleProperty(this, "toLength", 
            		DEFAULT_TO_LENGTH);
        }
        return toLength;
    }
	
	@Override
	protected void interpolate(double frac) {
		Arc node = arcProperty().get();
		if (!Double.isNaN(getFromStartAngle()) || 
				!Double.isNaN(getToStartAngle())) {
			double angle_delta = getToStartAngle() - getFromStartAngle();
			node.setStartAngle(getFromStartAngle() + frac*angle_delta);
		}
		if (!Double.isNaN(getFromLength()) || 
				!Double.isNaN(getToLength())) {
			double length_delta = getToLength() - getFromLength();
			node.setLength(getFromLength() + frac*length_delta);
			//System.out.println("- results: " + 
			//getFromLength() + frac*length_delta);
		}
	}
}