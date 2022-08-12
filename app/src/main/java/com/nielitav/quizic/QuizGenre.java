package com.nielitav.quizic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class QuizGenre extends AppCompatActivity {
    GridView genreGv;
    public static final String TAG="QuizGenreActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_genre);

        genreGv=findViewById(R.id.quizGenreGridV);
        ArrayList<GenreModel> genreModelArrayList=new ArrayList<GenreModel>();
        genreModelArrayList.add(new GenreModel("LINUX",R.drawable.quizic));
        genreModelArrayList.add(new GenreModel("SQL",R.drawable.quizic));
        genreModelArrayList.add(new GenreModel("ML",R.drawable.quizic));
        genreModelArrayList.add(new GenreModel("HTML",R.drawable.quizic));
        genreModelArrayList.add(new GenreModel("JAVASCRIPT",R.drawable.quizic));
        genreModelArrayList.add(new GenreModel("KUBERNETES",R.drawable.quizic));

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