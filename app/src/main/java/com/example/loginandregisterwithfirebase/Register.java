package com.example.loginandregisterwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    FirebaseAuth firebaseAuth;
    ProgressBar p1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=findViewById(R.id.editTextTextEmailAddress2);
        e2=findViewById(R.id.editTextTextPassword);
        b1=findViewById(R.id.button5);
        b2=findViewById(R.id.button4);
        p1=findViewById(R.id.progressBar1);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(s1.isEmpty()){
                    Toast.makeText(Register.this, "plz Fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(s2.isEmpty()){
                        Toast.makeText(Register.this, "plz fill password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                p1.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Data base registered succesfull ", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                            Intent j=new Intent(Register.this,MainActivity.class);
                            startActivity(j);
                            finish();
                            Toast.makeText(Register.this, "plz enter registered email to login", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Register.this, "Not Updated", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                        }

                    }
                });
                
            }
        });
    }
}