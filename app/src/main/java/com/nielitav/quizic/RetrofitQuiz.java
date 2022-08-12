package com.nielitav.quizic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitQuiz{

    private static Retrofit retrofit;
    private static String BASE_URL= "https://quizapi.io/api/v1/";


    public static Retrofit getRetrofitInstance(){
        if(retrofit ==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return  retrofit;
    }




}
