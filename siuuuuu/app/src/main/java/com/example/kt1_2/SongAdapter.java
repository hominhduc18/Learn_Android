package com.example.kt1_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    ArrayList<Song> listSong;
    public SongAdapter(@NonNull Context context, ArrayList<Song> listSong) {
        super(context, R.layout.item_album_layout,listSong);
        this.listSong = listSong;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            //nạp từ layout bằng inflate
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            view = layoutInflater.inflate(R.layout.item_album_layout, null);
        } else {
            view = convertView;
        }
        Song item = listSong.get(position);
        //gán dữ liệu ở vị trí possition vào View và trả về đối tượng View này
        setDataToView(item, view,position);
        return view;
    }


    private void setDataToView(Song song, View view,int position){
//        TextView tvStt = view.findViewById(R.id.tvSttSong);
//        tvStt.setText(String.valueOf(position));
//        TextView tvDate = view.findViewById(R.id.tvDateSong);
//        tvDate.setText(song.getDateSong());
//        TextView tvNameSong = view.findViewById(R.id.tvNameSong);
//        tvNameSong.setText(song.getNameSong());
    }

}
