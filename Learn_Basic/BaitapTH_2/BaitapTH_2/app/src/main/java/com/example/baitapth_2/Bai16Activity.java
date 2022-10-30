package com.example.baitapth_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Bai16Activity extends AppCompatActivity {

    //final ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView lvTen;
    TextView vitri_ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai16);

        String[] ten = new String[]{"Tèo", "Tý", "Bin","Bo"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ten);

        lvTen = findViewById(R.id.lvTen);
        vitri_ten = findViewById(R.id.vitri_ten);

        lvTen.setAdapter(adapter);

        lvTen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri_ten.setText("position: "+ i+"; value: "+ten[i]);
            }
        });

    }
}