package com.frogoutofwell.yullfrogapplication.manager;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.MyApplication;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetailResult;
import com.frogoutofwell.yullfrogapplication.data.DoDetailResult;
import com.frogoutofwell.yullfrogapplication.data.InterDoReviewResult;
import com.frogoutofwell.yullfrogapplication.data.InterInfoResult;
import com.frogoutofwell.yullfrogapplication.data.InterTestReviewResult;
import com.frogoutofwell.yullfrogapplication.data.MainHomeDetailResult;
import com.frogoutofwell.yullfrogapplication.data.MainInterResult;
import com.frogoutofwell.yullfrogapplication.data.MainMypageResult;
import com.frogoutofwell.yullfrogapplication.data.MyDoReviewResult;
import com.frogoutofwell.yullfrogapplication.data.MyTestReviewResult;
import com.frogoutofwell.yullfrogapplication.data.TestDetailResult;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tacademy on 2016-05-23.
 */
public class NetworkManager {

    private static NetworkManager instance;
    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private static final int DEFAULT_CACHE_SIZE = 50 * 1024 * 1024;
    private static final String DEFAULT_CACHE_DIR = "miniapp";
    OkHttpClient mClient;
    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context context = MyApplication.getContext();
        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));

        File dir = new File(context.getExternalCacheDir(), DEFAULT_CACHE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        builder.cache(new Cache(dir, DEFAULT_CACHE_SIZE));

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        mClient = builder.build();
    }

    public interface OnResultListener<T> {
        public void onSuccess(Request request, T result);
        public void onFail(Request request, IOException exception);
    }

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;

    class NetworkHandler extends Handler {
        public NetworkHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkResult result = (NetworkResult)msg.obj;
            switch (msg.what) {
                case MESSAGE_SUCCESS :
                    result.listener.onSuccess(result.request, result.result);
                    break;
                case MESSAGE_FAIL :
                    result.listener.onFail(result.request, result.excpetion);
                    break;
            }
        }
    }

    NetworkHandler mHandler = new NetworkHandler(Looper.getMainLooper());

    static class NetworkResult<T> {
        Request request;
        OnResultListener<T> listener;
        IOException excpetion;
        T result;
    }

    Gson gson = new Gson();

    // 홈 메인
    private static final String FROG_SERVER = "http://52.79.179.176:3000";
    private static final String FROG_MAIN_HOME = FROG_SERVER+"/homePage";
    public Request getFrogMainHomeFeed(Object tag,
                                    OnResultListener<MainHomeDetailResult> listener) {
        String url = String.format(FROG_MAIN_HOME);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MainHomeDetailResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    MainHomeDetailResult data = gson.fromJson(text, MainHomeDetailResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 대외활동 상단 정보
    private static final String FROG_INTER_INFO = FROG_SERVER+"/detailActivity/header/%s";
    public Request getFrogInterInfo(Object tag, int activitySeq,
                                          OnResultListener<InterInfoResult> listener) {
        String url = String.format(FROG_INTER_INFO, activitySeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<InterInfoResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InterInfoResult data = gson.fromJson(response.body().charStream(), InterInfoResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    // 모집요강
    private static final String FROG_INTER_GUIDE = FROG_SERVER+"/detailActivity/guide/%s";
    public Request getFrogInterGuide(Object tag, int activitySeq,
                                       OnResultListener<ActivityDetailResult> listener) {
        String url = String.format(FROG_INTER_GUIDE, activitySeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<ActivityDetailResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ActivityDetailResult data = gson.fromJson(response.body().charStream(), ActivityDetailResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 면접후기
    private static final String FROG_INTER_TEST_REVIEW = FROG_SERVER+"/detailActivity/interviews/%s";
    public Request getFrogInterTestReview(Object tag, int activitySeq,
                                     OnResultListener<InterTestReviewResult> listener) {
        String url = String.format(FROG_INTER_TEST_REVIEW, activitySeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<InterTestReviewResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InterTestReviewResult data = gson.fromJson(response.body().charStream(), InterTestReviewResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 활동후기
    private static final String FROG_INTER_DO_REVIEW = FROG_SERVER+"/detailActivity/postscripts/%s";
    public Request getFrogInterDoReview(Object tag, int activitySeq,
                                          OnResultListener<InterDoReviewResult> listener) {
        String url = String.format(FROG_INTER_DO_REVIEW, activitySeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<InterDoReviewResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InterDoReviewResult data = gson.fromJson(response.body().charStream(), InterDoReviewResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 면접후기 상세보기
    private static final String FROG_INTER_TEST_DETAIL = FROG_SERVER+"/detailInterview/%s";
    public Request getInterTestReviewDetail(Object tag, int interviewSeq,
                                          OnResultListener<TestDetailResult> listener) {
        String url = String.format(FROG_INTER_TEST_DETAIL, interviewSeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<TestDetailResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    TestDetailResult data = gson.fromJson(response.body().charStream(), TestDetailResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 활동후기 상세보기
    private static final String FROG_INTER_DO_DETAIL = FROG_SERVER+"/detailPostscript/%s";
    public Request getInterDoReviewDetail(Object tag, int activitySeq,
                                        OnResultListener<DoDetailResult> listener) {
        String url = String.format(FROG_INTER_DO_DETAIL, activitySeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<DoDetailResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    DoDetailResult data = gson.fromJson(response.body().charStream(), DoDetailResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 추천
    private static final String INTER_RECOMMEND = FROG_SERVER+"/activityPage";
    public Request getInterRecommend(Object tag, OnResultListener<MainInterResult> listener) {
        String url = String.format(INTER_RECOMMEND);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MainInterResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MainInterResult data = gson.fromJson(response.body().charStream(), MainInterResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 대외활동 메인
    private static final String FROG_MAIN_INTER = FROG_SERVER+"/activityPage";
    public Request getFrogMainInter(Object tag, OnResultListener<MainInterResult> listener) {
        String url = String.format(FROG_MAIN_INTER);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MainInterResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MainInterResult data = gson.fromJson(response.body().charStream(), MainInterResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 대외활동 조건별 검색
    private static final String FROG_MAIN_INTER_CONDITION = FROG_SERVER+"/conditionsActivity";
    public Request getFrogMainInterCondition(Object tag, OnResultListener<MainInterResult> listener) {
        String url = String.format(FROG_MAIN_INTER_CONDITION);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MainInterResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MainInterResult data = gson.fromJson(response.body().charStream(), MainInterResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 마이페이지
    private static final String FROG_MAIN_MYPAGE = FROG_SERVER+"/myPage/%s";
    public Request getFrogMainMypage(Object tag, int memSeq, OnResultListener<MainMypageResult> listener) {
        String url = String.format(FROG_MAIN_MYPAGE, memSeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MainMypageResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MainMypageResult data = gson.fromJson(response.body().charStream(), MainMypageResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 찜 대외활동
    private static final String MYPAGE_LIKE_ITEM = FROG_SERVER+"/myPage/moreActivity/%s";
    public Request getMypageLikeItem(Object tag, int memSeq, OnResultListener<MainInterResult> listener) {
        String url = String.format(MYPAGE_LIKE_ITEM, memSeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MainInterResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MainInterResult data = gson.fromJson(response.body().charStream(), MainInterResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 활동내역 나의 면접 후기
    private static final String MY_TEST_REVIEW = FROG_SERVER+"/myPage/interviews/%s";
    public Request getMyTestReview(Object tag, int memSeq, OnResultListener<MyTestReviewResult> listener) {
        String url = String.format(MY_TEST_REVIEW, memSeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MyTestReviewResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MyTestReviewResult data = gson.fromJson(response.body().charStream(), MyTestReviewResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    // 활동내역 나의 활동 후기
    private static final String MY_DO_REVIEW = FROG_SERVER+"/myPage/postscripts/%s";
    public Request getMyDoReview(Object tag, int memSeq, OnResultListener<MyDoReviewResult> listener) {
        String url = String.format(MY_DO_REVIEW, memSeq);
        Request request = new Request.Builder().url(url).build();

        final NetworkResult<MyDoReviewResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    MyDoReviewResult data = gson.fromJson(response.body().charStream(), MyDoReviewResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


}
