package com.frogoutofwell.yullfrogapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import java.net.URL;

/**
 * Created by Tacademy on 2016-06-08.
 */
public class MyGcmListenerService extends GcmListenerService{
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String result = data.getString("data1");
        String result2 = data.getString("data2");
        Log.e("result",result+", "+result2);
/*

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 */
/* Request code *//*
, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setTicker("GCM message")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("GCM ChatMessage")
                .setContentText(result)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 */
/* ID of notification *//*
, notificationBuilder.build());

*/

    }
}
