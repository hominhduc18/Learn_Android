package com.example.bai27;

public class Album2 {
    private String TenBH;
    private String Date;
    private int number=1;
    private int stt ;

    public Album2() {
    }

    public Album2(String tenBH, String date) {
        TenBH = tenBH;
        Date = date;
        number +=1;
        stt = number;
    }

    public String getTenBH() {
        return TenBH;
    }

    public void setTenBH(String tenBH) {
        TenBH = tenBH;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
