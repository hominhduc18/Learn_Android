package com.example.kt1_2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Album implements Serializable{
    private String nameAlbum;
    private String idAlbum;
    private String codeAlbum;
    private String age;

    public Album( String nameAlbum,String codeAlbum,String age) {
        this.nameAlbum = nameAlbum;
        this.codeAlbum = codeAlbum;
        this.age = age;
        this.idAlbum = String.valueOf(System.currentTimeMillis());
    }
    public Album(String idAlbum, String nameAlbum,String codeAlbum,String age) {
        this.nameAlbum = nameAlbum;
        this.idAlbum = idAlbum;
        this.codeAlbum = codeAlbum;
        this.age = age;
    }
    public String getCodeAlbum() {
        return codeAlbum;
    }

    public void setCodeAlbum(String codeAlbum) {
        this.codeAlbum = codeAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
