package com.example.contentprovide;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contentprovide.model.Contact;

import java.util.ArrayList;

public class DanhBa extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSION =1001;

    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> adapterDanhBa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControll();
        showAllContacControll();
    }

    private void showAllContacControll() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,
        null,null);
        dsDanhBa.clear();
        while(cursor.moveToNext()){
            String tencotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tencotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;


            int VitricotName = cursor.getColumnIndex(tencotName);
            int VitricotPhone = cursor.getColumnIndex(tencotPhone);
            //lay ra
            String name = cursor.getString(VitricotName);
            String phone =cursor.getString(VitricotPhone);
            //dua vao bang
            Contact contact =new Contact(name,phone);
            dsDanhBa.add(contact);
            adapterDanhBa.notifyDataSetChanged();
        }
    }

    private void addControll() {
        lvDanhBa =findViewById(R.id.lvDanhBa);
        dsDanhBa= new ArrayList<>();
        adapterDanhBa=new ArrayAdapter<>(DanhBa.this, android.R.layout.simple_list_item_1,dsDanhBa);
        lvDanhBa.setAdapter(adapterDanhBa);
    }

}