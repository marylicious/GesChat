package com.example.geschat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment {
    ImageButton btn;

    TextView email, confEmail;

    FirebaseAuth auth;

    //TODO progressbar

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_forgot_password, container,false);

        btn = view.findViewById(R.id.regBtn);
        email = view.findViewById(R.id.regEmail);
        confEmail = view.findViewById(R.id.confEmail);
        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return view;
    }

    public void sendEmail(){
        final String email = this.email.getText().toString().trim();
        final String confEmail = this.confEmail.getText().toString().trim();

        if (email.isEmpty()) {
            this.email.setError(getString(R.string.input_error_email));
            this.email.requestFocus();
            return;
        }

        if (confEmail.isEmpty()) {
            this.confEmail.setError(getString(R.string.input_error_email));
            this.confEmail.requestFocus();
            return;
        }

        if (confEmail.equals(email) == false) {
            this.confEmail.setError("Confirmation must match email");
            this.confEmail.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Email with instructions send to your email", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
