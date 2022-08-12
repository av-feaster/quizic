package com.nielitav.quizic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Button btn_takeQuiz;
    public static final String TAG="MainActivity";
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayMA);
        btn_takeQuiz = findViewById(R.id.btn_takeQuizMA);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_takeQuiz.setOnClickListener(v->{
//            QuizMethods method=RetrofitQuiz.getRetrofitInstance().create(QuizMethods.class);
//
//              Call<ArrayList<Questions>> call= method.getAllData();
//
//              call.enqueue(new Callback<ArrayList<Questions>>() {
//                  @Override
//                  public void onResponse(Call<ArrayList<Questions>> call, Response<ArrayList<Questions>> response) {
//                      Log.e(TAG,"onResponse:code "+ response.code());
//                      ArrayList<Questions> questions=response.body();
//                      Log.i(TAG, "onResponse:size"+questions.size());
//
//                  }
//
//                  @Override
//                  public void onFailure(Call<ArrayList<Questions>> call, Throwable t) {
//                      Log.e(TAG,"onFailure: "+ t.getMessage());
//
//                  }
//              });



            startActivity(new Intent(this,QuizGenre.class));

        });



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {return true;}
        return super.onOptionsItemSelected(item);
    }
}