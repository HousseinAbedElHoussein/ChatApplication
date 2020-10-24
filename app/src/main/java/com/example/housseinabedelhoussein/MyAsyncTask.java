package com.example.housseinabedelhoussein;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Chat, Chat, Void> {
    private ListView listview;
    ArrayList<String> list;
    DatabaseHandler db;
    ArrayAdapter adapter;
    Context context;

    public MyAsyncTask(ListView listView,ArrayList<String> l, ArrayAdapter adapter, Context context) {
        this.listview = listView;
        this.context = context;
        this.adapter=adapter;
        this.list=l;
    }
    @Override
    protected Void doInBackground(Chat... params) {

        //sleep
        try {
            Thread.sleep((1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publishProgress(params[0]);

        return null;
    }
    @Override
    protected void onProgressUpdate(Chat... progress) {
        super.onProgressUpdate();
        db= new DatabaseHandler(context);
        db.AddChat(progress[0]);
        db.update(progress[0].IdSender,progress[0].MessageDate,progress[0].Message);
        // Update the UI
       list.add(progress[0].Message);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);
    }
}
