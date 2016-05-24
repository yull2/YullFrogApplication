package com.frogoutofwell.yullfrogapplication.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.frogoutofwell.yullfrogapplication.MyApplication;

/**
 * Created by Tacademy on 2016-05-23.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance(){
        if (instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager(){
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPrefs.edit();
    }
}
