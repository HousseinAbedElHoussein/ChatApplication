package com.example.housseinabedelhoussein;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomListview extends ArrayAdapter<User> {
    private ArrayList<User> listusers;
    private Activity context;

    public CustomListview(Activity context, ArrayList<User> users) {
        super(context, R.layout.listview,users);
        this.context=context;
        this.listusers=users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        ViewHolder viewHolder=null;
        if(v==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            v=layoutInflater.inflate(R.layout.listview,null,true);
            viewHolder=new ViewHolder(v);
            v.setTag(viewHolder);
        }
        else
        {
             viewHolder=(ViewHolder)v.getTag();

        }

        viewHolder.img1.setImageResource(listusers.get(position).ImageID);
        viewHolder.text1.setText(listusers.get(position).Name);
        viewHolder.text2.setText(listusers.get(position).LastMessage);
        viewHolder.text3.setText(listusers.get(position).LastSendDate);

        return v;
    }


    class   ViewHolder{
        TextView text1;
        TextView text2;
        ImageView img1;
        TextView text3;
        ViewHolder(View v){
            text1=v.findViewById(R.id.Name);
            text2=v.findViewById(R.id.firstchat);
            text3=v.findViewById(R.id.Date);
            img1=v.findViewById(R.id.imageView);

        }
    }
}
