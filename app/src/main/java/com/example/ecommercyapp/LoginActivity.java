package com.example.ecommercyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button btn_signin;
    EditText pass,email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_signin=findViewById(R.id.btn_signin);

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();


        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString();
                String userpass = pass.getText().toString();

                if (TextUtils.isEmpty(useremail)) {
                    Toast.makeText(LoginActivity.this, "Enter your email!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userpass)) {
                    Toast.makeText(LoginActivity.this, "Enter your Password!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userpass.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Password too short,enter +6 character ", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(useremail,userpass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(LoginActivity.this, "-- Login Successful --", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                                    startActivity(intent);
                                }else {
                                    Toast.makeText(LoginActivity.this, "## Error in login ## \n"+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }
        });
    }

    public void signin(View view) {
        Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);

        startActivity(intent);
    }
}