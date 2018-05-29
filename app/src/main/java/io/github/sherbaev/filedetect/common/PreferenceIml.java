package io.github.sherbaev.filedetect.common;

import android.content.SharedPreferences;

public class PreferenceIml implements IPreference {
    private static final String TAG = "PreferenceIml";
    private static final String VERSIONS = "";
    private static final String COINS = "";
    private static final String LEVEL = "";


    private SharedPreferences preferences;

    public PreferenceIml(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    private void saveInteger(String key, int val) {
        preferences.edit().putInt(key, val).apply();
    }

    private int loadInteger(String key) {
        return preferences.getInt(key, 0);
    }

    @Override public void saveVersion(int version) {
        saveInteger(VERSIONS, version);
    }

    @Override public int loadVersion() {
        return loadInteger(VERSIONS);
    }
}