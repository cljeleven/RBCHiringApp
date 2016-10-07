package com.example.eleven.rbchiringapp.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okio.Buffer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eleven on 10/5/2016.
 */
public class RetrofitManager {
    public static final String BASE_URL = "https://api.yelp.com/";
    //  public static final String BASE_URL = "http://api.dev.acrossor.com/";
    static String token = "1gs0BJ22LMyKmhdcLZMOCX3xq3mx1QrwdQ74YYnxuRT7sU5wjLGNksSFx_VYSbfDajMd6suljPSBQnxPkIqz72YX528codmZGEOzsa15e-u2UC0qXCAu2gGk3jb0V3Yx";

    static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(
            new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization",
                                    "Bearer" + " " + token)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }
    );

    static OkHttpClient httpClient =  httpClientBuilder.build();

    static  Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .create();


    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build();

    private static RetrofitServices public_apiservice = retrofit.create(RetrofitServices.class);


    public static RetrofitServices  get_publicAPI( ){
        return public_apiservice;
    }
}
