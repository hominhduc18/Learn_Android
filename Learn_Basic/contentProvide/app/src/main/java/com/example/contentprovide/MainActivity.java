package com.example.contentprovide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSION =1001;
    private static final int REQUEST_SMS_ASK_PERMISSION =1001;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvents();
        }

    private void addEvents() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulymanhinhdanhba();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                xulymanhinhtinnhan();
            }
        });
    }

    private void xulymanhinhtinnhan() {
    }

    private void xulymanhinhdanhba(){
        Intent intent  = new Intent(MainActivity.this,DanhBa.class);
        intent.setClassName("com.example.contentprovide","com.example.contentprovide.DanhBa");
        startActivity(intent);

    }


    private void addControl() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
    }
}