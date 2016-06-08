package com.frogoutofwell.yullfrogapplication;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Tacademy on 2016-06-08.
 */
public class MyGcmListenerService extends GcmListenerService{
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String result = data.getString("data1");
        String result2 = data.getString("data2");
        Log.e("result",result+", "+result2);
    }
}
