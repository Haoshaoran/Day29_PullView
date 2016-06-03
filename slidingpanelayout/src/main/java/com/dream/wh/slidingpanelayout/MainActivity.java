package com.dream.wh.slidingpanelayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dream.wh.slidingpanelayout.fragment.LeftFragment;
import com.dream.wh.slidingpanelayout.fragment.RightFragment;

public class MainActivity extends AppCompatActivity implements LeftFragment.OnTitleClickListener{
    String[] urlString={"http://news.163.com/", "http://www.baidu.com/",
            "http://tv.sohu.com/"};
    private RightFragment rightFragment;
    private SlidingPaneLayout s;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rightFragment = (RightFragment) getSupportFragmentManager().findFragmentById(R.id.rightFragment);
        s = (SlidingPaneLayout) findViewById(R.id.sliding);
        //初始化RightFragment
        rightFragment.setWebView(urlString[0]);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.header_btn_more_nor);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //显示返回键
        //这个监听器的设置要写在setDisplayHomeAsUpEnabled(true)后面才有效
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //点击返回图标，出现侧滑菜单
                s.openPane();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        initFragment();
    }

    private void initFragment(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setOnTitleClick(int position){
        String currentStr=urlString[position];
        //更改ContentFragment中的内容
        rightFragment.setWebView(currentStr);
        //关闭侧滑菜单
        s.closePane();
    }
}
