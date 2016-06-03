package com.dream.wh.slidingpanelayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 16-6-2.
 */
public class LeftFragment extends ListFragment{
    private OnTitleClickListener listener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        String[] data={"网易","百度","搜狐"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        if(listener!=null){
            listener.setOnTitleClick(position);
        }
    }
    public interface OnTitleClickListener{
        void setOnTitleClick(int position);
    }
    public void setOnTitleClickListener(OnTitleClickListener listener){
        this.listener=listener;
    }
    //设置回调接口对象
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //就让管理该Fragment的Activity作为回调对象
        setOnTitleClickListener((OnTitleClickListener) getActivity());
    }
}
