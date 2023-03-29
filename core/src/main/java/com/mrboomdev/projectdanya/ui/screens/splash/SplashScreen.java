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
import com.mrboomdev.projectdanya.ui.screens.menu.MenuScreen;

public class SplashScreen extends BaseScreen {
	private UiManager game = UiManager.getInstance();
	private SpriteBatch batch;
	private AnimationUtil animations;
	private Sprite teamLogo;
	
	public SplashScreen() {
		batch = new SpriteBatch();
		animations = new AnimationUtil();
		animations.createTask("logoFade")
			.onSecond(0).fromTo(0, 1).withDuration(.5f).build()
			.onSecond(1.8f).fromTo(1, 0).withDuration(.7f).onFinish(() -> {
				game.assets.finishLoading();
				game.setScreen(new MenuScreen());
			}).build();
	}
	
	@Override
	public void show() {
		game.assets.load("ui/brand/team_logo.png", Texture.class);
		game.assets.finishLoading();
		teamLogo = new Sprite(game.assets.get("ui/brand/team_logo.png", Texture.class));
		teamLogo.setSize(450, 125);
		teamLogo.setCenter(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		game.assets.load("ui/screens/menu/background.png", Texture.class);
		game.assets.load("ui/screens/menu/danya/danya_1.png", Texture.class);
		game.assets.load("ui/screens/menu/danya/danya_2.png", Texture.class);
		game.assets.load("ui/screens/menu/danya/danya_3.png", Texture.class);
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
	
	@Override
	public void hide() {
		batch.dispose();
	}
}