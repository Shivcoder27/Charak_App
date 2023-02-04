package com.example.hackthisfall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class registerActivity extends AppCompatActivity {
TextView alreadyacc;

DatabaseReference databaseReference;
 FirebaseDatabase firebaseDatabase;
Button submitbtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
       submitbtn = findViewById(R.id.btnSubmit);
        alreadyacc = findViewById(R.id.loginpage);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();





        ///////////////////////////////////////////////////////
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        ////////////////////////////////////////////////////////
        alreadyacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerActivity.this,Logdoctor.class));
            }
        });
        /////////////////////////////////////////////////////////
    }
    private void registerUser() {
        EditText remail = findViewById(R.id.mail);
        EditText rpassword = findViewById(R.id.userPassword);
        EditText rfullname = findViewById(R.id.userFullname);
        EditText rcontact = findViewById(R.id.userContact);
        EditText rregID = findViewById(R.id.doctorRegid);
        EditText rbatchyear = findViewById(R.id.passoutBatch);
        EditText rspecialization = findViewById(R.id.usercomment);


        String email = remail.getText().toString();
        String  password= rpassword.getText().toString();
        String fullname = rfullname.getText().toString();
        String contact = rcontact.getText().toString();
        String registrationId = rregID.getText().toString();
        String  passoutBatch= rbatchyear.getText().toString();
        String specialization = rspecialization.getText().toString();

//        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("email",email);
//        hashMap.put("password",password);
//        hashMap.put("Name",fullname);
//        hashMap.put("contact",contact);
//        hashMap.put("DoctorID",registrationId);
//        hashMap.put("passOut",passoutBatch);
//        hashMap.put("Core_skills",specialization);


        if(email.isEmpty() || fullname.isEmpty() || password.isEmpty() || contact.isEmpty() || registrationId.isEmpty() || passoutBatch.isEmpty() || specialization.isEmpty()){
            Toast.makeText(this, "please fill All blocks", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(registerActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(email,password,fullname,contact,registrationId,passoutBatch,specialization);
                            FirebaseDatabase.getInstance().getReference("user")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(registerActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(registerActivity.this,searchPatient.class);
                                            startActivity(intent);
                                        }
                                    });

                        } else {
                            Toast.makeText(registerActivity.this, "Authentication failed..", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }

//    private void switchTologin() {
//        startActivity(new Intent(registerActivity.this,searchPatient.class));
//    }


}