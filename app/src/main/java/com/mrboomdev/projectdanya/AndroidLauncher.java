package com.mrboomdev.projectdanya;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.itsaky.androidide.logsender.LogSender;
import com.mrboomdev.projectdanya.ui.UiManager;

public class AndroidLauncher extends AndroidApplication {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogSender.startLogging(this);
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
		configuration.useImmersiveMode = true;
		initialize(UiManager.getInstance(), configuration);
	}
	
	@Override
	public void onBackPressed() {
		
	}
}