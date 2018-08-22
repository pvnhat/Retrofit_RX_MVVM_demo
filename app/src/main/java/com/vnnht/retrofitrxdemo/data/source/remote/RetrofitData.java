package com.vnnht.retrofitrxdemo.data.source.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitData {

    private static Retrofit sRetrofit = null;

    public static Retrofit getStudentDataRX(String url) {
        OkHttpClient okHttpClient =
                new OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS)
                        .writeTimeout(5000, TimeUnit.MILLISECONDS)
                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                        .retryOnConnectionFailure(true)
                        .build();
        Gson gson = new GsonBuilder().setLenient().create();
        sRetrofit = new Retrofit.Builder().baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return sRetrofit;
    }
}
