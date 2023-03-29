package com.mrboomdev.projectdanya.ui.animation;

import java.util.ArrayList;
import java.util.Collections;

public class AnimationTask {
	public ArrayList<AnimationData> keyFrames = new ArrayList<>();
	
	public float getValue(float progress) {
		var current = keyFrames.get(0);
		for(var keyFrame : keyFrames) {
			if(keyFrame.onSecond > current.onSecond) current = keyFrame;
		}
		return 1;
	}
	
	public AnimationTask build() {
		Collections.sort(keyFrames);
		return this;
	}
	
	public static class AnimationData implements Comparable<AnimationData> {
		private Runnable completedCallback;
		private boolean isCompleted;
		private float from, to, duration, onSecond;
		
		@Override
		public int compareTo(AnimationData data) {
			return 0;
		}
	}
	
	public static class TaskBuilder {
		protected AnimationUtil owner;
		protected String id;
		private AnimationTask task;
		
		public TaskBuilder() {
			this.id = id;
			this.task = new AnimationTask();
		}
		
		public TaskBuilder onSecond(float second) {
			return this;
		}
		
		public TaskBuilder fromTo(float from, float to) {
			return this;
		}
		
		public TaskBuilder withDuration(float duration) {
			return this;
		}
		
		public TaskBuilder onFinish(Runnable runnable) {
			return this;
		}
		
		public TaskBuilder build() {
			task.keyFrames.add(new AnimationData());
			owner.addTask(id, task.build());
			return this;
		}
	}
}