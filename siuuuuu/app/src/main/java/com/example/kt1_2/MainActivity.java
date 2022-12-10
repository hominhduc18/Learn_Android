package com.example.kt1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //ArrayList<Album> albums = new ArrayList<>();
    Button btnAddAlbum,btnDisplayAlbum,btnDisplaySong;
    Database data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapIdToView();
        data = new Database(this);
        btnAddAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddDialogClicked();
            }
        });
        btnDisplayAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intentSendAlbum = new Intent(getApplicationContext(),DisplayAlbumActivity.class);
                bundle.putSerializable("LISTALBUM", data.getAllAlbum());
                intentSendAlbum.putExtra("Bundle",bundle);
                startActivity(intentSendAlbum);
            }
        });
        btnDisplaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InsertSongActivity.class);
                startActivity(intent);
            }
        });
    }
    public void mapIdToView(){
        btnAddAlbum = findViewById(R.id.btnAddAlbum);
        btnDisplayAlbum = findViewById(R.id.btnDisplayAlbum);
        btnDisplaySong = findViewById(R.id.btnManagerSing);
    }
    private void btnAddDialogClicked()  {
        CustomDialog.SaveAlbumListener listener = new CustomDialog.SaveAlbumListener() {
            @Override
            public void btnSaveEntered(String codeAlbum,String nameAlbum,String age) {
                Album album = new Album(codeAlbum,nameAlbum,age);
                data.addAlbum(album);
                Toast.makeText(MainActivity.this,"Tạo tác giả thành công!!",Toast.LENGTH_SHORT);
                //albums.add(album);
            }
        };
        final CustomDialog dialog = new CustomDialog(this, listener);
        dialog.show();
        dialog.getWindow().setLayout(1400, 1000);
    }




}