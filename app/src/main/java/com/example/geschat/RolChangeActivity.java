package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RolChangeActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    Button btn;
    DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_change);

        Toolbar toolbar = findViewById(R.id.rol_change_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Assign user rights");

        radioGroup = findViewById(R.id.radiogroup);
        textView = findViewById(R.id.rol_email);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        btn = findViewById(R.id.assignRightsBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeUserRole();

            }
        });
    }

    //TODO falla cuando el email no esta en la DB
    private void changeUserRole(){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        final String newRole =  radioButton.getText().toString().toLowerCase();
        String email = textView.getText().toString().trim().toLowerCase();
        btn.setEnabled(false);

        Query emailQuery = userRef.orderByChild("email").equalTo(email);

        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        if (data != null) {
                            String keys = data.getKey();
                            changeRoleUserWithKey(keys, newRole);
                        } else {

                            Toast.makeText(getApplicationContext(), "Error fetching user, please introduce a valid email", Toast.LENGTH_LONG).show();
                        }

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error fetching user, please introduce a valid email", Toast.LENGTH_LONG).show();
                }
                btn.setEnabled(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error fetching user, please introduce a valid email", Toast.LENGTH_LONG).show();
                btn.setEnabled(true);
            }
        });

    }

    private void changeRoleUserWithKey(String userKey, String newRole){

        userRef.child(userKey).child("role").setValue(newRole).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                btn.setEnabled(true);

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User role changed successfully", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }


}
