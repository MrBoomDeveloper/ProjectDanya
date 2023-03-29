package com.mrboomdev.projectdanya.ui.animation;

import com.badlogic.gdx.utils.ObjectMap;

public class AnimationUtil {
	protected ObjectMap<String, AnimationTask> tasks = new ObjectMap<>();
	private float progress;
	
	public void addTask(String id, AnimationTask task) {
		tasks.put(id, task);
	}
	
	public AnimationTask.TaskBuilder createTask(String id) {
		var builder = new AnimationTask.TaskBuilder();
		builder.owner = this;
		builder.id = id;
		return builder;
	}
	
	public float getValue(String id) {
		return tasks.get(id).getValue(progress);
	}
	
	public void update(float delta) {
		progress += delta;
	}
}