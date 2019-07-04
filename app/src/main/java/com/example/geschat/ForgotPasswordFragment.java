package com.example.geschat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment {
    ImageButton btn;

    TextView fEmail, fConfEmail;

    FirebaseAuth auth;

    ProgressBar progressBar;

    CardView cardView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_forgot_password, container,false);

        btn = view.findViewById(R.id.regBtn);
        fEmail = view.findViewById(R.id.regEmail);
        fConfEmail = view.findViewById(R.id.confEmail);
        auth = FirebaseAuth.getInstance();
        progressBar = view.findViewById(R.id.forgotProgressBar);
        progressBar.setVisibility(View.GONE);
        cardView = view.findViewById(R.id.cv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return view;
    }

    public void sendEmail(){
        final String email = fEmail.getText().toString().trim();
        final String confEmail =  fConfEmail.getText().toString().trim();

        if (email.isEmpty()) {
            fEmail.setError(getString(R.string.input_error_email));
            fEmail.requestFocus();
            return;
        }

        if (confEmail.isEmpty()) {
            fConfEmail.setError(getString(R.string.input_error_email));
            fConfEmail.requestFocus();
            return;
        }

        if (confEmail.equals(email) == false) {
            fConfEmail.setError("Confirmation must match email");
            fConfEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
        fEmail.setFocusable(false);
        fConfEmail.setFocusable(false);
        cardView.setCardBackgroundColor(Color.parseColor("#f0f3f7"));

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {


                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
                        fEmail.setFocusableInTouchMode(true);
                        fConfEmail.setFocusableInTouchMode(true);

                        if(task.isSuccessful()){

                            Toast.makeText(getContext(), "An email with instructions was sent to your email", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getContext(), LoginActivity.class));
                        }
                        else{
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
