package com.example.baitapth_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Bai12Activity extends AppCompatActivity {

    CheckBox cbLuuTT;
    Button btnDangnhap, btnThoat;
    EditText etUser, etPassword;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai12);

        cbLuuTT = findViewById(R.id.cbLuuTT);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnThoat = findViewById(R.id.btnThoat);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);

        //luu thong tin
        sharedPreferences = getSharedPreferences("luuthongtin",MODE_PRIVATE);

        //lay thong tin
        etUser.setText(sharedPreferences.getString("user",""));
        etPassword.setText(sharedPreferences.getString("password",""));
        cbLuuTT.setChecked(sharedPreferences.getBoolean("check", false));

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUser.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(username.equals("hcadoan") && password.equals("123123")){
                    if(cbLuuTT.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("check", true);
                        editor.putString("user", username);
                        editor.putString("password", password);
                        editor.commit();

                        Toast.makeText(Bai12Activity.this, "Chào mừng bạn đăng nhập vào hệ thống, bạn đã lưu thông tin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Bai12Activity.this, Bai12_dangnhapActivity.class);
                        startActivity(intent);
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("check");
                        editor.remove("user");
                        editor.remove("password");
                        editor.commit();

                        Toast.makeText(Bai12Activity.this, "Chào mừng bạn đăng nhập vào hệ thống, bạn không lưu thông tin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Bai12Activity.this, Bai12_dangnhapActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }


    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Question?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bai12Activity.this.finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}