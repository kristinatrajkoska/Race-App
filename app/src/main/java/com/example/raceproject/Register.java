package com.example.raceproject;

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
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText fname,lname,birth,emailR,passwordR;
    Button reg,log;
    FirebaseAuth mAuth;


    //ovde so ako veke postoi user prvo
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), Welcome.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        fname=(EditText) findViewById(R.id.firstName);
        lname=(EditText) findViewById(R.id.lastName);
        birth=(EditText) findViewById(R.id.birthDate);
        emailR=(EditText) findViewById(R.id.emailReg);
        passwordR=(EditText) findViewById(R.id.passwordReg);
        reg=(Button) findViewById(R.id.Register);
        log=(Button) findViewById(R.id.loginReg);

        //napraj da odis nazad kon login
        log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name,surname,dateB,email,pass;
                name=String.valueOf(fname.getText());
                surname=String.valueOf(lname.getText());
                dateB=String.valueOf(birth.getText());
                email=String.valueOf(emailR.getText());
                pass=String.valueOf(passwordR.getText());


                if(TextUtils.isEmpty(name)){
                    Toast.makeText(Register.this,"Enter a first name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(surname)){
                    Toast.makeText(Register.this,"Enter a last name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(dateB)){
                    Toast.makeText(Register.this,"Enter a date of birth",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Enter an email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Register.this,"Enter a password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this,"Account created",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


    }
}