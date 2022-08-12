package com.nielitav.quizic;

import static com.nielitav.quizic.MainActivity.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nielitav.quizic.Questions.Answers;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionLoadActivity extends AppCompatActivity {

    private Call<ArrayList<Questions>> call;
    TextView QuestionTv,optA,optB,optC,optD;
    ArrayList<Questions> questionsDetails;
    public static final String TAG="QuestionLoadActivity";
    private final String apiKey="NYRYb0agEigA7DMktj20ME0FA3hfeZSdilhYREUm";

    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_load);
        QuestionTv=findViewById(R.id.questionTvQLA);
        optA=findViewById(R.id.optA_QLA);
        optB=findViewById(R.id.optB_QLA);
        optC=findViewById(R.id.optC_QLA);
        optD=findViewById(R.id.optD_QLA);
        next=findViewById(R.id.nextQLA);
        Intent intent;
        Bundle extras = getIntent().getExtras();
        String name= extras.getString("name").toLowerCase(Locale.ROOT).trim();
        Log.e(TAG,"Selected Genre  is"+ name);
        QuizMethods method=RetrofitQuiz.getRetrofitInstance().create(QuizMethods.class);

        switch(name){
            case "html" : call=method.getQuestionsOf(apiKey,"html");
                Log.e(TAG, "html");
                break;

            case "sql":call=method.getSqlQuestions();
                Log.e(TAG, "sql");
                break;

            case "linux":call=method.getLinuxQuestions();
                Log.e(TAG, "linux");

                break;


            default: call=method.getAllData();
                Log.e(TAG, "all data");
                break;

        }
        Log.e(TAG, "after get "+name);

        getData();


    }
public void getData(){


    call.enqueue(new Callback<ArrayList<Questions>>() {
        @Override
        public void onResponse(Call<ArrayList<Questions>> call, Response<ArrayList<Questions>> response) {
            Log.e(TAG,"onResponse:code "+ response.code());
            ArrayList<Questions> questions=response.body();
//            Log.i(TAG, "onResponse: "+questions.size());
            questionsDetails= response.body();

            setView(questionsDetails);
        }

        @Override
        public void onFailure(Call<ArrayList<Questions>> call, Throwable t) {
            Log.e(TAG,"onFailure: "+ t.getMessage());
            finish();
        }
    });


}
public void  setView(ArrayList<Questions>questionsDetails){
        int pos=0;
        if(questionsDetails.get(pos).getqId()!=null) {
            QuestionTv.setText(questionsDetails.get(pos).getQuestion());
            optA.setText(questionsDetails.get(pos).answers.getAnsA());
            optB.setText(questionsDetails.get(pos).answers.getAnsB());
            optC.setText(questionsDetails.get(pos).answers.getAnsC());
            optD.setText(questionsDetails.get(pos).answers.getAnsD());
//            string s;questionsDetails.get(pos).getCategory();
            Toast.makeText(this,questionsDetails.get(pos).getCategory() , Toast.LENGTH_SHORT).show();

            Log.e(TAG, questionsDetails.get(pos).getCategory() );
        }


}

}



