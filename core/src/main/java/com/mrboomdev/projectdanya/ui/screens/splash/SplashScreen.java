package com.mrboomdev.projectdanya.ui.screens.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mrboomdev.projectdanya.ui.UiManager;
import com.mrboomdev.projectdanya.ui.animation.AnimationUtil;
import com.mrboomdev.projectdanya.ui.screens.base.BaseScreen;
import com.mrboomdev.projectdanya.ui.screens.loading.LoadingScreen;

public class SplashScreen extends BaseScreen {
	private UiManager app = UiManager.getInstance();
	private SpriteBatch batch;
	private AnimationUtil animations;
	private Sprite teamLogo;
	
	public SplashScreen() {
		batch = new SpriteBatch();
		animations = new AnimationUtil();
		animations.createTask("logoFade")
			.onSecond(0).fromTo(0, 1).withDuration(.5f).build()
			.onSecond(2).fromTo(1, 0).withDuration(1).onFinish(() -> {
				app.setScreen(new LoadingScreen());
			}).build();
	}
	
	@Override
	public void show() {
		app.assets.load("ui/brand/team_logo.png", Texture.class);
		app.assets.finishLoading();
		teamLogo = new Sprite(app.assets.get("ui/brand/team_logo.png", Texture.class));
		teamLogo.setSize(450, 125);
		teamLogo.setCenter(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		animations.update(delta);
		batch.begin();
		teamLogo.setAlpha(animations.getValue("logoFade"));
		teamLogo.draw(batch);
		batch.end();
	}
}