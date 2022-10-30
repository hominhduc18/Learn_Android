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
                //sau khi cập nhật thì reset dữ liệu và cho focus tới editCV
                editCongViec.setText("");
                editNoiDung.setText("");
                editCongViec.requestFocus();
            }
        });

        getDefaultInfor();
    }
    public void getDefaultInfor()
    {
        //lấy ngày hiện tại của hệ thống
        cal= Calendar.getInstance();
        SimpleDateFormat dft=null;
        //Định dạng ngày / tháng /năm
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hiển thị lên giao diện
        textViewDate.setText(strDate);
        //Định dạng giờ phút am/pm
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        //đưa lên giao diện
        textViewTime.setText(strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        textViewTime.setTag(dft.format(cal.getTime()));

//        editCv.requestFocus();
        //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
        dateFinish=cal.getTime();
        hourFinish=cal.getTime();
    }


    /**
     * Hàm hiển thị DatePicker dialog
     */
    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                textViewDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=textViewDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                TaskActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày");
        pic.show();
    }
    /**
     * Hàm hiển thị TimePickerDialog
     */
    public void showTimePickerDialog()
    {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                textViewTime.setText(hourTam +":"+minute +(hourOfDay>12?" PM":" AM")); //(hourTam<10?"0"+hourTam: hourTam)
                //lưu giờ thực vào tag
                textViewTime.setTag(s);
                //lưu vết lại giờ vào hourFinish
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                hourFinish=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=textViewTime.getTag()+"";
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                TaskActivity.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ");
        time.show();
    }
}