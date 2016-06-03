package com.dream.wh.drawlayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-6-2.
 */
public class ContentFragment extends ListFragment{

    private String content;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        content = getArguments().getString("url");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //设置内容
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(content+"........."+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,
                datas);
        setListAdapter(adapter);

    }
}
