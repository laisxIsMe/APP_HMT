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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.scau.hometown.R;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbOnItemClickListener;
import cn.edu.scau.hometown.library.com.javis.abslidingpagerview.AbSlidingPlayView;
import cn.edu.scau.hometown.library.com.javis.mygridview.Adapter_GridView;
import cn.edu.scau.hometown.library.com.javis.mygridview.MyGridView;
import cn.edu.scau.hometown.library.com.javis.mylistview.Adapter_ListView_hmtF;
import cn.edu.scau.hometown.library.com.javis.mylistview.ItemBean;
import cn.edu.scau.hometown.tools.ListViewMeasureUtil;


/**
 * Created by acer on 2015/7/24.
 */
public class HmtForumFragment extends Fragment {
    private View view;

    private AbSlidingPlayView vp_ab;
    /**存储首页轮播的界面*/
    private ArrayList<View> allListView;
    //TODO 相片的载入
    private int[] resId = { R.drawable.menu_viewpager_1, R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3, R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5 };


    /**分类的九宫格*/
    private MyGridView gridView_classify;
    //TODO
    private String[] themes = {"海洋馆","theme","theme","theme","theme","theme","theme","theme","theme","更多"};
    private int[] pic_path_classify = { R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8, R.drawable.menu_guide_8 };
    private Adapter_GridView adapter_GridView_classify;

    /**热门帖子*/
    private ListView lv;
    private ItemBean[] itemBeans=new ItemBean[]{
            new ItemBean(),
            new ItemBean(),
            new ItemBean(),
            new ItemBean(),
            new ItemBean("红满堂","2015-11-12","感情宣泄","9","那天的夜晚啦啦啦啦啦啦啦啦啦，顶顶顶顶顶大，顶顶顶顶顶大快快快快快快！")
    };//资源


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hmt_forum,container,false);
        init_abSlidingPagerView();
        init_gridView();
        init_listView();
        return  view;
    }

    private void init_abSlidingPagerView(){
        vp_ab = (AbSlidingPlayView)view.findViewById(R.id.vp_ab_F);


        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            //导入ViewPager的布局,前三行是把图片封装成一个ImageView
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item_for_hmtfragment, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item_for_hmtfragment);
            imageView.setImageResource(resId[i]);
            allListView.add(view);
        }


        vp_ab.addViews(allListView);
        //开始轮播
        vp_ab.makesurePosition();
        vp_ab.startPlay();
        vp_ab.setOnItemClickListener(new AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                //跳转到详情界面
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
        gridView_classify = (MyGridView) view.findViewById(R.id.gv_hmt_F);
        gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter_GridView_classify = new Adapter_GridView(getActivity(), pic_path_classify,themes);
        gridView_classify.setAdapter(adapter_GridView_classify);

        gridView_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                //挑战到宝贝搜索界面
//				Intent intent = new Intent(getActivity(), WareActivity.class);
//				startActivity(intent);
                Toast.makeText(getActivity(), "gridItem="+position , Toast.LENGTH_LONG).show();
            }
        });


    }

    private void init_listView(){
        lv = (ListView)view.findViewById(R.id.lv_hmt_F);
        Adapter_ListView_hmtF adapter_listView_hmtF = new Adapter_ListView_hmtF(getActivity(), itemBeans);
        lv.setAdapter(adapter_listView_hmtF);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //挑战到宝贝搜索界面
//				Intent intent = new Intent(getActivity(), WareActivity.class);
//				startActivity(intent);
                Toast.makeText(getActivity(), "id=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        ListViewMeasureUtil.setListViewHeightBasedOnChildren(lv);

    }

}
