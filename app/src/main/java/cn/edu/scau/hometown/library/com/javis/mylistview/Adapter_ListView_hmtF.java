package cn.edu.scau.hometown.library.com.javis.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.edu.scau.hometown.R;

/**
 * Created by laisixiang on 2015/8/10.
 * 这个Adapter节约内存，当数据大量时
 */
public class Adapter_ListView_hmtF extends BaseAdapter{
    private Context context;
    private ItemBean[] itemBeans;

    public Adapter_ListView_hmtF(Context context,ItemBean[] itemBeans){
        this.context=context;
        this.itemBeans=itemBeans;

    }

    @Override
    public int getCount() {
        return itemBeans.length;
    }

    @Override
    public Object getItem(int position) {
        return itemBeans[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView=null;
        if (convertView == null){
            holderView = new HolderView();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_hmt_listview_hot,null);
            holderView.title = (TextView) convertView.findViewById(R.id.title_hot);
            holderView.time = (TextView) convertView.findViewById(R.id.time_hot);
            holderView.owner =(TextView) convertView.findViewById(R.id.owner_hot);
            holderView.comment=(TextView) convertView.findViewById(R.id.comment_hot);
            holderView.brief=(TextView) convertView.findViewById(R.id.text_hot);
            convertView.setTag(holderView);
        }
        else {
            holderView = (HolderView)convertView.getTag();
        }

        holderView.title.setText(itemBeans[position].getTitile());
        holderView.time.setText(itemBeans[position].getTime());
        holderView.owner.setText(itemBeans[position].getOwner());
        holderView.comment.setText(itemBeans[position].getCommmentNum());
        holderView.brief.setText(itemBeans[position].getBrief());

        return convertView;
    }

    public class HolderView{
        private TextView title;
        private TextView time;
        private TextView owner;
        private TextView comment;
        private TextView brief;
    }
}
