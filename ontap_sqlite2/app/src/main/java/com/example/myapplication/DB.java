package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    private Context context;
    public DB( Context context) {
        super(context, "NguyenHieuDucAn_15071971", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists project(id integer primary key autoincrement,projectname nvarchar(200),deadline nvarchar(10),name nvarchar(200));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table project");
        onCreate(db);
    }

    public long insertPJ(Project project) {
        long n = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("projectname", project.getProjectname());
        values.put("deadline", project.getDeadline());
        values.put("name", project.getName());
        n = database.insert("project", null, values);
        return n;
    }

    public ArrayList<Project> getAllPJ() {
        ArrayList<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setId(cursor.getInt(0));
                project.setProjectname(cursor.getString(1));
                project.setDeadline(cursor.getString(2));
                project.setName(cursor.getString(3));
                projects.add(project);
            } while (cursor.moveToNext());
        }
        database.close();
        return projects;
    }

    public long deletePJ(String id) {
        long n = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        n = database.delete("project", "id=?", new String[]{id});
        database.close();
        return n;
    }

    public long updatePJ(String id, Project project) {
        long n = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("projectname", project.getProjectname());
        values.put("deadline", project.getDeadline());
        values.put("name", project.getName());
        n = database.update("project", values, "id=?", new String[]{id});
        return n;
    }

    public ArrayList<Project> search(String query) {
        ArrayList<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project where projectname like '%"+query+"%' or deadline like '%"+query+"%' or name like '%"+query+"%';";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setId(cursor.getInt(0));
                project.setProjectname(cursor.getString(1));
                project.setDeadline(cursor.getString(2));
                project.setName(cursor.getString(3));
                projects.add(project);
            } while (cursor.moveToNext());
        }
        database.close();
        return projects;
    }
}
