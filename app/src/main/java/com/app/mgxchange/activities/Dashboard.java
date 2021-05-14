package com.app.mgxchange.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.mgxchange.R;
import com.app.mgxchange.fragments.Home;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;
import com.app.mgxchange.utils.ApiUrls;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String TAG = "Dashboard";
    private RelativeLayout slider_layout;
    private LinearLayout order_layout, track_layout;
    Fragment fragment;
    CircularImageView userProfile;
    TextView fullName, userEmail;
    View headerView;
    FragmentTransaction fragmentTransaction;
    UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout_dashboard);
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        String userID = userSharedPrefManager.getUser().getUserID();
        String firstName = userSharedPrefManager.getUser().getFirstName();
        String lastName = userSharedPrefManager.getUser().getLastName();
        String imagePath = userSharedPrefManager.getUser().getImagePath();
        String email = userSharedPrefManager.getUser().getEmail();
        Log.d(TAG, "User ID = " + userID);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        userProfile = (CircularImageView) headerView.findViewById(R.id.ivDashboardUserProfile);
        fullName = (TextView) headerView.findViewById(R.id.tvDashboardUserFullName);
        userEmail = (TextView) headerView.findViewById(R.id.tvDashboardUserEmail);
        Glide.with(getApplicationContext())
                .load(ApiUrls.imgParentUrl + imagePath)
                .placeholder(R.drawable.image_default)
                .into(userProfile);
        fullName.setText(firstName + " " + lastName);
        userEmail.setText(email);
        navigationView.setNavigationItemSelectedListener(this);
        if (fragment == null) {
            fragment = new Home();
            setTitle("Dashboard");
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_dashboard, fragment);
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
        } else if (id == R.id.action_invite) {
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            setTitle("Dashboard");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout_dashboard, new Home());
            ft.commit();
        }
        if (id == R.id.nav_sell_or_loan) {
            Intent intent = new Intent(getApplicationContext(), LoanOrSellSelection.class);
            startActivity(intent);
        } else if (id == R.id.nav_user_profile) {
            Intent intent = new Intent(getApplicationContext(), DetailedUserProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_track_item) {
            Intent intent = new Intent(getApplicationContext(), ItemsList.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user_id", "null");
            editor.putString("email", "null");
            editor.putString("firstName", "null");
            editor.putString("lastName", "null");
            editor.putString("contact", "null");
            editor.putString("imagePath", "null");
            editor.putString("address", "null");
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), Welcome.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}