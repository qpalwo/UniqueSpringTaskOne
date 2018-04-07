package com.example.v2ex_client.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.Reply;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 肖宇轩 on 2018/4/5.
 */

public class JsoupUtils {


    //获取  帖子详情页面的跟帖回复信息
    public void getReplies(String address, final CallBack<List<Reply>> callBack) {
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.setCallBack(new CallBack<Document>() {
            @Override
            public void onSuccess(final Document data) {
                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Reply> replies = new ArrayList<>();
                        Elements time = data.getElementsByClass("ago");
                        Elements repliersName = data.getElementsByClass("cell")
                                .select("table")
                                .select("tr")
                                .select("td")
                                .select("strong");
                        Elements content = data.getElementsByClass("reply_content");
                        Elements replyNumber = data.getElementsByClass("no");
                        Elements repliersImg = data.getElementsByClass("cell")
                                .select("table")
                                .select("tr")
                                .select("td")
                                .select("img");
                        for (int i = 0; i < repliersName.size(); i++) {
                            Reply reply = new Reply();
                            Member member = new Member();
                            reply.setTime(time.get(i).text());
                            reply.setReplyContent(content.get(i).text());
                            reply.setReplyNumber(replyNumber.get(i).text());
                            member.setAvatar_normal("https:" + repliersImg.get(i).attr("src"));
                            member.setUsername(repliersName.get(i).text());
                            member.setUrl("http://www.v2ex.com/member/" + member.getUsername());
                            reply.setReplyMember(member);
                            replies.add(reply);
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(replies);
                                callBack.onComplete();
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onFailure(String msg) {
                callBack.onFailure(msg);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
        jsoupAsyncTask.execute(address);


    }

    public void getPostCheckedTimes(String address, final CallBack<String> callBack){
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.setCallBack(new CallBack<Document>() {
            @Override
            public void onSuccess(Document data) {
                Elements checkedTime = data.getElementsByClass("gray");
                String checked = checkedTime.get(0).text();
                //String pattern = "·\\s\\[1-9\\]\\d\\*\\s\\[\\u4e00-\\u9fa5\\]\\[\\u4e00-\\u9fa5\\]\\[\\u4e00-\\u9fa5\\]";
//                String pattern = "\\\\d";
//                Pattern r = Pattern.compile(pattern);
//                Matcher m = r.matcher(checked);
//                int temp = m.groupCount();
//                Log.i("********", "onSuccess: " + m.group());
                callBack.onSuccess(checked);
                callBack.onComplete();
            }

            @Override
            public void onFailure(String msg) {
                callBack.onFailure(msg);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

        jsoupAsyncTask.execute(address);

    }

    //获取Document
    class JsoupAsyncTask extends AsyncTask<String, Integer, Document> {

        CallBack<Document> callBack;

        @Override
        protected Document doInBackground(String... strings) {
            Document document = null;
            try {
                document = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            if (callBack != null) {
                callBack.onSuccess(document);
                callBack.onComplete();
            }
        }

        void setCallBack(CallBack<Document> callBack) {
            this.callBack = callBack;
        }


    }

}
