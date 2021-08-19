package com.example.ecommercyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    Button btn_signup;
    EditText name, email, pass;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser()!=null){
//
//            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        btn_signup = findViewById(R.id.btn_signup);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String useremail = email.getText().toString();
                String userpass = pass.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegistrationActivity.this, "Enter your name!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(useremail)) {
                    Toast.makeText(RegistrationActivity.this, "Enter your email!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userpass)) {
                    Toast.makeText(RegistrationActivity.this, "Enter your Password!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userpass.length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Password too short,enter +6 character ", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(useremail, userpass)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this, "-- Successful Register --", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "-- Registration fail --\n" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });


    }

    public void signin(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);

        startActivity(intent);

    }
}