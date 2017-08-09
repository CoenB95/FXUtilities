package com.cbapps.javafx.utilities.animation;

import javafx.animation.AnimationTimer;

public class AnimationLine extends AnimationTimer {

	long start_time;
	
	public void addAnimationAt() {
		
	}

	@Override
	public void start() {
		start_time = System.nanoTime();
		super.start();
	}
	
	@Override
	public void handle(long now) {
		System.out.println("Start: " + start_time + " Now: " + now + " Time: " + 
				((now-start_time)/1000000));
		if ((now-start_time)/1000000 >= 3000) {
			System.out.println("Stopped.");
			stop();
		}
	}
}
