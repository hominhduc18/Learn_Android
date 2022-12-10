package com.example.kt1_2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAlbumDialog extends Dialog {

    interface DeleteAlbumListener {
        void btnDeleteEntered();
    }

    public Context context;

    private Button btnCalcel,btnDeleteAuthor;

    private DeleteAlbumListener listener;

    public DeleteAlbumDialog(Context context, DeleteAlbumListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delete_album_dialog);

        btnCalcel = findViewById(R.id.btnCalcel_deleteDiaLog);
        btnDeleteAuthor = findViewById(R.id.btnDeleteAuthor);

        btnDeleteAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteAuthorClick();
            }
        });
        btnCalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalcelClick();
            }
        });
    }

    private void btnDeleteAuthorClick()  {

        if(this.listener!= null)  {
            this.listener.btnDeleteEntered();
        }
        this.dismiss();
    }
    private void btnCalcelClick()  {
        this.dismiss();
    }
}
