package com.example.v2ex_client.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Html;
import android.util.Log;

import com.example.v2ex_client.base.CallBack;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class HtmlAnalyUtil {

    private Html.ImageGetter imageGetter;

    private Context context;

    private ACache aCache;

    public HtmlAnalyUtil(Context context){
        this.context = context;
        this.aCache = ACache.get(context, ACache.CACHE_NAME);
        initUtil();
    }

    private void initUtil(){
        imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                drawable = loadFromCache(source);
                if (drawable != null){
                    Log.d("123*****", "getDrawable:加载缓存成功 ");
                    return drawable;
                }
                drawable = loadFromNet(source);
                if (drawable  != null){
                    return drawable;
                }
                return null;
            }
        };
    }


    private Drawable loadFromCache(String url){
        Drawable drawable = null;
        drawable = aCache.getAsDrawable(url);
        return drawable;
    }

    private Drawable loadFromNet(final String url){
        final Drawable[] drawable = new Drawable[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    InputStream inputStream = url1.openStream();
                    drawable[0] = Drawable.createFromStream(inputStream, url);
                    inputStream.close();
                    drawable[0].setBounds(0,0,64,64);
                    aCache.put(url, drawable[0], ACache.TIME_HOUR * 3);
                    countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return drawable[0];
    }

    public Html.ImageGetter getImageGetter() {
        return imageGetter;
    }
}
