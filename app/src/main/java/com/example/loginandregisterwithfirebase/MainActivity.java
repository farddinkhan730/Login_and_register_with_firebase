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

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    FirebaseAuth firebaseAuth;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editTextTextEmailAddress);
        e2=findViewById(R.id.editTextTextPassword2);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        p1=findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
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
                    Toast.makeText(MainActivity.this, "plz Fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(s2.isEmpty()){
                        Toast.makeText(MainActivity.this, "plz fill password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                p1.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "You have succesfully registerde", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                            Intent j=new Intent(MainActivity.this,Welcome.class);
                            startActivity(j);
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "You are not registerd plz registerd first", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });
    }
}