package com.example.hackthisfall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Logdoctor extends AppCompatActivity {
//EditText userN,userP;
private FirebaseAuth mAuth;

Button loginbtn;
TextView registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logdoctor);
        mAuth = FirebaseAuth.getInstance();
       loginbtn = findViewById(R.id.btnLogin);
       registerbtn = findViewById(R.id.lnkRegister);


       loginbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           userAuthentication();

           }
       });
       registerbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Logdoctor.this,registerActivity.class);
               startActivity(intent);

           }
       });




    }

//    private void showActivity() {
//        startActivity(new Intent(Logdoctor.this,registerActivity.class));
//    }

    private void userAuthentication() {
        EditText etmail = findViewById(R.id.txtemail);
        EditText etpassword = findViewById(R.id.txtPwd);

        String email = etmail.getText().toString();
        String password = etpassword.getText().toString();
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Please fill email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Logdoctor.this, "logged successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Logdoctor.this,searchPatient.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Logdoctor.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}