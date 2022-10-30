package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baitapth_2.R;

import java.util.List;

import Model.TinhThanh;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<TinhThanh> listTinhThanh;

    public CustomAdapter(Context context, List<TinhThanh> listTinhThanh) {
        this.context = context;
        this.listTinhThanh = listTinhThanh;
    }

    @Override
    public int getCount() {
        return listTinhThanh.size();
    }

    @Override
    public Object getItem(int position) {
        if(position < 0)
            return null;
        return listTinhThanh.get(position);
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.layout_custom_item, null);

        TextView tvTinhthanh = view.findViewById(R.id.tvTinhthanh);
        ImageView imageView = view.findViewById(R.id.imageview);

        final TinhThanh tt = listTinhThanh.get(i);
        if(listTinhThanh != null && !listTinhThanh.isEmpty()){
            tvTinhthanh.setText(tt.getTinh());
            int idImage = tt.getId();
            switch (idImage){
                case 1:
                    imageView.setImageResource(R.drawable.ic_traidat);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.ic_star);
                    break;
                default:
                    break;
            }
        }
        return view;
    }
}
