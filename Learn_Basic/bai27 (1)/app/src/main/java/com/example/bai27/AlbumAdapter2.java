package com.example.bai27;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AlbumAdapter2 extends BaseAdapter {
    private Context context;
    private List<Album2> listAlbum;

    public AlbumAdapter2(Context context, List<Album2> listAlbum) {
        this.context = context;
        this.listAlbum = listAlbum;
    }

    @Override
    public int getCount() {
        return listAlbum.size();
    }

    @Override
    public Object getItem(int position) {
        if(position < 0)
            return null;
        return listAlbum.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_album_layout3, null);

        TextView tvTenBH = view.findViewById(R.id.tvtenBH);
        TextView tvDate = view.findViewById(R.id.tvDate);
        //TextView tvSTT = view.findViewById(R.id.tvStt);

        Album2 album2 = listAlbum.get(i);
        tvTenBH.setText(album2.getTenBH());
        tvDate.setText(album2.getDate());
        //tvSTT.setText(album2.getStt());

        return view;
    }
}
