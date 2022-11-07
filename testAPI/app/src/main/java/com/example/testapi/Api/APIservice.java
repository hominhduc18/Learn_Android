package com.example.testapi.Api;

import com.example.testapi.Model.Currency;
import com.example.testapi.Model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIservice {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyy-MM-dd HH:mm:ss")
            .create();

    APIservice apiservice = new Retrofit.Builder()
            .baseUrl("http://apilayer.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIservice.class);

    //Cách 1

//    @GET("api/live")
//    Call<Currency> convertUSDtoVND(@Query("access_key") String access_key ,
//                                    @Query("currencies") String currencies,
//                                   @Query("source") String source,
//                                   @Query("format") int format);

    //Cach 2 có param đằng sau
    @GET("api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1")
    Call<Currency> convertUSDtoVND1();

// nối với param đằng sau
    @GET("api/live")
    Call<Currency> convertUSDtoVND5(@QueryMap Map<String, String> options);


    //Cách3 link ko có param đăng sau


    //LINK API: http://apilayer.net/api/users/list
    @GET("api/users/list")
    Call<Currency> convertUSDtoVND2();



    //LINK API: http://apilayer.net/api/group/1/user co id là 1
    @GET("api/group/{id}/user")
    Call<Currency> convertUSDtoVND3(@Path("id") int groupid);




    //LINK API: http://apilayer.net/api/group/1/user?sort=desc
    @GET("api/group/{id}/user")
    Call<Currency> convertUSDtoVND4(@Path("id") int groupid,
                                    @Query("sort") String sort);



    //Phương thức Post https://jsonlaceholder.typicode.com/posts
    APIservice apiservicee = new Retrofit.Builder()
            .baseUrl(" https://jsonlaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIservice.class);
    @POST("posts")
    Call<Post> sendPost(@Body Post post);



}

//http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1