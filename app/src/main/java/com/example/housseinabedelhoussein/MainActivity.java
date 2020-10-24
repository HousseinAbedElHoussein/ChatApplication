package com.example.housseinabedelhoussein;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;
    ListView listview;
    ArrayList<User> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=(ListView)findViewById(R.id.ListView);
         listItem =new ArrayList<User>();
         db= new DatabaseHandler(this);

            for (int i = 0; i < 200; i++) {
                User user = new User();
                user.Name = "UserName" + i;
                user.ImageID = R.drawable.image;
                user.LastMessage = "";
                user.LastSendDate = "";
                db.AddUser(user);

            }

            listItem=  db.GetAllUser();
            CustomListview customListview=new CustomListview(this,listItem);
            listview.setAdapter(customListview);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this  , chatView.class);
                intent.putExtra("Id",String.valueOf(listItem.get(position).Id) );
                intent.putExtra("Name", listItem.get(position).Name);
                startActivity(intent);

            }
        });
    }
}