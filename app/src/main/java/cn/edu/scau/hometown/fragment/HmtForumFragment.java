package cn.edu.scau.hometown.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbOnItemClickListener;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbSlidingPlayView;
import cn.edu.scau.hometown.library.com.javis.mygridview.Adapter_GridView;
import cn.edu.scau.hometown.library.com.javis.mygridview.MyGridView;


/**
 * Created by acer on 2015/7/24.
 */
public class HmtForumFragment extends Fragment {
    private View view;

    private AbSlidingPlayView vp_ab;
    /**�洢��ҳ�ֲ��Ľ���*/
    private ArrayList<View> allListView;
    /**��ҳ�ֲ��Ľ������Դ*/
    //TODO ��Ƭ������
    private int[] resId = { R.drawable.menu_viewpager_1, R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3, R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5 };


    /**����ľŹ���*/
    private MyGridView gridView_classify;
    /**����Ź������Դ�ļ�*/
    private int[] pic_path_classify = { R.drawable.menu_guide_1, R.drawable.menu_guide_2, R.drawable.menu_guide_3, R.drawable.menu_guide_4, R.drawable.menu_guide_5, R.drawable.menu_guide_6, R.drawable.menu_guide_7, R.drawable.menu_guide_8 };
    private Adapter_GridView adapter_GridView_classify;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hmt_forum,container,false);
        init_abSlidingPagerView();
        init_gridView();
        return  view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void init_abSlidingPagerView(){
        vp_ab = (AbSlidingPlayView)view.findViewById(R.id.vp_ab);


        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            //����ViewPager�Ĳ���,ǰ�����ǰ�ͼƬ��װ��һ��ImageView
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item_for_hmtfragment, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item_for_hmtfragment);
            imageView.setImageResource(resId[i]);
            allListView.add(view);
        }


        vp_ab.addViews(allListView);
        //��ʼ�ֲ�
        vp_ab.makesurePosition();
        vp_ab.creatIndex();
        vp_ab.startPlay();
        vp_ab.setOnItemClickListener(new AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                //��ת���������
//				Intent intent = new Intent(getActivity(), BabyActivity.class);
//				startActivity(intent);
                vp_ab.stopPlay();
                Log.d("MyDebug","position========="+position);
                Toast.makeText(getActivity(), "position=" + position, Toast.LENGTH_LONG).show();
                vp_ab.startPlay();
            }
        });
    }

    private void init_gridView(){
        gridView_classify = (MyGridView) view.findViewById(R.id.my_gridview);
        gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter_GridView_classify = new Adapter_GridView(getActivity(), pic_path_classify);
        gridView_classify.setAdapter(adapter_GridView_classify);

        gridView_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //��ս��������������
//				Intent intent = new Intent(getActivity(), WareActivity.class);
//				startActivity(intent);
                Toast.makeText(getActivity(), "gridItem=" , Toast.LENGTH_LONG).show();
            }
        });
    }


}
