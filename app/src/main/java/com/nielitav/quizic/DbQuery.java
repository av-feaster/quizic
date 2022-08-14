package com.nielitav.quizic;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DbQuery {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase;
//    DatabaseReference dbRef=firebaseDatabase.getReference("")

    DatabaseReference databaseReference = firebaseDatabase.getReference("users").child(Objects.requireNonNull(mAuth.getUid()));





}
