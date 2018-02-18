package com.example.hp.ringerontimer;

/**
 * Created by HP on 2/12/2018.
 */

public class time {
    private String time;
    private String id;
    private int i;

    public time(String time,String id,int i)
    {
        this.time = time;
        this.id=id;
        this.i=i;
    }

    public String getTime() {
        return time;
    }
    public String getId(){
        return id;
    }
    public int getI(){return i;}
}
