package com.example.kt1_2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomDialog extends Dialog {

    interface SaveAlbumListener {
        void btnSaveEntered(String codeAlbum, String nameAlbum,String age);
    }

    public Context context;

    private Button btnDelete,btnSave;
    private EditText etCodeAlbum,etNameAlbum,etAge;

    private SaveAlbumListener listener;

    public CustomDialog(Context context, SaveAlbumListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_album_dialog);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);
        etCodeAlbum = findViewById(R.id.etCodeAlbum);
        etNameAlbum = findViewById(R.id.etNameAlbum);
        etAge = findViewById(R.id.etAge);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSaveClick();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteClick();
            }
        });


    }

    private void btnSaveClick()  {
        String codeAlbum = etCodeAlbum.getText().toString();
        String nameAlbum = etNameAlbum.getText().toString();
        String age= etAge.getText().toString();
        if(codeAlbum== null || nameAlbum==null)  {
            Toast.makeText(this.context, "Error", Toast.LENGTH_LONG).show();
            return;
        }
        this.dismiss();

        if(this.listener!= null)  {
            this.listener.btnSaveEntered(codeAlbum,nameAlbum,age);
        }
    }

    private void btnDeleteClick()  {
        etNameAlbum.setText("");
        etCodeAlbum.setText("");

    }
}
