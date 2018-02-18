package com.example.hp.ringerontimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by HP on 1/30/2018.
 */

public class set_alarm_Activity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        AudioManager audioManager =(AudioManager) context.getSystemService(AUDIO_SERVICE);
        int mode=audioManager.getRingerMode();
        //issue only works for vibrate mode does not work for silent mode
        if(mode==1)
        {
            audioManager.setRingerMode(2);
        }




    }



}
