package com.mrboomdev.projectdanya.ui.animation;

public class AnimationTask {
	public float progress;
	private float from, to;
	
	public AnimationTask fromTo(float from, float to) {
		this.from = from;
		this.to = to;
		return this;
	}
	
	public static class TaskBuilder {
		
	}
}