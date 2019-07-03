package com.example.geschat.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geschat.ForgotPasswordFragment;
import com.example.geschat.MainActivity;
import com.example.geschat.R;
import com.example.geschat.RegisterFragment;

public class LoginActivity extends AppCompatActivity {


    private Button button; //Linea agregada para el cambio de abajo

    private TextView txt; //Linea agregada para abrir el reg

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicio de codigo para Cambiar de actividad(pasar de login a menu)
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
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

    }

    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
        //FIN de cambiar actividad
        // Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();


