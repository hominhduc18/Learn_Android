package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;



public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();


    private TableLayout table;
   EditText ed1,ed2, ed3, ed4, ed5, ed6;
    Button b1,b2;
//    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        img1 = findViewById(R.id.img1);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.txtsub);
        ed5 = findViewById(R.id.txtpay);
        ed6 = findViewById(R.id.txt);
        b1 = findViewById(R.id.btn11);
        b2 = (Button) findViewById(R.id.btn11);

        ed5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                int subtotal = Integer.parseInt(ed4.getText().toString());
                int pay = Integer.parseInt(ed5.getText().toString());
                int bal = subtotal - pay;
                ed6.setText(String.valueOf(bal));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, chartActivity.class);
                startActivity(intent);

            }

            ;

        });
    }



//        private void setSupportActionBar(Toolbar toolbar) {
//        }
//
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menutoolbar,menu);
//        return onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        // lay id cua cac item menu
//        int id = item.getItemId();
//        // click thi se hien thi thong bao
//        switch (id){
//            case R.id.about:
//                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.infor:
//                Toast.makeText(this, "Infor", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.search:
//                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.login:
//                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.uers:
//                Toast.makeText(this, "User", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.back:
//                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//
//        }
//        return true;
//    }

    public void add() {
        int tot;
        String nvname = ed1.getText().toString();
        int day = Integer.parseInt(ed2.getText().toString());
        int hour = Integer.parseInt(ed3.getText().toString());
        tot = day * hour * 200;

        data.add(nvname);
        data1.add(String.valueOf(day));
        data2.add(String.valueOf(hour));
        data3.add(String.valueOf(tot));

        TableLayout table = (TableLayout) findViewById(R.id.tb1);
        TableRow row = new TableRow(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);
        TextView t4 = new TextView(this);
        String Total;
        int sum = 0;
        for (int i = 0; i < data.size(); i++) {
            String nv = data.get(i);
            String d = data1.get(i);
            String h = data2.get(i);
            Total = data3.get(i);

            t1.setText(nv);
            t2.setText(d);
            t3.setText(h);
            t4.setText(Total);

            sum = sum + Integer.parseInt(data3.get(i).toString());

        }
        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        table.addView(row);
ed4.setText(String.valueOf(sum));
ed1.setText("");
ed2.setText("");
ed3.setText("");
ed1.requestFocus();



    }
}
