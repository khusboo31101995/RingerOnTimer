package com.example.hp.ringerontimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by HP on 2/12/2018.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>
{
    private ItemClickListener clickListener;
    private  Context ctx;
    private List<time> showtime;

    public RecycleAdapter(Context ctx, List<time> showtime) {
        this.ctx = ctx;
        this.showtime = showtime;
    }

    @Override

    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view=inflater.inflate(R.layout.row_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final time Time=showtime.get(position);
        holder.time.setText(Time.getTime());
        String p=(Time.getId());
        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Clicking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return showtime.size();
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView time;
        Button del;


        public MyViewHolder(View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.timeshow);
            del=itemView.findViewById(R.id.del);
            del.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v,getAdapterPosition());
        }
    }
}
