package com.example.geschat.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.ForgotPasswordFragment;
import com.example.geschat.MainActivity;
import com.example.geschat.R;
import com.example.geschat.RegisterFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ImageButton logBtn;
    private TextView txt, emailTv, passwordTv;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private CardView cardView;

    //TODO darken card view

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logBtn = findViewById(R.id.logBtn);
        emailTv = findViewById(R.id.logUsername);
        passwordTv = findViewById(R.id.logPassword);
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.logProgressBar);
        progressBar.setVisibility(View.GONE);
        cardView = findViewById(R.id.logCv);


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });


        //Codigo para pasar de login page to registration page
        txt = (TextView) findViewById(R.id.logRegister);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fM = getSupportFragmentManager();
                RegisterFragment regFrag = new RegisterFragment();
                fM.beginTransaction().replace(R.id.logContainer,regFrag).commit();
            }
        });

        //Codigo para pasar de login page to forgot password
        txt = (TextView) findViewById(R.id.logForgotPassword);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fM = getSupportFragmentManager();
                ForgotPasswordFragment forgFrag = new ForgotPasswordFragment();
                fM.beginTransaction().replace(R.id.logContainer,forgFrag).commit();
            }
        });



        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
        }

    }


    public void logIn(){
        String email = emailTv.getText().toString().trim();
        String password = passwordTv.getText().toString().trim();

        //Validation
        if (email.isEmpty()) {
            emailTv.setError(getString(R.string.input_error_email));
            emailTv.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordTv.setError(getString(R.string.input_error_password));
            passwordTv.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
       cardView.setCardBackgroundColor(Color.parseColor("#f0f3f7"));
        passwordTv.setFocusable(false);
       emailTv.setFocusable(false);

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
               passwordTv.setFocusableInTouchMode(true);
               emailTv.setFocusableInTouchMode(true);

                if (task.isSuccessful()) {

                    finish();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (auth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }


}


