package com.nielitav.quizic;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionLoadActivity extends AppCompatActivity {

    private Call<ArrayList<Questions>> call;
    ProgressDialog progress;

    int pos=0;
    TextView QuestionNo,QuestionTv,optA,optB,optC,optD,optE,optF;
    LinearLayout linearLayout;
    ProgressBar pgBar;
    String cValue,userSelection,genre;
    ImageView back,exit;
    int score=0;
    int red = Color.parseColor("#FF0000");
    int teal=Color.parseColor("#F37878");
    int green=Color.parseColor("#59CE8F");
    int brown=Color.parseColor("#876445");
    int lbrown=Color.parseColor("#CA955C");
    int bteal=Color.parseColor("#FAD9A1");
    int lblug=Color.parseColor("#ADCF9F");

    ArrayList<Questions> questionsDetails;
    public static final String TAG="QuestionLoadActivity";
    private final String apiKey="NYRYb0agEigA7DMktj20ME0FA3hfeZSdilhYREUm";
    CardView optAq,optBq,optCq,optDq,optEq,optFq,cardQuestionQLA;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_load);
        getSupportActionBar().hide();
        QuestionNo=findViewById(R.id.qnoQLA);
        QuestionTv=findViewById(R.id.questionTvQLA);
        optA=findViewById(R.id.optA_QLA);
        optB=findViewById(R.id.optB_QLA);
        optC=findViewById(R.id.optC_QLA);
        optD=findViewById(R.id.optD_QLA);
        optE=findViewById(R.id.optE_QLA);
        optF=findViewById(R.id.optF_QLA);
        back=findViewById(R.id.backBtnQQ);
        exit=findViewById(R.id.exitBtnQQ);
        cardQuestionQLA=findViewById(R.id.cardQuestionQLA);
        next=findViewById(R.id.nextQLA);
        pgBar=findViewById(R.id.progressBarQQ);
        linearLayout=findViewById(R.id.llCardViewQLA);

        optAq=findViewById(R.id.optA_QQ);
        optBq=findViewById(R.id.optB_QQ);
        optCq=findViewById(R.id.optC_QQ);
        optDq=findViewById(R.id.optD_QQ);
        optEq=findViewById(R.id.optE_QQ);
        optFq=findViewById(R.id.optF_QQ);


        Intent intent=getIntent();
        Bundle extras = getIntent().getExtras();
         genre= extras.getString("name").toLowerCase(Locale.ROOT).trim();

//        questionsDetails= (ArrayList<Questions>)intent.getSerializableExtra("questionDetails");
        Log.e(TAG,"Selected Genre  is"+ genre);
        QuizMethods method=RetrofitQuiz.getRetrofitInstance().create(QuizMethods.class);
        pgBar.setVisibility(View.VISIBLE);
        switch(genre){
            case "html" : call=method.getQuestionsOf(apiKey,"","html","11");
                Log.e(TAG, "html");
                break;

            case "sql":call=method.getSqlQuestions();
                Log.e(TAG, "sql");
                break;

            case "linux":call=method.getLinuxQuestions();
                Log.e(TAG, "linux");
                break;
            case"javascript":call=method.getQuestionsOf(apiKey,"","javascript","11");
                 Log.e(TAG,"JavaScript");
                 break;

            case "code":call=method.getCodeQuestions();
                Log.e(TAG, "Code" );
                break;

            case "docker":call=method.getDockerQuestions();
                 Log.e(TAG,"Docker");
                 break;

            default: call=method.getAllData();
                Log.e(TAG, "all data");
                break;

        }
        Log.e(TAG, "after get "+genre);
        if(pos > 9){
            Log.e(TAG, "set view:pos is "+pos );
            Intent Gameintent =new Intent(this,GameOver.class).putExtra("Genre",genre).putExtra("score",score);
            startActivity(Gameintent);
        }
        if(pos==0){
            progressBar();
        }else {
            getData();
        }
        Log.e(TAG, "onCreate: "+score );

        optAq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    userSelection="A";
                    optAq.setCardBackgroundColor(teal);
                    optA.setBackgroundColor(teal);
                    revealAnswers();
                    if(userSelection==cValue) score=score+1;
                getScore();
                disableCardOptions();



            }


        });
        optBq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    userSelection="B";
                    optBq.setCardBackgroundColor(teal);
                    optB.setBackgroundColor(teal);
                    revealAnswers();

                    if(userSelection==cValue) score=score+1;
                getScore();
                disableCardOptions();



            }
        });
        optCq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    userSelection="C";
                    optCq.setCardBackgroundColor(teal);
                    optC.setBackgroundColor(teal);
                    revealAnswers();
                    if(userSelection==cValue) score=score+1;
                getScore();
                disableCardOptions();




            }
        });
        optDq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    userSelection="D";
                    optDq.setCardBackgroundColor(teal);
                    optD.setBackgroundColor(teal);
                    revealAnswers();
                    if(userSelection==cValue) score=score+1;
                getScore();
                disableCardOptions();




            }
        });
        optEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    userSelection="E";
                    optEq.setCardBackgroundColor(teal);
                    optE.setBackgroundColor(teal);
                    revealAnswers();
                    if(userSelection==cValue) score=score+1;

                getScore();
                disableCardOptions();




            }
        });
        optFq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    userSelection="F";
                    optFq.setCardBackgroundColor(teal);
                    optF.setBackgroundColor(teal);
                    revealAnswers();
                    if(userSelection==cValue) score=score+1;
                getScore();
                linearLayout.setEnabled(false);
                disableCardOptions();


            }
        });
        getScore();

        next.setOnClickListener(v->{
            nextQuestion(questionsDetails,score);
        });
        back.setOnClickListener(v->{
            startActivity(new Intent(this,QuizGenre.class));
        });

        exit.setOnClickListener(v->{
            startActivity(new Intent(this,GameOver.class).putExtra("genre",genre).putExtra("score",score));
        });
    }
    //oncreate ends here -x-x-x-
public void getData(){


    call.enqueue(new Callback<ArrayList<Questions>>() {
        @Override
        public void onResponse(Call<ArrayList<Questions>> call, Response<ArrayList<Questions>> response) {
            Log.e(TAG,"onResponse:code "+ response.code());
            ArrayList<Questions> questions=response.body();
//            Log.i(TAG, "onResponse: "+questions.size());
            questionsDetails= response.body();


                pgBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);


            setView(questionsDetails,pos);

        }

        @Override
        public void onFailure(Call<ArrayList<Questions>> call, Throwable t) {
            pgBar.setVisibility(View.GONE);

            Log.e(TAG,"onFailure: "+ t.getMessage());
            finish();
        }
    });


}
public void  setView(ArrayList<Questions>questionsDetails,int pos){
//    if(pos > 9){
//        Log.e(TAG, "set view:pos is "+pos );
//        Intent Gintent =new Intent(this,GameOver.class).putExtra("Genre",genre).putExtra("score",score);
//        startActivity(Gintent);
//    }

    if(questionsDetails.get(pos).getqId()!=null) {
        QuestionNo.setText(String.valueOf(pos+1));
            QuestionTv.setText(questionsDetails.get(pos).getQuestion());
            optA.setText(questionsDetails.get(pos).answers.getAnsA());
            optB.setText(questionsDetails.get(pos).answers.getAnsB());
            optC.setText(questionsDetails.get(pos).answers.getAnsC());
            optD.setText(questionsDetails.get(pos).answers.getAnsD());
            optE.setText(questionsDetails.get(pos).answers.getAnsE());
            optF.setText(questionsDetails.get(pos).answers.getAnsF());
            Toast.makeText(this,questionsDetails.get(pos).getCategory() , Toast.LENGTH_SHORT).show();
            Log.e(TAG, questionsDetails.get(pos).getCategory() );
            pgBar.setProgress(pos);

            if(optC.getText().toString().isEmpty()){
                optCq.setVisibility(View.GONE);
            }
            if(optD.getText().toString().isEmpty()){
                optDq.setVisibility(View.GONE);
            }
            if(optE.getText().toString().isEmpty()){
                optEq.setVisibility(View.GONE);
            }
            if(optF.getText().toString().isEmpty()){
                optFq.setVisibility(View.GONE);
            }
        }
           linearLayout.setVisibility(View.VISIBLE);
           pgBar.setVisibility(View.VISIBLE);
           cValue=correctAnswerOpt(questionsDetails,pos);
          Log.e(TAG,cValue);


}
public String correctAnswerOpt(ArrayList<Questions> questionsDetails,int pos) {
    if (questionsDetails.get(pos).correct_answers.getAnsAC().equals("true")) {
        return "A";
    } else if (questionsDetails.get(pos).correct_answers.getAnsBC().equals("true")) {
        return "B";
    } else if (questionsDetails.get(pos).correct_answers.getAnsCC().equals("true")) {
        return "C";
    } else if (questionsDetails.get(pos).correct_answers.getAnsDC().equals("true")) {
        return "D";
    } else if (questionsDetails.get(pos).correct_answers.getAnsEC().equals("true")) {
        return "E";
    } else if (Objects.equals(questionsDetails.get(pos).correct_answers.getAnsFC(), "true")) {
        return "F";
    }
    return "";
   }


public void revealAnswers(){
        final String ans=cValue;
        switch(ans){
            case "A":optAq.setCardBackgroundColor(green);
            optA.setBackgroundColor(green);
            optA.setTextColor(Color.WHITE);
            break;

            case "B":optBq.setCardBackgroundColor(green);
                optB.setBackgroundColor(green);
                optB.setTextColor(Color.WHITE);
                break;

            case "C":optCq.setCardBackgroundColor(green);
                optC.setBackgroundColor(green);
                optC.setTextColor(Color.WHITE);
                break;

            case "D":optDq.setCardBackgroundColor(green);
                optD.setBackgroundColor(green);
                optD.setTextColor(Color.WHITE);
                break;

            case "E":optEq.setCardBackgroundColor(green);
                optE.setBackgroundColor(green);
                optE.setTextColor(Color.WHITE);
                break;

            case "F":optFq.setCardBackgroundColor(green);
                optF.setBackgroundColor(green);
                optF.setTextColor(Color.WHITE);
                break;

        }

}
public void getScore(){
    Log.i(TAG, "getScore: "+score);
}
public void disableCardOptions(){
    optAq.setEnabled(false);
    optBq.setEnabled(false);
    optCq.setEnabled(false);
    optDq.setEnabled(false);
    optEq.setEnabled(false);
    optFq.setEnabled(false);
}

@SuppressLint("ResourceAsColor")
public void nextQuestion(ArrayList<Questions>questionsDetails, int score){
   optAq.setEnabled(true);
    optBq.setEnabled(true);
    optCq.setEnabled(true);
    optDq.setEnabled(true);
    optEq.setEnabled(true);
    optFq.setEnabled(true);
    pos=pos+1;
    Log.i(TAG, "pos "+pos);

    optAq.setVisibility(View.VISIBLE);
    optBq.setVisibility(View.VISIBLE);
    optCq.setVisibility(View.VISIBLE);
    optDq.setVisibility(View.VISIBLE);
    optEq.setVisibility(View.VISIBLE);
    optFq.setVisibility(View.VISIBLE);

    optAq.setCardBackgroundColor(bteal);
    optA.setBackgroundColor(bteal);
    optB.setBackgroundColor(bteal);
    optC.setBackgroundColor(bteal);
    optD.setBackgroundColor(bteal);
    optE.setBackgroundColor(bteal);
    optF.setBackgroundColor(bteal);

    optBq.setCardBackgroundColor(bteal);
    optCq.setCardBackgroundColor(bteal);
    optDq.setCardBackgroundColor(bteal);
    optEq.setCardBackgroundColor(bteal);
    optFq.setCardBackgroundColor(bteal);
    cardQuestionQLA.setCardBackgroundColor(lblug);

    optA.setTextColor(Color.BLACK);
    optA.setTextColor(Color.BLACK);
    optB.setTextColor(Color.BLACK);
    optC.setTextColor(Color.BLACK);
    optD.setTextColor(Color.BLACK);
    optE.setTextColor(Color.BLACK);
    optF.setTextColor(Color.BLACK);
    if(pos >9){
        Log.e(TAG, "nextques:pos is "+pos );
        linearLayout.setVisibility(View.GONE);
        Intent intent =new Intent(this,GameOver.class).putExtra("genre",genre).putExtra("score",score);
        startActivity(intent);
    }
    else{
    setView(questionsDetails,pos);
    }



}
public void progressBar(){
    progress = new ProgressDialog(QuestionLoadActivity.this);


    progress.setTitle("Loading Questions.."); // setting title
    progress.setMessage("Please Wait Loading..."); // creating message
    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
    progress.setIndeterminate(true);
    progress.show();

    new Thread() {

        public void run() {
            getData();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress.dismiss();
        }

    }.start();
}
}





