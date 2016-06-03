package com.dream.wh.drawlayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dream.wh.drawlayout.fragment.ContentFragment;
import com.dream.wh.drawlayout.fragment.TitleFragment;

public class MainActivity extends AppCompatActivity implements TitleFragment.OnTitleItemClickListener{

    private TitleFragment titleFragment;
    private FragmentManager fragmentManager;
    String[] urlStrings = new String[]{"开通会员Url", "QQ钱包url", "个性装扮url", "我的收藏url"};
    private DrawerLayout d;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d = (DrawerLayout) findViewById(R.id.drawLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //换返回键图标
       // toolbar.setNavigationIcon(R.mipmap.header_btn_more_nor);
        setSupportActionBar(toolbar);
        //让返回键显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置toggle的变化和drawerLayout的滑动同步
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,d,toolbar,R.string.app_name,R.string.app_name);
        //设置toggle的变化和drawerLayout的滑动同步
        toggle.syncState();
        d.addDrawerListener(toggle);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final Snackbar snackbar = Snackbar.make(view, "nice to meet you ", Snackbar.LENGTH_LONG);
                snackbar.show();
                snackbar.setAction("退出", new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        snackbar.dismiss();
                    }
                });
            }
        });
        fragmentManager = getSupportFragmentManager();
        titleFragment = (TitleFragment) fragmentManager.findFragmentById(R.id.fragment);
        titleFragment.setOnTitleItemClickListener(this);
        //默认显示数据
        onTitleItemClick(0);
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
    public void onTitleItemClick(int position){
        //当item被点击的时候创建内容Fragment
        ContentFragment contentFragment=new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",urlStrings[position]);
        contentFragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,contentFragment);
        transaction.commit();
        d.closeDrawer(GravityCompat.START);
    }
}
