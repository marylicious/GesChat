package com.example.geschat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RolChangeActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_change);

        Toolbar toolbar = findViewById(R.id.rol_change_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Assign user rights");

        radioGroup = findViewById(R.id.radiogroup);

        Button btn = findViewById(R.id.assignRightsBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                Toast.makeText(RolChangeActivity.this, "seleccionaste " + radioButton.getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    //Funcion inutil que te muestra el boton que selecciones con el getCheckedRadioButtonId(), con esto puedes hacer la comprobacion que quieres

}
