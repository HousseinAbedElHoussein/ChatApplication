package com.example.housseinabedelhoussein;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION=1;
    //DatabaseName
    private static final String DATABASE_Name="ChatManager";

    private static final String TABLE_CHAT="Chat";
    private static final String TABLE_Users="Users";

    public DatabaseHandler(Context c) {
        super(c, DATABASE_Name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE="CREATE TABLE "+TABLE_Users+"("+"id"+" INTEGER  PRIMARY KEY autoincrement,"+

                "name TEXT, "+"LastSendDate TEXT, "+"LastMessage TEXT, "+"ImageId INTEGER" + ")";
        String CREATE_CHAT_TABLE="CREATE TABLE "+TABLE_CHAT+"("+"id"+" INTEGER PRIMARY KEY autoincrement,"+
                 "Message TEXT, "+ "MessageDate TEXT, "+
                "USERID INTEGER)";
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_CHAT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHAT);
    }
    void AddChat(Chat c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("USERID",c.IdSender);
        values.put("Message",c.Message);
        values.put("MessageDate",c.MessageDate);
        db.insert(TABLE_CHAT,null,values);
        db.close();

    }

    void AddUser(User u)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("name",u.Name);
        values.put("LastSendDate",u.LastSendDate.toString());
        values.put("LastMessage",u.LastMessage);
        values.put("ImageId",u.ImageID);
        db.insert(TABLE_Users,null,values);
        db.close();

    }
    ArrayList<Chat> GetChat (int id) {
        SQLiteDatabase db = this .getReadableDatabase ();
        String query="SELECT * FROM "+ TABLE_CHAT+" WHERE USERID="+id;
        ArrayList<Chat> chats = new ArrayList <Chat>();

        Cursor cursor = db.rawQuery (query , null );


        if( cursor.moveToFirst ()){
            do {
                Chat c = new Chat( Integer.parseInt (cursor.getString (0)),
                        cursor.getString (1), cursor.getString (2));
                chats.add(c);
            }while (cursor.moveToNext ());
        }
            db.close ();
                return chats;
        }


    ArrayList<User> GetAllUser () {
        SQLiteDatabase db = this .getReadableDatabase ();
        String query="SELECT * FROM "+ TABLE_Users+" ORDER BY LastSendDate DESC" ;
        Cursor cursor = db.rawQuery (query , null );
        ArrayList<User> user = new ArrayList <User>();
        if( cursor.moveToFirst ()){
            do {
                User c = new User( Integer.parseInt (cursor.getString (0)),
                        cursor.getString (1), cursor.getString (2),cursor.getString (3),Integer.parseInt (cursor.getString (4)));
                user.add(c);
            }while (cursor.moveToNext ());
        }
        db.close ();
        return user;
    }
    public boolean update(int id, String date, String msg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("LastSendDate",date);
        contentValues.put("LastMessage",msg);
        db.update(TABLE_Users,contentValues,"id=?",new String[]{String.valueOf(id)});
        return true;
    }


}
