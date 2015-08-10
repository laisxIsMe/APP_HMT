package cn.edu.scau.hometown.library.com.javis.mygridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.scau.hometown.R;

public class Adapter_GridView extends BaseAdapter {
    private Context context;
    private int[] pic;
    private String[] theme;

    public Adapter_GridView(Context context, int[] pic,String[] theme) {

        this.context = context;
        this.pic = pic;
        this.theme = theme;
    }

    @Override
    public int getCount() {
        return pic.length;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup arg2) {
        HolderView holderView = null;
        if (currentView == null) {
            holderView = new HolderView();
            currentView = LayoutInflater.from(context).inflate(R.layout.adapter_grid_home, null);
            holderView.iv_pic = (ImageView) currentView.findViewById(R.id.iv_adapter_grid_pic);
            holderView.tv_theme = (TextView) currentView.findViewById(R.id.tv_adapter_grid_tv);
            currentView.setTag(holderView);
        } else {
            holderView = (HolderView) currentView.getTag();
        }


        holderView.iv_pic.setImageResource(pic[position]);
        holderView.tv_theme.setText(theme[position]);

        return currentView;
    }


    public class HolderView {

        private ImageView iv_pic;
        private TextView tv_theme;

    }

}
