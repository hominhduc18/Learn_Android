package com.example.baitapth_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import Adapter.CustomAdapter;
import Model.TinhThanh;

public class Bai20Activity extends AppCompatActivity {

    ArrayList<String> item;
    ArrayAdapter<String> adapter;
    final CustomAdapter customAdapter;
    final ArrayList<TinhThanh> listSanpham;
    TinhThanh sanpham;

    public Bai20Activity() {

        listSanpham = new ArrayList<>();
        listSanpham.add(new TinhThanh(1,"Iphone 5"));
        listSanpham.add(new TinhThanh(1,"SamSung S III"));
        listSanpham.add(new TinhThanh(2,"HTC"));
        listSanpham.add(new TinhThanh(1,"Nokia"));

        customAdapter = new CustomAdapter(this, listSanpham);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai20);

        Spinner spnDanhmuc = findViewById(R.id.spnDanhmuc);
        ListView lvSanpham = findViewById(R.id.lvSanpham);

        item = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, item);
        item.add("Điện thoại");
        item.add("Máy tính");
        item.add("Đồng hồ");
        adapter.notifyDataSetChanged();

        lvSanpham.setAdapter(customAdapter);

        spnDanhmuc.setAdapter(adapter);
        spnDanhmuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(item.get(i) == "Điện thoại"){
                    listSanpham.clear();
                    listSanpham.add(new TinhThanh(1,"Iphone 5"));
                    listSanpham.add(new TinhThanh(1,"SamSung S III"));
                    listSanpham.add(new TinhThanh(2,"HTC"));
                    listSanpham.add(new TinhThanh(1,"Nokia"));
                    customAdapter.notifyDataSetChanged();
                } else if(item.get(i) == "Máy tính"){
                    listSanpham.clear();
                    listSanpham.add(new TinhThanh(1,"Máy tính 1"));
                    listSanpham.add(new TinhThanh(1,"Máy tính 2"));
                    listSanpham.add(new TinhThanh(2,"MT 3"));
                    listSanpham.add(new TinhThanh(1,"Máy tính 4"));
                    customAdapter.notifyDataSetChanged();
                } else if(item.get(i) == "Đồng hồ"){
                    listSanpham.clear();
                    listSanpham.add(new TinhThanh(1,"Dồng hồ 1"));
                    listSanpham.add(new TinhThanh(1,"Đồng hồ 2"));
                    listSanpham.add(new TinhThanh(2,"DH 3"));
                    listSanpham.add(new TinhThanh(1,"Đồng hồ 4"));
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}