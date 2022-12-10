package com.example.kt1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayAlbumActivity extends AppCompatActivity {
    //ArrayList<Album> albums ;
    ListView listViewAlbum;
    ArrayList<Album> listAlbum = new ArrayList<>();
    Database dataSql = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_album);
        listViewAlbum= findViewById(R.id.lvAlbum);
        setDataToView();
    }
//    public void receiData(){
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("Bundle");
//        data = (ArrayList<Album>) (bundle.getSerializable("LISTALBUM"));
//        //Log.d("hhhh",data.getAllAlbum().get(0).getCodeAlbum().toString());
//    }
    private AlbumAdapter createListViewAuthor() {
        updateDataFromSQL();
        AlbumAdapter adapter = new AlbumAdapter(this,listAlbum);
        listViewAlbum.setAdapter(adapter);
        return adapter;
    }
    private void updateDataFromSQL() {
        listAlbum=dataSql.getAllAlbum();
    }
    private void setDataToView(){
        //receiData();
        AlbumAdapter adapter = createListViewAuthor();
        listViewAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DisplayAlbumActivity.this, "Clicked to item at " + (position + 1), Toast.LENGTH_SHORT).show();
                itemAlbumClicked(view,position,adapter);
            }
        });
//        listViewAlbum.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                itemAlbumLongClicked(view,position);
//                return true;
//            }
//        });
    }


    private void itemAlbumClicked(View view,int positon,AlbumAdapter adapter){
        Album album;
        ArrayList<Album> listAlbum = new ArrayList<>();
        listAlbum= dataSql.getAllAlbum();
        album = listAlbum.get(positon);
        ArrayList<Album> finalListAlbum = listAlbum;
        EditAlbumDialog.EditAlbumListener listener = new EditAlbumDialog.EditAlbumListener() {
            @Override
            public void btnUpdateEntered(String codeAlbum, String nameAlbum,String age) {

                String idAlbum = finalListAlbum.get(positon).getIdAlbum();
                dataSql.deleteAlbum(idAlbum);
                createListViewAuthor();
            }
        };
        final EditAlbumDialog dialog = new EditAlbumDialog(this, listener,album);

        dialog.show();
        dialog.getWindow().setLayout(1400, 1000);
    }
//    private void itemAlbumLongClicked(View view, int position){
//        DeleteAlbumDialog.DeleteAlbumListener listener = new DeleteAlbumDialog.DeleteAlbumListener() {
//            @Override
//            public void btnDeleteEntered() {
//
//            }
//
//
//        };
//        final DeleteAlbumDialog dialog = new DeleteAlbumDialog(this, listener);
//        dialog.getWindow().setLayout(1000, 700);
//        dialog.show();
//    }


}