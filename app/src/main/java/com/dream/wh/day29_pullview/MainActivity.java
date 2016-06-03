package com.dream.wh.day29_pullview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class MainActivity extends AppCompatActivity {
    private String[] urlStrings = {
            "http://img5.duitang.com/uploads/item/201410/04/20141004141720_vr23M.jpeg",
            "http://img1.imgtn.bdimg.com/it/u=3724834980,466973059&fm=11&gp=0.jpg",
            "http://i2.hexunimg.cn/2015-04-07/174742774.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2525689499,526305536&fm=21&gp=0.jpg"
    };
    private PtrClassicFrameLayout pcfl;
    private TextView textView;
    private ImageView imageView;
    private  int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //注册下拉监听器
        pcfl.setPtrHandler(new PtrDefaultHandler(){
            //一般在下拉时，开启异步任务或者是线程去执行耗时操作，
            //任务完成时调用 myPtrFL.refreshComplete()方法让下拉刷新的头部消失
            @Override
            public void onRefreshBegin(PtrFrameLayout frame){
                //下拉时，开始下载图片
                imageLoad();
            }
        });




        final StoreHouseHeader header = new StoreHouseHeader(this);
        //        //设置布局参数，-1指匹配父窗体，-2指包裹内容
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        //        //设置内边距...PtrLocalDisplay框架自带
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        // 头部使用的字符串。这里的字符串只能是 [A-Z 0-9 - .]
        header.initWithString("loading-go-go-xgo");
        header.setTextColor(Color.GRAY);
        //给下拉刷新设置下拉头部 StoreHouseHeader布局
        pcfl.setHeaderView(header);
        //        //添加一个UI时间处理回调函数。为MaterialHeader的内部实现回调。
        pcfl.addPtrUIHandler(header);

    }

    private void imageLoad(){
        new Thread(){
            @Override
            public void run(){
                try{
                    HttpURLConnection connection= (HttpURLConnection) new URL(urlStrings[index%urlStrings.length]).openConnection();
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode()==200){
                        SystemClock.sleep(3000);
                        final Bitmap bitmap= BitmapFactory.decodeStream(connection.getInputStream());
                        index++;
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                imageView.setImageBitmap(bitmap);
                                textView.setText("下载完成"+index);
                                pcfl.refreshComplete();
                            }
                        });
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initView(){
        pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.image);
    }


}
