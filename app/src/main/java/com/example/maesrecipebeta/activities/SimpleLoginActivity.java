package com.example.maesrecipebeta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.activities.ui.login.CustomLoginActivity;

public class SimpleLoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText passWord;
    private Button signIn;
    private TextView register;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_login);
        this.userName = findViewById(R.id.signin_username);
        this.passWord = findViewById(R.id.signin_password);
        this.signIn = findViewById(R.id.signin_login);
        this.register = findViewById(R.id.signin_login_register);
        this.errorText = findViewById(R.id.signin_error_message);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SimpleLoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        this.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });


    }

    private void loggedIn(){
        Intent i = new Intent(SimpleLoginActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    private void errorLogin(){
        this.errorText.setVisibility(View.VISIBLE);
    }

    private void resultCheck(boolean signIn){
        if(signIn){
            loggedIn();
        }else{
            errorLogin();
        }
    }


    private void verify(){
        /*
        Amplify.Auth.signIn(
                userName.getText().toString(),
                passWord.getText().toString(),
                result ->resultCheck(result.isSignInComplete()),
                error -> Log.e("AuthQuickstart", error.toString())
        );*/
        Amplify.Auth.signIn(
                "TestingTester4",
                "seattle123",
                /*userName.getText().toString(),*/
                /*passWord.getText().toString(),*/
                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                error -> error.addSuppressed(new Exception("CATCH"))
        );

    }







}