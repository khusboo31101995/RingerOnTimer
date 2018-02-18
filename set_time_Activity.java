package com.example.hp.ringerontimer;

import android.accessibilityservice.AccessibilityService;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by HP on 1/30/2018.
 */

public class set_time_Activity extends AppCompatActivity {
    TimePicker timePicker;
    Button setalarm;
    Button cancelalarm;
    AlarmManager alarmManager;
    Intent intent;
    PendingIntent pendingIntent;
    Button view;
    Intent intent22;
    SharedPreferences pref;
    int i=0;
    String id="";
    boolean doublebacktoexit=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_time);
        pref=getApplicationContext().getSharedPreferences("MyPref",0);
        final SharedPreferences.Editor editor=pref.edit();
        setalarm = (Button) findViewById(R.id.button);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        view=(Button)findViewById(R.id.view);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(getApplicationContext(), set_alarm_Activity.class);
        intent22=new Intent(set_time_Activity.this,view_alarm_Activity.class);


        setalarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                i = (int) System.currentTimeMillis();
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),i, intent, 0);
                int h=timePicker.getHour();
                int m=timePicker.getMinute();
                Toast.makeText(set_time_Activity.this, "The Ringer Mode has been set.\n"+h+":"+m, Toast.LENGTH_SHORT).show();
                 Calendar calender= Calendar.getInstance();

                calender.setTimeInMillis(System.currentTimeMillis());
                calender.set(Calendar.HOUR_OF_DAY,h );
                calender.set(Calendar.MINUTE,m);
                String tt=h+":"+m;
                id=UUID.randomUUID().toString();
                time t=new time(tt,id,i);
                Gson gson=new Gson();
                String json=gson.toJson(t);
                editor.putString(id,json);
                editor.commit();
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY, pendingIntent);
                startActivity(intent22);

            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(intent22);

            }
        });
    }

   /* @Override
    public void onBackPressed() {

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);


    }*/

}
