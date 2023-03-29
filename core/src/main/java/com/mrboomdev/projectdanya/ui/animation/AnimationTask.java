package com.mrboomdev.projectdanya.ui.animation;

import com.badlogic.gdx.utils.Array;

public class AnimationTask {
	public Array<AnimationData> keyFrames = new Array<>();
	
	public float getValue(float progress) {
		var current = keyFrames.get(0);
		for(var keyFrame : keyFrames) {
			if(keyFrame.onSecond > current.onSecond && progress > keyFrame.onSecond) {
				current = keyFrame;
			}
		}
		return current.getProgress(progress);
	}
	
	public AnimationTask build() {
		return this;
	}
	
	public static class AnimationData {
		public Runnable completedCallback;
		public boolean isCompleted;
		public float from, to, duration, onSecond;
		
		public float getProgress(float progress) {
			if(progress < onSecond) return from;
			if(progress > onSecond + duration) {
				if(!isCompleted) {
					if(completedCallback != null) completedCallback.run();
					isCompleted = true;
				}
				return to;
			}
			return from + (duration - (duration - (progress - onSecond))) * ((to - from) / duration);
		}
		
		public AnimationData cpy() {
			var copy = new AnimationData();
			copy.completedCallback = completedCallback;
			copy.isCompleted = isCompleted;
			copy.from = from;
			copy.to = to;
			copy.duration = duration;
			copy.onSecond = onSecond;
			return copy;
		}
	}
	
	public static class TaskBuilder {
		protected AnimationUtil owner;
		protected String id;
		private AnimationTask task;
		private AnimationData data;
		
		public TaskBuilder() {
			this.id = id;
			this.task = new AnimationTask();
			this.data = new AnimationData();
		}
		
		public TaskBuilder onSecond(float second) {
			data.onSecond = second;
			return this;
		}
		
		public TaskBuilder fromTo(float from, float to) {
			data.from = from;
			data.to = to;
			return this;
		}
		
		public TaskBuilder withDuration(float duration) {
			data.duration = duration;
			return this;
		}
		
		public TaskBuilder onFinish(Runnable runnable) {
			data.completedCallback = runnable;
			return this;
		}
		
		public TaskBuilder build() {
			task.keyFrames.add(data.cpy());
			this.data = new AnimationData();
			owner.addTask(id, task.build());
			return this;
		}
	}
}