package io.github.sherbaev.filedetect;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.sherbaev.filedetect.common.IPreference;
import io.github.sherbaev.filedetect.common.PreferenceIml;


public class BaseActivity extends AppCompatActivity {
    public IPreference preference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new PreferenceIml(PreferenceManager.getDefaultSharedPreferences(this));
    }

    public void startActivity(Class<? extends AppCompatActivity> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
