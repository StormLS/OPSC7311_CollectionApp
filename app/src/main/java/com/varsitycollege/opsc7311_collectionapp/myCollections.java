package com.varsitycollege.opsc7311_collectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class myCollections extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //Initializing TOOLBAR variables
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleOnOff;
    private NavigationView navigationView;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collections);

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
        //navigationView.setNavigationItemSelectedListener(this);
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
                startActivity(new Intent(this, dashboard.class));

                return true;


            case R.id.nav_Categories:
                //startActivity(new Intent(getApplicationContext(), myCategories.class));
                startActivity(new Intent(this, dashboard.class));
                return true;

            case R.id.nav_Analytics:
                //startActivity(new Intent(getApplicationContext(), analytics.class));
                startActivity(new Intent(this, dashboard.class));
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