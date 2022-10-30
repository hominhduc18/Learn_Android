package Model;

import android.media.Image;
import android.widget.ImageView;

public class TinhThanh {
    private int id;
    private String tinh;

    public TinhThanh() {
    }

    public TinhThanh(int id, String tinh) {
        this.id = id;
        this.tinh = tinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }
}



