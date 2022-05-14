package com.varsitycollege.opsc7311_collectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
                Intent i = new Intent(dashboard.this, myCollections.class);
                startActivity(i);
                finish();
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
        toggleOnOff.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
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
            case R.id.nav_Home:
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                finish();
                return true;

            case R.id.nav_Collections:
                startActivity(new Intent(getApplicationContext(), myCollections.class));
                finish();
                return true;

            case R.id.nav_Categories:
                //startActivity(new Intent(getApplicationContext(), myCategories.class));
                return true;

            case R.id.nav_Analytics:
                //startActivity(new Intent(getApplicationContext(), analytics.class));
                return true;

            case R.id.nav_Contact:
                //startActivity(new Intent(getApplicationContext(), contact.class));
                return true;

            case R.id.nav_FAQ:
                //startActivity(new Intent(getApplicationContext(), faq.class));
                return true;

            case R.id.nav_About:
                //startActivity(new Intent(getApplicationContext(), about.class));
                return true;

            case R.id.nav_Settings:
                //startActivity(new Intent(getApplicationContext(), settings.class));
                return true;

            case R.id.nav_Account:
                //startActivity(new Intent(getApplicationContext(), account.class));
                return true;

            case R.id.nav_Logout:
                //startActivity(new Intent(getApplicationContext(), logout.class));
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


