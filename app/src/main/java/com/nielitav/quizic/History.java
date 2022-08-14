package com.nielitav.quizic;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class History extends AppCompatActivity {
   RecyclerView recyclerView;
   DatabaseReference databaseReference,historyReference,userRef,nameRef;
   HistoryAdapter historyAdapter;
   FirebaseAuth mAuth=FirebaseAuth.getInstance();;
   ArrayList<QuizInfo>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView=findViewById(R.id.recyclerviewHA);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().hide();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        userRef= databaseReference.child("users").child(Objects.requireNonNull(mAuth.getUid()).toString());
        historyReference=userRef.child("History");
        list=new ArrayList<QuizInfo>();
        historyAdapter=new HistoryAdapter(this,list);
        recyclerView.setAdapter(historyAdapter);

        historyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Log.e("HistoryActivity",dataSnapshot.toString());
                    QuizInfo quiz=dataSnapshot.getValue(QuizInfo.class);
                    list.add(quiz);
                }
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("HistoryActivity","Cancelled");

            }
        });


    }
}