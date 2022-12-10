package com.example.kt1_2;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;


public class InsertSongActivity extends AppCompatActivity {
    Spinner spnAuthor;
    EditText edtNameBook;
    EditText edtDateBook;
    Button btnChoseDayBook,btnAddBook;
    ListView listViewBook;
    Database dataSql;
    ArrayList<Song> listSong = new ArrayList<>();
    SongAdapter adapterSong ;
    //////////////////// GET DATE

    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        edtDateBook.setText(fmtDateAndTime.format(myCalendar.getTime()));
    }
    /////////////////////END
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_song);
        mapIdToView();
        setupView();
    }

    private void setupView() {
        setupSpinerAuthor();
        btnChoseDayBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InsertSongActivity.this, d,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddBookClicked();

            }
        });
        spnAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idAuthor;
                idAuthor =getIdAuthorFromSpinner();

                showListSong(idAuthor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private String getIdAuthorFromSpinner(){
        String idAuthor = "null";
        String nameAuthor = spnAuthor.getSelectedItem().toString();
        ArrayList<Album> listDataAuthor = new ArrayList<>();
        listDataAuthor = dataSql.getAllAlbum();
        for(Album author: listDataAuthor){
            if(author.getNameAlbum().equals(nameAuthor)){
                idAuthor = author.getIdAlbum();
            }
        }
        return idAuthor;
    }

    private void showListSong(String idAuthor) {
//        ArrayList<Song> newlist = new ArrayList<>();
//        listSong = dataSql.getAllSong();
//        for(int i=0;i<listSong.size();i++){
//            if(listSong.get(i).getIdAuthor().equals(idAuthor)){
//                newlist.add(listSong.get(i));
//            }
//        }
//        adapterSong = new SongAdapter(InsertSongActivity.this,newlist);
//        listViewBook.setAdapter(adapterSong);
//        adapterSong.notifyDataSetChanged();
    }


    private void btnAddBookClicked(){
//        Song song ;
//        String idAuthor;
//        idAuthor = getIdAuthorFromSpinner();
//        String nameSong = edtNameBook.getText().toString();
//        String dateSong  = edtDateBook.getText().toString();
//        song = new Song(idAuthor,dateSong,nameSong);
//        dataSql.addSong(song);
//        showListSong(idAuthor);
    }
    private void setupSpinerAuthor() {
        dataSql = new Database(this);
        ArrayList<Album> listDataAuthor = new ArrayList<>();
        listDataAuthor = dataSql.getAllAlbum();
        ArrayList<String> listNameAuthor = new ArrayList<>();
        for(Album author: listDataAuthor){
            listNameAuthor.add(author.getNameAlbum());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listNameAuthor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAuthor.setAdapter(adapter);

    }

    private void mapIdToView() {
        spnAuthor = findViewById(R.id.spinnerAuthor);
        edtNameBook = findViewById(R.id.edtNameBook);
        edtDateBook = findViewById(R.id.edtDateBook);
        btnChoseDayBook = findViewById(R.id.btnChoseDay);
        btnAddBook = findViewById(R.id.btnAddBook);
        listViewBook = findViewById(R.id.listview_Book);
    }
}