package com.example.bai27;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class insert_album extends Activity {
    int day,month,year;
    AlbumAdapter2 albumAdapter;
    List<Album2> listAlbum;

    ArrayList<Album> albums;
    ArrayAdapter<Album> arrayAdapter;

    ListView lvBook;
    EditText etTenBH, etDate;
    Spinner spinner;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_album);

        lvBook = findViewById(R.id.listViewBook);
        etTenBH = findViewById(R.id.editTextTitle);
        etDate = findViewById(R.id.editTextDate);
        spinner = findViewById(R.id.spinner1);
        listAlbum = new ArrayList<>();
        albumAdapter = new AlbumAdapter2(this, listAlbum);
        lvBook.setAdapter(albumAdapter);

//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, albums);
//        /*arrayAdapter.notifyDataSetChanged();
//        spinner.setAdapter(arrayAdapter);*/
//
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);

        Button bChangeDate=(Button) findViewById(R.id.buttonDate);
        bChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(113);
            }
        });
        //them tac gia
        Button btnInsertBook =(Button) findViewById(R.id.buttonInsertBook);
        btnInsertBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TenBH = etTenBH.getText().toString();
                String Date = etDate.getText().toString();
                listAlbum.add(new Album2(TenBH, Date));
                albumAdapter.notifyDataSetChanged();

                /*EditText txtTitle=(EditText) findViewById(R.id.editTextTitle);
                ContentValues values=new ContentValues();
                values.put("title", txtTitle.getText().toString());
                Calendar c=Calendar.getInstance();
                c.set(year, month, day);
                SimpleDateFormat dfmt=new SimpleDateFormat("dd-MM-yyyy");
                values.put("dateadded",dfmt.format(c.getTime()));*/

            }
        });
    }
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if(id==113)
        {
            return new DatePickerDialog(this, dateChange, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener dateChange= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year1, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            year=year1;
            month=monthOfYear;
            day=dayOfMonth;
            EditText eDate=(EditText) findViewById(R.id.editTextDate);
            eDate.setText(day+"-"+(month+1)+"-"+year);
        }
    };
    /**
     * thiết lập ngày tháng năm hiện tại
     */
    public void setCurrentDateOnView()
    {
        EditText eDate=(EditText) findViewById(R.id.editTextDate);
        Calendar cal=Calendar.getInstance();
        day=cal.get(Calendar.DAY_OF_MONTH);
        month=cal.get(Calendar.MONTH);
        year=cal.get(Calendar.YEAR);
        eDate.setText(day+"-"+(month+1)+"-"+year);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_insert_album, menu);
        return true;
    }
}
