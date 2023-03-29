package com.mrboomdev.projectdanya.ui.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mrboomdev.projectdanya.ui.UiManager;
import com.mrboomdev.projectdanya.ui.screens.base.BaseScreen;

public class MenuScreen extends BaseScreen {
	private UiManager game = UiManager.getInstance();
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Sprite background, danya;
	private int currentDanya;
	private float danyaPosition;
	private float changeLookProgress = 1;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(32, 18);
		viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		
		background = new Sprite(game.assets.get("ui/screens/menu/background.png", Texture.class));
		background.setSize(32, 18);
		danyaPosition = 25;
		changeLook();
	}

    @Override
    public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		{
			background.draw(batch);
			danya.setCenter(22, danyaPosition);
			danya.draw(batch);
		}
		batch.end();
		changeLook();
		danyaPosition -= delta * .7f;
		if(danyaPosition < -5) {
			danyaPosition = 25;
		}
	}
	
	private void changeLook() {
		changeLookProgress += Gdx.graphics.getDeltaTime();
		if(changeLookProgress > 0) {
			currentDanya += currentDanya < 3 ? 1 : -2;
			danya = new Sprite(game.assets.get("ui/screens/menu/danya/danya_" + currentDanya + ".png", Texture.class));
			danya.setSize(48, 70);
			changeLookProgress = -6 -(float)(Math.random() * 9);
		}
	}
	
	@Override
	public void hide() {
		batch.dispose();
	}
}