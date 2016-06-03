package com.dream.wh.drawlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dream.wh.drawlayout.R;

/**
 * Created by Administrator on 16-6-2.
 */
public class TitleFragment extends Fragment{

    private ListView listView;
    private OnTitleItemClickListener listener;

    public interface OnTitleItemClickListener{
        void onTitleItemClick(int position);
    }
    public void setOnTitleItemClickListener(OnTitleItemClickListener listener){
     this.listener=listener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.title_layout,null);
        listView = (ListView) view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1
        ,new String[]{"开通会员", "QQ钱包", "个性装扮", "我的收藏"});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //点击的时候要切换内容的Fragment
                if(listener!=null){
                    listener.onTitleItemClick(position);
                }
            }
        });
        return view;
    }
}
