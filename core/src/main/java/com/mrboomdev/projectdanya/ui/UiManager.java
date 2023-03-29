package com.mrboomdev.projectdanya.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.mrboomdev.projectdanya.ui.screens.splash.SplashScreen;

public class UiManager extends Game {
	public AssetManager assets;
	private static UiManager instance;
	
	public static UiManager getInstance() {
		if(instance == null) instance = new UiManager();
		return instance;
	}
	
	private UiManager() {
		assets = new AssetManager();
	}

    @Override
    public void create() {
		setScreen(new SplashScreen());
	}
}
