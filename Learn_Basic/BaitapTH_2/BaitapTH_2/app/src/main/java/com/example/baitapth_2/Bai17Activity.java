package com.example.baitapth_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Bai17Activity extends AppCompatActivity {

    final ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView lvTen17;
    TextView vitri_ten17;
    EditText etNhapten;
    Button btnNhapten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai17);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        lvTen17 = findViewById(R.id.lvTen17);
        vitri_ten17 = findViewById(R.id.vitri_ten17);
        etNhapten = findViewById(R.id.etNhapten17);
        btnNhapten = findViewById(R.id.btnNhapten17);

        lvTen17.setAdapter(adapter);

        btnNhapten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(etNhapten.getText()+"");
                adapter.notifyDataSetChanged();
            }
        });

        lvTen17.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri_ten17.setText("position: "+ i+"; value: "+list.get(i));
            }
        });
    }
}