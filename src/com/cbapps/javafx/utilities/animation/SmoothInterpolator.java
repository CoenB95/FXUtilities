package com.cbapps.javafx.utilities.animation;

import javafx.animation.Interpolator;

public class SmoothInterpolator extends Interpolator {

	private AnimType type;

	public enum AnimType {ACCELERATE, DECELERATE, ACCELDECEL};

	public SmoothInterpolator(AnimType t) {
		type = t;
	}

	private double accel(double prog) {
		return (1.0 - Math.cos(prog*Math.PI/2));
	}

	private double decel(double prog) {
		return Math.sin(prog*Math.PI/2);
	}

	@Override
	protected double curve(double progress) {
		switch(type) {
			case ACCELERATE:
				return accel(progress);
			case DECELERATE:
				return decel(progress);
			case ACCELDECEL:
				return accel(progress * 2) / 2;
			default:
				return progress;
		}
	}

}
