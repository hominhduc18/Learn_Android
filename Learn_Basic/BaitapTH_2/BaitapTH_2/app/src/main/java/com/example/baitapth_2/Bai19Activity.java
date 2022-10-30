package com.example.baitapth_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import Model.TinhThanh;

public class Bai19Activity extends AppCompatActivity {

    final CustomAdapter customAdapter;
    final ArrayList<TinhThanh> listTinh;
    TinhThanh tinhThanh;

    TextView hienthi;


    public Bai19Activity() {
        listTinh = new ArrayList<>();
        listTinh.add(new TinhThanh(1,"Hà Nội"));
        listTinh.add(new TinhThanh(2,"Huế"));

        customAdapter = new CustomAdapter(this, listTinh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai19);

        ListView lvTinh = findViewById(R.id.listviewTen);
        lvTinh.setAdapter(customAdapter);

        hienthi = findViewById(R.id.hienthi);

        lvTinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tinhThanh = listTinh.get(i);
                hienthi.setText(tinhThanh.getTinh());
            }
        });

    }


}