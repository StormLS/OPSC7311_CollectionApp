package com.varsitycollege.opsc7311_collectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //Initializing all my variables here
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleOnOff;
    private NavigationView navigationView;

    private ImageButton btn_mycollections;
    private ImageButton btn_allcatagories;
    private ImageButton btn_analytics;
    private ImageButton btn_settings;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_with_nav_drawer);

        //---- findViewByID() & setOnClickListener() here for Dashboard variables ----
        btn_mycollections = findViewById(R.id.btn_mycollections);
        btn_allcatagories = findViewById(R.id.btn_allcatagories);
        btn_analytics = findViewById(R.id.btn_analytics);
        btn_settings = findViewById(R.id.btn_settings);

        btn_mycollections.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast btn_mycollections_pressed = Toast.makeText(getApplicationContext(), "'My Collections' button was pressed.. do x", Toast.LENGTH_SHORT);
                btn_mycollections_pressed.show();
                // TODO: Intent i = new Intent(dashboard.this, mycollections.class);
                //       startActivity(i);
            }
        });

        btn_allcatagories.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast btn_allcatagories_pressed = Toast.makeText(getApplicationContext(), "'All Catagories' button was pressed.. do x", Toast.LENGTH_SHORT);
                btn_allcatagories_pressed.show();
                // TODO: Intent i = new Intent(dashboard.this, allcatagories.class);
                //       startActivity(i);
            }
        });

        btn_analytics.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast btn_analytics_pressed = Toast.makeText(getApplicationContext(), "'Analytics' button was pressed.. do x", Toast.LENGTH_SHORT);
                btn_analytics_pressed.show();
                // TODO: Intent i = new Intent(dashboard.this, analytics.class);
                //       startActivity(i);
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast btn_settings_pressed = Toast.makeText(getApplicationContext(), "'Settings' button was pressed.. do x", Toast.LENGTH_SHORT);
                btn_settings_pressed.show();
                // TODO: Intent i = new Intent(dashboard.this, settings.class);
                //       startActivity(i);
            }
        });
        //-----------------------------------------------------

        //----- Code that manages and sets up the toolbar and the navigation of said tool bar -----
        toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggleOnOff = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggleOnOff);
        toggleOnOff.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        //-----------------------------------------------------------------------------------------


    }
    ///Stops the screen from going back a window when the back button is pressed with a open nav bar
    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }
    }

    //Manages all the buttons on the Burger Menu; such as if a user presses any of these buttons
    // FAQ, Contact Us, Settings, Logout
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_FAQ:
                Toast faq_btn_pressed = Toast.makeText(getApplicationContext(), "FAQ button was pressed.. do x", Toast.LENGTH_SHORT);
                faq_btn_pressed.show();
                //TODO: Add methods to go to the next screen
                return true;

            case R.id.nav_contact:
                Toast contact_btn_pressed = Toast.makeText(getApplicationContext(), "Contact Us button was pressed.. do y", Toast.LENGTH_SHORT);
                contact_btn_pressed.show();
                //TODO: Add methods to go to the next screen
                return true;

            case R.id.nav_settings:
                Toast settings_btn_pressed = Toast.makeText(getApplicationContext(), "Settings button was pressed.. do z", Toast.LENGTH_SHORT);
                settings_btn_pressed.show();
                //TODO: Add methods to go to the next screen
                return true;

            case R.id.nav_logout:
                Toast logout_btn_pressed = Toast.makeText(getApplicationContext(), "Logout button was pressed.. do h", Toast.LENGTH_SHORT);
                logout_btn_pressed.show();
                //TODO: Add methods to go to the next screen
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}