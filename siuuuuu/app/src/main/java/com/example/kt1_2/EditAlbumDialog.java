package com.example.kt1_2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAlbumDialog extends Dialog {

    interface EditAlbumListener {
        void btnUpdateEntered(String codeAlbumUpdate, String nameAlbumUpdate,String age);
    }

    public Context context;

    private Button btnDeleteUpdate,btnUpdate;
    private EditText etCodeAlbumUpdate,etNameAlbumUpdate,etAge;

    private EditAlbumListener listener;
    private Album album;
    public EditAlbumDialog(Context context, EditAlbumListener listener,Album album) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.album = album;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_album_dialog);

        btnDeleteUpdate = findViewById(R.id.btnDeleteUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        etCodeAlbumUpdate = findViewById(R.id.etCodeAlbumUpdate);
        etCodeAlbumUpdate.setText(album.getCodeAlbum());
        etNameAlbumUpdate = findViewById(R.id.etNameAlbumUpdate);
        etNameAlbumUpdate.setText(album.getNameAlbum());
        etAge = findViewById(R.id.etAgeShow);
        etAge.setText(album.getAge());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdateClick();
            }
        });
        btnDeleteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteClick();
            }
        });
    }

    private void btnUpdateClick()  {
        String codeAlbumUpdate = etCodeAlbumUpdate.getText().toString();
        String nameAlbumUpdate = etNameAlbumUpdate.getText().toString();
        if(codeAlbumUpdate== null || nameAlbumUpdate==null)  {
            return;
        }
        this.dismiss();

        if(this.listener!= null)  {
            this.listener.btnUpdateEntered(codeAlbumUpdate,nameAlbumUpdate,nameAlbumUpdate);
        }
    }
    private void btnDeleteClick()  {
        dismiss();
    }
}
