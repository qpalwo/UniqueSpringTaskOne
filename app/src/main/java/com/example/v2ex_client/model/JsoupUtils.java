package com.example.v2ex_client.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.v2ex_client.base.CallBack;
import com.example.v2ex_client.model.Bean.Member;
import com.example.v2ex_client.model.Bean.MemberPost;
import com.example.v2ex_client.model.Bean.MemberReply;
import com.example.v2ex_client.model.Bean.Post;
import com.example.v2ex_client.model.Bean.Reply;
import com.google.gson.Gson;

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

    //获取帖子的点击次数
    public void getPostCheckedTimes(String address, final CallBack<String> callBack){
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.setCallBack(new CallBack<Document>() {
            @Override
            public void onSuccess(Document data) {
                Elements checkedTime = data.getElementsByClass("gray");
                String checked = checkedTime.get(0).text();
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

    //获取用户 页面用户创建的主题
    public void getMemberPosts(String address, final CallBack<List<MemberPost>> callBack){
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.setCallBack(new CallBack<Document>() {
            @Override
            public void onSuccess(Document data) {
                List<MemberPost> memberPosts = new ArrayList<>();
                Elements posts = data.getElementsByClass("item_title");
                Elements nodes = data.getElementsByClass("node");
                Elements timeAndLasts = data.getElementsByClass("small fade").select("strong");
                //TODO 换一个上限，这个会越界
                for(int i = 0; i < posts.size() - 1; i++){
                    MemberPost memberPost = new MemberPost();
                    Post post = new Post();
                    Member lastReply = new Member();
                    post.setTitle(posts.get(i).text());
                    post.setUrl("https://www.v2ex.com" + posts.get(i).select("a").attr("href"));
                    lastReply.setUsername(timeAndLasts.get(2 * i + 1).text());
                    lastReply.setUrl("https://www.v2ex.com" + timeAndLasts.get(2 * i + 1).select("a").attr("href"));
                    memberPost.setNode(nodes.get(i).text());
                    memberPost.setTimeAndLast(timeAndLasts.get(2 * i).text());
                    memberPost.setPost(post);
                    memberPost.setLastReply(lastReply);
                    memberPosts.add(memberPost);
                }
                callBack.onSuccess(memberPosts);
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
                callBack.onComplete();
            }
        });
        jsoupAsyncTask.execute(address);
    }

    //获取用户页面用户回复的主题
    public void getMemberReplies(String address, final CallBack<List<MemberReply>> callBack){
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.setCallBack(new CallBack<Document>() {
            @Override
            public void onSuccess(Document data) {
                List<MemberReply> memberReplies = new ArrayList<>();
                Elements repliedContents = data.getElementsByClass("reply_content");
                Elements repliedTimes = data.getElementsByClass("fade");
                Elements repliedPosts = data.getElementsByClass("gray");
                for (int i = 0; i < repliedContents.size(); i++){
                    MemberReply memberReply = new MemberReply();
                    Member member = new Member();
                    Post post = new Post();
                    member.setUsername(repliedPosts.get(i + 2).select("a").get(0).text());
                    member.setUrl("https://www.v2ex.com" + repliedPosts.get(i + 2).select("a").get(0).attr("href"));
                    post.setUrl("https://www.v2ex.com" + repliedPosts.get(i + 2).select("a").get(2).attr("href"));
                    post.setTitle(repliedPosts.get(i + 2).select("a").get(2).text());
                    memberReply.setRepliedContent(repliedContents.get(i).html());
                    memberReply.setRepliedTime(repliedTimes.get(i).text());
                    memberReply.setRepliedCreatedMember(member);
                    memberReply.setRepliedPost(post);
                    memberReplies.add(memberReply);
                }
                callBack.onSuccess(memberReplies);
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
                callBack.onComplete();
            }
        });
        jsoupAsyncTask.execute(address);
    }

    //根据api获取用户具体信息
    public void getMemberInfo(String name, final CallBack<Member> callBack){
        HttpConnectionUtils.getResponse("GET",
                null,
                HttpConnectionUtils.V2EX_USER + "username=" + name,
                new CallBack<String>() {
                    @Override
                    public void onSuccess(String data) {
                        ResponseHandle.memberHandler(data, new CallBack<Member>() {
                            @Override
                            public void onSuccess(Member data) {
                                callBack.onSuccess(data);
                            }

                            @Override
                            public void onFailure(String msg) {

                            }

                            @Override
                            public void onError() {

                            }

                            @Override
                            public void onComplete() {
                                callBack.onComplete();
                            }
                        });
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
