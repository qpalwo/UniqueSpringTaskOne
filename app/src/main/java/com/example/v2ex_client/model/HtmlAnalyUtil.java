package com.example.v2ex_client.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.util.Log;

import com.example.v2ex_client.base.CallBack;

import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class HtmlAnalyUtil {

    private Html.ImageGetter imageGetter;

    private Html.TagHandler tagHandler;


    ACacheHelper aCacheHelper;

    public HtmlAnalyUtil() {
        aCacheHelper = ACacheHelper.getInstance();
        initUtil();
    }

    private void initUtil() {
        imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                drawable = loadFromCache(source);
                if (drawable != null) {
                    Log.d("123*****", "getDrawable:加载缓存成功 ");
                    return drawable;
                }
                drawable = loadFromNet(source);
                if (drawable != null) {
                    return drawable;
                }
                return null;
            }
        };

        tagHandler = new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                switch (tag) {

                }
            }
        };
    }


    private Drawable loadFromCache(String url) {
        Drawable drawable = null;
        Bitmap bitmap = ACacheHelper.getaCache().getAsBitmap(url);
        if (bitmap != null) {
            drawable = new BitmapDrawable(null, bitmap);
            drawable.setBounds(0, 0,
                    bitmap.getWidth(),
                    bitmap.getHeight());
        }
        return drawable;
    }

    private Drawable loadFromNet(final String url) {
        final Drawable[] drawable = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    InputStream inputStream = url1.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    drawable[0] = new BitmapDrawable(null, bitmap);
                    drawable[0].setBounds(0, 0,
                            bitmap.getWidth(),
                            bitmap.getHeight());
                    ACacheHelper.getaCache().put(url, bitmap, ACache.TIME_HOUR * 3);
                    final Drawable temp = Drawable.createFromStream(inputStream, url);
                    inputStream.close();
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

    private Drawable loadFromNet1(final String url) {
        final Drawable[] drawable = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    InputStream inputStream = url1.openStream();
                    drawable[0] = Drawable.createFromStream(inputStream, url);
                    inputStream.close();
                    drawable[0].setBounds(0, 0,
                            drawable[0].getIntrinsicWidth(),
                            drawable[0].getIntrinsicHeight());
                    ACacheHelper.getaCache().put(url, drawable[0], ACache.TIME_HOUR * 3);
                    final Drawable temp = Drawable.createFromStream(inputStream, url);
                    inputStream.close();
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
