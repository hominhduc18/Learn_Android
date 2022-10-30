package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail;
    private  EditText txtPass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = (EditText) this.findViewById(R.id.email);
        txtPass = (EditText) this.findViewById(R.id.password);
        btnLogin = (Button) this.findViewById(R.id.login);
        this.btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    protected void Login() {
        if(this.txtEmail.getText().toString().equals("google@gmail.com") && this.txtPass.getText().toString().equals("123")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Log.d("test",this.txtEmail.getText().toString() );
        }
        else {
            Log.d("test","failed" );
        }
    }
}