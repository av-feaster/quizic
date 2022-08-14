package com.nielitav.quizic;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class GameOver extends AppCompatActivity {

    TextView setScore,setGenre;
    AppCompatButton home,restart,exit;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    Date time;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference,userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();
        String genre= extras.getString("genre").trim();
        int Score=extras.getInt("score");

        restart=findViewById(R.id.RestartGA);
        home=findViewById(R.id.homeGA);
        exit=findViewById(R.id.exitGA);
        setScore=findViewById(R.id.setScoreGO);
        setGenre=findViewById(R.id.setGenreGA);

        setScore.setText(String.valueOf(Score));
        setGenre.setText(genre.toUpperCase(Locale.ROOT));
        databaseReference = firebaseDatabase.getInstance().getReference();
        Log.e("GameOver",mAuth.getUid());
        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault()).format(new Date());


        QuizInfo quizInfo = new QuizInfo();

        userRef= databaseReference.child("users").child(Objects.requireNonNull(mAuth.getUid()).toString());

        HashMap<String,Object> History=new HashMap<String,Object>();
        History.put("genre",genre);
        History.put("score",Score);
        History.put("currentDate",currentDate);
        History.put("currentTime",currentTime);
        addDatatoFirebase(genre, String.valueOf(Score),currentDate,currentTime);
        if(Score>0){
            updateScore(String.valueOf(Score));
        }

        restart.setOnClickListener(v->{
            startActivity(new Intent(this,QuizGenre.class));

        });
        home.setOnClickListener(v->{
            startActivity(new Intent(this,MainActivity.class));
        });
        exit.setOnClickListener(v->{
            finishAffinity();
            Process.killProcess(Process.myPid());
            System.exit(0);

        });


    }
    //

    private void addDatatoFirebase(String genre, String score, String date,Date time) {
        // below 3 lines of code is used to set
        // data in our object class.
        String Hid=genre+"_"+date;
        DatabaseReference hRef=userRef.child("History").child(Hid);
        QuizInfo quizInfo = new QuizInfo();

        quizInfo.setGenre(genre);
        quizInfo.setScore(score);
        quizInfo.setDate(date);
        quizInfo.setCurrentTime(time);

        // we are use add value event listener method
        // which is called with database reference.
        hRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                hRef.setValue(quizInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(GameOver.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(GameOver.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateScore(String cscore){
        DatabaseReference scoreRef=userRef.child("score");
        String pScore=scoreRef.getKey();
        Log.i("gameActivity", "p: "+pScore);
        userRef.child("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // data available in snapshot.value()
                HashMap<String,Object>up=new HashMap<String,Object>();
                final String prevScore= Objects.requireNonNull(snapshot.getValue()).toString();
                Log.e("gameActivity", "prev: "+prevScore);
                final int finalScore = parseInt(prevScore) + parseInt(cscore);
                up.put("score",finalScore);
                userRef.updateChildren(up);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("gameActivity", "Cancelled");

            }


        });


    }

//

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}
