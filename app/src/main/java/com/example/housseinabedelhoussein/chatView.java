package com.example.housseinabedelhoussein;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class chatView extends AppCompatActivity {
    DatabaseHandler db;
    ArrayList<String> message;
    ArrayList<Chat> chats;
    ListView list;
    EditText e;
    ArrayAdapter<String> adapter;
    MyAsyncTask myAsyncTask;
    int id;
    String text="";
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        String NameUser = intent.getStringExtra("Name");
        id=Integer.parseInt(intent.getStringExtra("Id"));
        this.setTitle(NameUser);
        db= new DatabaseHandler(this);
        list=(ListView)findViewById(R.id.ListViewchat);
        e= findViewById(R.id.Message);
        b=findViewById(R.id.Send);

        chats=db.GetChat(id);
        message=new ArrayList<String>();
        if(chats!=null)
        {

            for(int i=0;i<chats.size();i++)
            {
                message.add(chats.get(i).Message);
            }
            adapter = new ArrayAdapter<String>(chatView.this, android.R.layout.simple_list_item_1, message);
            list.setAdapter(adapter);

        }
        b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {


                text=e.getText().toString();
                Date now=new Date();
                String date = new SimpleDateFormat("MM-dd-yyyy HH:mm:SS.ss").format(now);
                Chat c = new Chat(id,text,date);
                db.AddChat(c);
                message.add(c.Message);
                db.update(id,date,text);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                e.setText("");
                myAsyncTask = new MyAsyncTask(list,message,adapter,chatView.this);
                myAsyncTask.execute(c);
            }

        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                Intent in=new Intent(this, MainActivity.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}