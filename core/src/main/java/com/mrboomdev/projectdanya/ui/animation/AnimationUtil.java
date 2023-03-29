package com.mrboomdev.projectdanya.ui.animation;

import com.badlogic.gdx.utils.ObjectMap;

public class AnimationUtil {
	protected ObjectMap<String, AnimationTask> tasks = new ObjectMap<>();
	
	public AnimationTask addTask(String id) {
		var task = new AnimationTask();
		tasks.put(id, task);
		return task;
	}
	
	public float getValue(String id) {
		return tasks.get(id).progress;
	}
}