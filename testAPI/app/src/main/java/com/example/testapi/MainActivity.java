package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.Api.APIservice;
import com.example.testapi.Model.Currency;
import com.example.testapi.Model.Post;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tern;
    private TextView Source;
    private TextView Usdvnd;
    private Button callApi;
    private TextView post_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tern =  findViewById(R.id.term);
        Source = findViewById(R.id.source);
        Usdvnd = findViewById(R.id.usd_vnd);
        callApi = findViewById(R.id.call_api);
        post_result = findViewById(R.id.post_result);
        callApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                sendPost();
            }

        });
    }

    private void clickCallapi() {
        //cách 1
//        APIservice .apiservice.convertUSDtoVND("843d4d34ae72b3882e3db642c51e28e6",
//                                               "VND",
//                                               "USD",
//                                               1).enqueue(new Callback<Currency>()
//                                               {
//Cách 2
//                APIservice .apiservice.convertUSDtoVND1().enqueue(new Callback<Currency>()
//                                               {
  //cách 3 sư dungMap
        Map<String,String> options = new HashMap<>();
        options.put("access_key","843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies","VND");
        options.put("source","USD");
        options.put("format","1");
        APIservice .apiservice.convertUSDtoVND5(options).enqueue(new Callback<Currency>()
    {
//
//            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "CAll API Success", Toast.LENGTH_SHORT).show();
                Currency currency = response.body();
                if(currency != null && currency.isSuccess()){
                    tern.setText(currency.getTerms());
                    Source.setText(currency.getSource());
                    Usdvnd.setText(String.valueOf(currency.getQuotes().getUSDVND()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void sendPost(){
        Post post = new Post(10,101,"duc","tetsApi");
        APIservice.apiservice.sendPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "CAll API Success", Toast.LENGTH_SHORT).show();
                Post postResult = response.body();
                if(postResult != null){
                    post_result.setText(postResult.toString());
                }


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}