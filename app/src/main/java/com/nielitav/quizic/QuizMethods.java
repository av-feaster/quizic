package com.nielitav.quizic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuizMethods {
    String url1="questions?apiKey=NYRYb0agEigA7DMktj20ME0FA3hfeZSdilhYREUm";
    String url="questions";

    @GET(url)
    Call<ArrayList<Questions>> getAllData();


    String linux=url1+"&category=linux&limit=10";
    @GET(linux)
    Call<ArrayList<Questions>> getLinuxQuestions();

    String html=url1+"&category=html&limit=10";
    @GET(html)
    Call<ArrayList<Questions>> getHtmlQuestions();

    String sql=url1+"&category=sql&limit=10";
    @GET(sql)
    Call<ArrayList<Questions>> getSqlQuestions();

    @GET(url)
    Call<ArrayList<Questions>> getQuestionsOf(@Query("apiKey") String apiKey,@Query("category") String category);






}
