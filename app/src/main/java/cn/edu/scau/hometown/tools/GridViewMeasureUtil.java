package cn.edu.scau.hometown.tools;

import android.widget.Adapter;
import android.widget.GridView;

/**
 * Created by laisixiang on 2015/8/11.
 */
public class GridViewMeasureUtil {

    public static void setGridViewHeightBaseOnLine(GridView gridView){
        Adapter gridAdapter = gridView.getAdapter();
        if (gridAdapter==null){
            return;
        }

        for (int i=0;i<gridAdapter.getCount();i++){
        }
    }
}
