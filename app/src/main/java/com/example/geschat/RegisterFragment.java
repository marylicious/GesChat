package com.example.geschat;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.geschat.models.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {

    private EditText regFullName, regId, regEmail, regPassword, regConfPassword;
    private ImageButton regBtn;
    private ProgressBar regProgressBar;
    private FirebaseAuth auth;
    private CardView cardView;

    //TODO darken card view

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_register, container, false);

        regFullName = view.findViewById(R.id.regFullName);
        regId = view.findViewById(R.id.regId);
        regEmail = view.findViewById(R.id.regEmail);
        regConfPassword = view.findViewById(R.id.regconfPassword);
        regPassword = view.findViewById(R.id.regPassword);
        regProgressBar = view.findViewById(R.id.regProgressBar);
        regProgressBar.setVisibility(View.GONE);
        cardView = view.findViewById(R.id.cv);

        this.auth = FirebaseAuth.getInstance();

        regBtn = view.findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        return view;
    }

    public Boolean validateFields(String name, String email, String idPerson, String password, String confPassword){

        if (name.isEmpty()) {
            regFullName.setError(getString(R.string.input_error_name));
            regFullName.requestFocus();
            return false;
        }

        if (idPerson.isEmpty()) {
            regId.setError(getString(R.string.input_error_Id));
            regId.requestFocus();
            return false;
        }


        if (email.isEmpty()) {
            regEmail.setError(getString(R.string.input_error_email));
            regEmail.requestFocus();
            return false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            regEmail.setError(getString(R.string.input_error_email_invalid));
            regEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            regPassword.setError(getString(R.string.input_error_password));
            regPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            regPassword.setError(getString(R.string.input_error_password_length));
            regPassword.requestFocus();
            return false;
        }

        if (confPassword.isEmpty()) {
            regConfPassword.setError(getString(R.string.input_error_password));
            regConfPassword.requestFocus();
            return false;
        }

        if (confPassword.equals(password) == false) {
            regConfPassword.setError(getString(R.string.input_error_passwordDontMatch));
            regConfPassword.requestFocus();
            return false;
        }

        return true;
    }

    public void registerUser(){

       //Fields
        final String name = regFullName.getText().toString().trim();
        final String email = regEmail.getText().toString().trim();
        final String idPerson = regId.getText().toString().trim();
        String password = regPassword.getText().toString().trim();
        String confPassword = regConfPassword.getText().toString().trim();
        final String photo = "urlphoto";
        final String role = "scholar";


        //Validation
        if(validateFields(name, email, idPerson, password, confPassword) == true){


            regProgressBar.setVisibility(View.VISIBLE);
            regProgressBar.setIndeterminate(true);
            regProgressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
            regFullName.setFocusable(false);
            regEmail.setFocusable(false);
            regId.setFocusable(false);
            regPassword.setFocusable(false);
            regConfPassword.setFocusable(false);
            cardView.setCardBackgroundColor(Color.parseColor("#f0f3f7"));

            this.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            regProgressBar.setVisibility(View.GONE);
                            regFullName.setFocusableInTouchMode(true);
                            regEmail.setFocusableInTouchMode(true);
                            regId.setFocusableInTouchMode(true);
                            regPassword.setFocusableInTouchMode(true);
                            regConfPassword.setFocusableInTouchMode(true);
                            cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));


                            if (task.isSuccessful()) {

                                User user = new User(name, email, idPerson,photo,role);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(getContext(), getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getContext(),MainActivity.class));
                                        } else {
                                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }


    }



}




