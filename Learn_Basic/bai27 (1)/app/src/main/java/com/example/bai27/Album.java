package com.example.bai27;
import java.io.Serializable;
public class Album implements Serializable{
    private String codeAlbum;
    private String nameAlbum;

    private int stt =1 ;
    public Album(String codeAlbum, String nameAlbum) {
        this.codeAlbum = codeAlbum;
        this.nameAlbum = nameAlbum;
        stt +=1;

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
    public int getStt() {
        return stt;
    }
}
