package com.example.geschat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geschat.models.User;
import com.example.geschat.ui.login.LoginActivity;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private TextView usernameInDrawer, emailInDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference();



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener(toggle);

        //Get the drawer name and email
        View header = navigationView.getHeaderView(0);
        usernameInDrawer = header.findViewById(R.id.drawerUsername);
        emailInDrawer = header.findViewById(R.id.drawerEmail);



        //Pagina de inicio
        if(savedInstanceState == null){
            getSupportActionBar().setTitle("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        toggle.syncState();



        loadUserInformation();


    }

    private void loadUserInformation(){

        if (user != null) {
        emailInDrawer.setText(user.getEmail());

        //TODO cambiar users por user
        //TODO cambiar por display name
        DatabaseReference userRef = db.child("Users").child(user.getUid()).child("name");


            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.getValue(String.class);
                    usernameInDrawer.setText(name);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });


         }



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (auth.getCurrentUser()!=null && (auth.getCurrentUser().getDisplayName() == null || auth.getCurrentUser().getPhotoUrl() ==null)){
            finish();
            startActivity(new Intent(this, LoadUserInfo.class));
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        switch(menuItem.getItemId()){
            case R.id.nav_home:
                getSupportActionBar().setTitle("Home");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_chats:
                getSupportActionBar().setTitle("Chats");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportActionBar().setTitle("Profile");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_dashboard:
                intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);

                break;
            case R.id.nav_contact:
                getSupportActionBar().setTitle("Contact Us");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactFragment()).commit();
                break;
            case R.id.nav_close_session:
                intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                auth.signOut();
                break;


        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}
