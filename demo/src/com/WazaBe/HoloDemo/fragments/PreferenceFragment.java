package com.WazaBe.HoloDemo.fragments;

import android.os.Bundle;

import com.WazaBe.HoloDemo.R;

public class PreferenceFragment extends
		com.WazaBe.HoloEverywhere.preference.PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
