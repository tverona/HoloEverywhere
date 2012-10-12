package com.WazaBe.HoloEverywhere.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
<<<<<<< .merge_file_8CYjI3
import android.support.v4.app.FragmentActivity;
=======
import android.support.v4.app.Watson;
>>>>>>> .merge_file_U0Vew3
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.WazaBe.HoloEverywhere.FontLoader;
import com.WazaBe.HoloEverywhere.LayoutInflater;
import com.WazaBe.HoloEverywhere.Settings;
import com.WazaBe.HoloEverywhere.ThemeManager;
import com.WazaBe.HoloEverywhere.preference.PreferenceManager;
import com.WazaBe.HoloEverywhere.preference.SharedPreferences;

<<<<<<< .merge_file_8CYjI3
public abstract class Activity extends FragmentActivity implements Base {
=======
public abstract class Activity extends Watson implements Base {
>>>>>>> .merge_file_U0Vew3
	private boolean forceThemeApply = false;

	@Override
	public void addContentView(View view, LayoutParams params) {
		super.addContentView(FontLoader.apply(view), params);
	}

	@Override
	public LayoutInflater getLayoutInflater() {
		return LayoutInflater.from(this);
	}

	@Override
	public SharedPreferences getSupportSharedPreferences(String name, int mode) {
		return PreferenceManager.wrap(this, name, mode);
	}

	@Override
	public Object getSystemService(String name) {
		return LayoutInflater.getSystemService(super.getSystemService(name));
	}

	@Override
	@SuppressLint("NewApi")
	public void holoStartThemedActivity(Intent intent, Bundle options) {
		if (VERSION.SDK_INT >= 16) {
			super.startActivity(intent, options);
		} else {
			super.startActivity(intent);
		}
	}

	@Override
	public boolean isABSSupport() {
		return false;
	}

	@Override
	public boolean isForceThemeApply() {
		return forceThemeApply;
	}

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {
		if (!getSupportFragmentManager().popBackStackImmediate()) {
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getLayoutInflater().addFactory(this, 0);
		if (Settings.isUseThemeManager()) {
			ThemeManager.applyTheme(this);
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(FontLoader.inflate(this, layoutResID));
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(FontLoader.apply(view));
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(FontLoader.apply(view), params);
	}

	public void setForceThemeApply(boolean forceThemeApply) {
		this.forceThemeApply = forceThemeApply;
	}

	@Override
	public void startActivity(Intent intent) {
		if (Settings.isUseParentTheme()) {
			ThemeManager.startActivity(this, intent);
		} else {
			holoStartThemedActivity(intent, null);
		}
	}

	@Override
	public void startActivity(Intent intent, Bundle options) {
		if (Settings.isUseParentTheme()) {
			ThemeManager.startActivity(this, intent, options);
		} else {
			holoStartThemedActivity(intent, options);
		}
	}
}
