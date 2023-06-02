package com.mrboomdev.projectdanya;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mrboomdev.projectdanya.ui.UiManager;

public class AndroidLauncher extends AndroidApplication {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		var config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		config.useCompass = false;
		initialize(UiManager.getInstance(), config);
	}
	
	@Override
	public void onBackPressed() {}
}