package com.unagi.hackaz.linkedout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.unagi.hackaz.linkedout.Model.CompaniesList;
import com.unagi.hackaz.linkedout.Model.Object;

import java.util.ArrayList;
import java.util.logging.Level;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button textDemoButton;

    CompaniesList companiesList = new CompaniesList();

    public static final String BASE_URL = "http://ec2-52-90-101-66.compute-1.amazonaws.com/api/";

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();


    Retrofit retrofit ;


    RecyclerView recView;
    CompanyListAdapter adapter;
    LinearLayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AWSMobileClient.getInstance().initialize(this).execute();
        setContentView(R.layout.activity_main);

        recView = (RecyclerView) findViewById(R.id.rec_view);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompanyListAdapter(new ArrayList<Object>(),this);
        recView.setAdapter(adapter);



        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
                .build();


        init();


    }

    public interface MyAPI{

        @GET("companies")
        Call<CompaniesList> getCompanies(@Query("format") String format);



    }



    private void init() {
        Log.e(TAG, "Initializing app");



        MyAPI api = retrofit.create(MyAPI.class);

        Call<CompaniesList> call = api.getCompanies("json");


        call.enqueue(new Callback<CompaniesList>() {
            @Override
            public void onResponse(Call<CompaniesList> call, Response<CompaniesList> response) {
                companiesList = response.body();
                adapter.setAdapter(companiesList.getObjects());
            }

            @Override
            public void onFailure(Call<CompaniesList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching companies data", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    @Override
//    public void onClick(final View v) {
//        switch ((v.getId())) {
//            case R.id.button_select_text:
//                Intent textIntent = new Intent(this, TextActivity.class);
//                startActivity(textIntent);
//                break;
//
//        }
//    }

}
