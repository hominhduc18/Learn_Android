package com.trungnam.taskdatetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.trungnam.taskdatetimepicker.Models.Job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity {
    Calendar cal;
    Date dateFinish;
    Date hourFinish;

    Button buttonDate, buttonTime, buttonAddCV;
    EditText editCongViec,editNoiDung;
    ListView listViewTask;
    TextView textViewDate, textViewTime;

    ArrayAdapter<Job> arrayAdapter = null;
    ArrayList<Job> arrayList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        buttonAddCV = (Button) findViewById(R.id.buttonAddCV);
        buttonDate = (Button) findViewById(R.id.buttonDate);
        buttonTime = (Button) findViewById(R.id.buttonTime);
        editCongViec = (EditText) findViewById(R.id.editCongViec);
        editNoiDung = (EditText) findViewById(R.id.editNoiDung);
        listViewTask = (ListView) findViewById(R.id.listViewTask);
        arrayList = new ArrayList<Job>();
        arrayAdapter = new ArrayAdapter<Job>(this, android.R.layout.simple_list_item_1, arrayList);
        listViewTask.setAdapter(arrayAdapter);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        buttonAddCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=editCongViec.getText()+"";
                String description=editNoiDung.getText()+"";
                Job job=new Job(title, description, dateFinish, hourFinish);
                arrayList.add(job);
                arrayAdapter.notifyDataSetChanged();
                //sau khi c???p nh???t th?? reset d??? li???u v?? cho focus t???i editCV
                editCongViec.setText("");
                editNoiDung.setText("");
                editCongViec.requestFocus();
            }
        });

        getDefaultInfor();
    }
    public void getDefaultInfor()
    {
        //l???y ng??y hi???n t???i c???a h??? th???ng
        cal= Calendar.getInstance();
        SimpleDateFormat dft=null;
        //?????nh d???ng ng??y / th??ng /n??m
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hi???n th??? l??n giao di???n
        textViewDate.setText(strDate);
        //?????nh d???ng gi??? ph??t am/pm
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        //????a l??n giao di???n
        textViewTime.setText(strTime);
        //l???y gi??? theo 24 ????? l???p tr??nh theo Tag
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        textViewTime.setTag(dft.format(cal.getTime()));

//        editCv.requestFocus();
        //g??n cal.getTime() cho ng??y ho??n th??nh v?? gi??? ho??n th??nh
        dateFinish=cal.getTime();
        hourFinish=cal.getTime();
    }


    /**
     * H??m hi???n th??? DatePicker dialog
     */
    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //M???i l???n thay ?????i ng??y th??ng n??m th?? c???p nh???t l???i TextView Date
                textViewDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //L??u v???t l???i bi???n ng??y ho??n th??nh
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish=cal.getTime();
            }
        };
        //c??c l???nh d?????i n??y x??? l?? ng??y gi??? trong DatePickerDialog
        //s??? gi???ng v???i tr??n TextView khi m??? n?? l??n
        String s=textViewDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                TaskActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Ch???n ng??y");
        pic.show();
    }
    /**
     * H??m hi???n th??? TimePickerDialog
     */
    public void showTimePickerDialog()
    {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //X??? l?? l??u gi??? v?? AM,PM
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                textViewTime.setText(hourTam +":"+minute +(hourOfDay>12?" PM":" AM")); //(hourTam<10?"0"+hourTam: hourTam)
                //l??u gi??? th???c v??o tag
                textViewTime.setTag(s);
                //l??u v???t l???i gi??? v??o hourFinish
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                hourFinish=cal.getTime();
            }
        };
        //c??c l???nh d?????i n??y x??? l?? ng??y gi??? trong TimePickerDialog
        //s??? gi???ng v???i tr??n TextView khi m??? n?? l??n
        String s=textViewTime.getTag()+"";
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                TaskActivity.this,
                callback, gio, phut, true);
        time.setTitle("Ch???n gi???");
        time.show();
    }
}