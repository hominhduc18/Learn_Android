package com.example.kt1_2;

public class Song {
    private String idAuthor;
    private String nameSong;
    private  String dateSong;
    private String idSong;

    public Song() {
    }

    public Song(String idAuthor, String dateSong, String nameSong) {
        this.idAuthor = idAuthor;
        this.nameSong = nameSong;
        this.dateSong = dateSong;
        this.idSong = String.valueOf(System.currentTimeMillis());
    }
    public Song (String idSong,String dateSong,String nameSong,String idAuthor){
        this.idSong= idSong;
        this.dateSong = dateSong;
        this.nameSong= nameSong;
        this.idAuthor=idAuthor;
    }
    public String getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(String idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getDateSong() {
        return dateSong;
    }

    public void setDateSong(String dateSong) {
        this.dateSong = dateSong;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }
}
