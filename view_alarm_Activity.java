package com.example.hp.ringerontimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class view_alarm_Activity extends AppCompatActivity implements ItemClickListener {
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    List<time> Time;
    int h=0,m=0;
    String t="";
    String i="";
    int hu=0;
    SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alarm_);
        pref=getApplicationContext().getSharedPreferences("MyPref",0);
        Gson gson=new Gson();
        Time=new ArrayList<>();
        Map<String,?> keys=pref.getAll();
        for(Map.Entry<String,?>entry:keys.entrySet()) {
            String json = pref.getString(entry.getKey(), "");
            time tt = gson.fromJson(json, time.class);
            t = tt.getTime();
            i=tt.getId();
            hu=tt.getI();
            Time.add(
                    new time(t,i,hu)
            );
        }

        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recycleAdapter=new RecycleAdapter(this,Time);
        recycleAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setClickListener(this);

    }

    @Override
    public void onClick(View view, int position) {
        //delete an element from shared preferences
        SharedPreferences.Editor editor=pref.edit();
        time t=Time.get(position);
        String ii=t.getId();
        int hhg=t.getI();
        editor.remove(ii);
        editor.commit();
        Time.remove(position);
        recycleAdapter.notifyItemRemoved(position);
        recycleAdapter.notifyItemRangeChanged(position,Time.size());
        Intent k=new Intent(getApplicationContext(),set_alarm_Activity.class);
        PendingIntent p=PendingIntent.getBroadcast(getApplicationContext(),hhg,k,0);
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(p);
        Toast.makeText(this, "The set alarm has been deleted!", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.sett);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.sett) {
            Intent i = new Intent(view_alarm_Activity.this, set_time_Activity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        return true;
    }
}
