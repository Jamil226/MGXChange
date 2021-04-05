package com.app.mgxchange;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.mgxchange.fragments.About;
import com.app.mgxchange.fragments.Home;
import com.app.mgxchange.fragments.Profile;
import com.app.mgxchange.fragments.Support;
import com.google.android.material.navigation.NavigationView;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout slider_layout;
    private LinearLayout order_layout, track_layout;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.parseColor("#064493"));

        fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout_dashboard);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(fragment==null){
            fragment = new Home();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_dashboard,fragment);
            fragmentTransaction.commit();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_privacy_policy) {
            Intent i = new Intent(Dashboard.this, PrivacyPolicy.class);
            startActivity(i);
        }
        else if(id == R.id.action_invite)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download Application here: https://www.facilesol.com/");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home)
        {
            setTitle(R.string.app_name);
            FragmentTransaction ft= getSupportFragmentManager ().beginTransaction ();
            ft.replace (R.id.frame_layout_dashboard, new Home());
            ft.commit ();
        }
        else if (id == R.id.nav_profile)
        {
            setTitle("Profile");
            FragmentTransaction ft= getSupportFragmentManager ().beginTransaction ();
            ft.replace (R.id.frame_layout_dashboard, new Profile());
            ft.commit ();
        }
        else if (id == R.id.nav_about)
        {
            setTitle("About Us");
            FragmentTransaction ft= getSupportFragmentManager ().beginTransaction ();
            ft.replace (R.id.frame_layout_dashboard, new About());
            ft.commit ();

        }
        else if (id == R.id.nav_support)
        {
            setTitle("Support");
            FragmentTransaction ft= getSupportFragmentManager ().beginTransaction ();
            ft.replace (R.id.frame_layout_dashboard, new Support());
            ft.commit ();

        }
        else if (id == R.id.nav_share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download Application here: https://play.google.com/store/apps/details?id=com.app.mgxchange");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}