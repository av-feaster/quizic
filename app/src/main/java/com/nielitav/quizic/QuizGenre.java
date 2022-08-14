package com.nielitav.quizic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizGenre extends AppCompatActivity {
    GridView genreGv;
    ProgressDialog progress;
    //



    //
    public static final String TAG="QuizGenreActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_genre);
        getSupportActionBar().hide();
        genreGv=findViewById(R.id.quizGenreGridV);
        ArrayList<GenreModel> genreModelArrayList=new ArrayList<GenreModel>();
        genreModelArrayList.add(new GenreModel("LINUX",R.drawable.linux));
        genreModelArrayList.add(new GenreModel("SQL",R.drawable.img_sql));
        genreModelArrayList.add(new GenreModel("DOCKER",R.drawable.docker));
        genreModelArrayList.add(new GenreModel("HTML",R.drawable.html_file));
        genreModelArrayList.add(new GenreModel("JAVASCRIPT",R.drawable.js));
        genreModelArrayList.add(new GenreModel("CODE",R.drawable.programming));
        genreModelArrayList.add(new GenreModel("RANDOM",R.drawable.question));





        GenreGVAdapter adapter=new GenreGVAdapter(this,genreModelArrayList);
        genreGv.setAdapter(adapter);

        genreGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = genreModelArrayList.get(position).getCourse_name().toLowerCase();
                Log.e(TAG,"selected Name is "+ selectedName);
                Intent i = new Intent(QuizGenre.this, QuestionLoadActivity.class).putExtra("name" ,selectedName);
                startActivity(i);

            }
        });
    }
}