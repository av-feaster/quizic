package com.nielitav.quizic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    Button btn_signUp;
    EditText user_name,pass_word,cnf,age,nameI;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();


        user_name=findViewById(R.id.usernameRA);
        pass_word=findViewById(R.id.passwordRA);
        nameI=findViewById(R.id.nameRA);
        age=findViewById(R.id.ageRA);
        cnf=findViewById(R.id.cnfPasswordRA);

        btn_signUp=findViewById(R.id.btn_signupRA);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        btn_signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String email = user_name.getText().toString().trim();
                String password = pass_word.getText().toString().trim();
                String userAge=age.getText().toString().trim();
                String name_user=nameI.getText().toString().trim();
                String cnfrm=cnf.getText().toString().trim();
                if (email.isEmpty()) {
                    user_name.setError("Email is empty");
                    user_name.requestFocus();
                    return;
                }
                if (name_user.isEmpty()) {
                    nameI.setError("Name is empty");
                    nameI.requestFocus();
                    return;
                }
                if (userAge.isEmpty()) {
                    user_name.setError("Age is empty");
                    user_name.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    user_name.setError("Enter the valid email address");
                    user_name.requestFocus();
                    return;
                }


                if (password.isEmpty()) {
                    pass_word.setError("Enter the password");
                    pass_word.requestFocus();
                    return;
                }

                String str=age.getText().toString();
                if(Integer.parseInt(str)>100 && Integer.parseInt(str)>0){
                    age.setError("Age must be between 0 and 100");
                    age.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    pass_word.setError("Length of the password should be more than 6");
                    pass_word.requestFocus();
                    return;
                }

                if(!cnfrm.equals(password)){

                    Toast.makeText(RegisterActivity.this, "The password and confirmation password do not match.", Toast.LENGTH_SHORT).show();
                    pass_word.setError("Please Enter Again!");
                    cnf.setError("The password and confirmation password do not match");
                    pass_word.requestFocus();
                    return;
                }
                HashMap<String,Object>userMap=new HashMap<String,Object>();
                userMap.put("name",name_user);
                userMap.put("email",email);
                userMap.put("age",userAge);
                userMap.put("password",password);
                userMap.put("score",0);


                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.i(TAG, String.valueOf(userMap.size()));
                              mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).setValue(userMap);
                            Toast.makeText(RegisterActivity.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"Not Allowed Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });








    }


}