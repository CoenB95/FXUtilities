package com.cbapps.javafx.utilities.animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class LayoutTimeline {

	public LayoutTimeline(LayoutFrame...frames) {
		Timeline time = new Timeline();
		time.getKeyFrames().add(new KeyFrame(Duration.ZERO, e -> {
			
		}));
		for (LayoutFrame frame:frames) {
			time.getKeyFrames().add(new KeyFrame(frame.duration, e -> {
				
			}));
		}
	}
	
	public static class LayoutFrame {
		
		private Duration duration;
		private FrameTransition type_in;
		private Node node;
		private FrameTransition type_out;
		
		public LayoutFrame(Duration d, FrameTransition in, Node n, 
				FrameTransition out) {
			duration = d;
			type_in = in;
			node = n;
			type_out = out;
		}
	}
	
	public enum FrameTransition {
		TOP, LEFT, BOTTOM, RIGHT, FADE
	}
}
