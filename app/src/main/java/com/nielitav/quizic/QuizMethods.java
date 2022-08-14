package com.nielitav.quizic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuizMethods {
    String url1="questions?apiKey=NYRYb0agEigA7DMktj20ME0FA3hfeZSdilhYREUm";
    String url="questions";

    @GET(url1)
    Call<ArrayList<Questions>> getAllData();


    String linux=url1+"&category=linux&limit=11";
    @GET(linux)
    Call<ArrayList<Questions>> getLinuxQuestions();

    String html=url1+"&category=html&limit=11";
    @GET(html)
    Call<ArrayList<Questions>> getHtmlQuestions();

    String sql=url1+"&category=sql&limit=11";
    @GET(sql)
    Call<ArrayList<Questions>> getSqlQuestions();

    String javascript=url1+"&tags=javascript&limit=11";
    @GET(javascript)
    Call<ArrayList<Questions>>getJavaScriptQuestions();

    String docker=url1+"&category=docker&limit=11";
    @GET(docker)
    Call<ArrayList<Questions>>getDockerQuestions();

    String code=url1+"&category=code&limit11&difficulty=easy";
    @GET(code)
    Call<ArrayList<Questions>>getCodeQuestions();


    @GET(url)
    Call<ArrayList<Questions>> getQuestionsOf(@Query("apiKey") String apiKey,@Query("category") String category,@Query("tags") String tags,@Query("limit") String limit);







}
