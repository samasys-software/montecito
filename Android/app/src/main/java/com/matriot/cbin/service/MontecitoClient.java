package com.matriot.cbin.service;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Preethiv on 1/13/2018.
 */

public class MontecitoClient {
    public MontecitoService getClient(){


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try{
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    String body = response.body().string();
                    System.out.println("current API Call"+body);

                    return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();
                }
                catch(Exception er ){
                    //er.printStackTrace();
                    System.out.println("Error:"+er);
                    return null;
                }

            }
        });
        String baseUrl = "http://13.126.19.130:3000";
        //String baseUrl = ""http://ec2-52-91-5-22.compute-1.amazonaws.com:8080/montecito"
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.FINAL,Modifier.STATIC,Modifier.TRANSIENT)
                .serializeNulls()
                .create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( baseUrl )
                .addConverterFactory( GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.client( httpClientBuilder.build()).build();

        MontecitoService client = retrofit.create(MontecitoService.class);

        return client;

    }
}
//