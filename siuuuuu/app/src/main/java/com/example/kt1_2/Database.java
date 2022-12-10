package com.example.kt1_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Manager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_AUTHOR = "authors";
    private static final String TABLE_SONG = "songs";

    private static final String KEY_CODE_AUTHOR = "code_nv";
    private static final String KEY_NAME_AUTHOR = "name_author";
    private static final String KEY_AGE = "age";
    private static final String KEY_ID_AUTHHOR = "id_author";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_author_table = String.format("CREATE TABLE %s(%s TEXT, %s TEXT,%s TEXT,%s TEXT)", TABLE_AUTHOR,KEY_ID_AUTHHOR, KEY_CODE_AUTHOR, KEY_NAME_AUTHOR,KEY_AGE);
        db.execSQL(create_author_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_author_table = String.format("DROP TABLE IF EXISTS %s", TABLE_AUTHOR);
        db.execSQL(drop_author_table);
        onCreate(db);
    }


    public void addAlbum(Album album) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_AUTHHOR, album.getIdAlbum());
        values.put(KEY_CODE_AUTHOR, album.getCodeAlbum());
        values.put(KEY_NAME_AUTHOR, album.getNameAlbum());
        values.put(KEY_AGE, album.getAge());
        db.insert(TABLE_AUTHOR, null, values);
        db.close();
    }

    public Album getAlbum(String idAlbum) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_AUTHOR, null, KEY_ID_AUTHHOR + " = ?", new String[] { idAlbum },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Album album = new Album(cursor.getString(0), cursor.getString(1),cursor.getString(2));
        return album;
    }
    public ArrayList<Album> getAllAlbum() {
        ArrayList<Album>  albumList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_AUTHOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            Album album = new Album(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3));
            albumList.add(album);
            cursor.moveToNext();
        }
        return albumList;

    }

    public void deleteAlbum(String idAlbum) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUTHOR, KEY_ID_AUTHHOR + " = ?", new String[] { idAlbum });
        db.close();
    }

}

