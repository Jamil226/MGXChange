package com.app.mgxchange.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.mgxchange.models.User;

public class UserSharedPrefManager {

    private static final String SHARED_PREF_NAME = "UserPref";
    Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserSharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_id", user.getUserID());
        editor.putString("first_name", user.getFirstName());
        editor.putString("last_name", user.getLastName());
        editor.putString("login_method", user.getLoginMethod());
        editor.putString("email", user.getEmail());
        editor.putString("contact", user.getContact());
        editor.putString("address", user.getAddress());
        editor.putString("image_path", user.getImagePath());
        editor.putBoolean("login_status", true);
        editor.apply();

    }

    public boolean isLoggedIn(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login_status", false);
    }

    public User getUser() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("user_id", "null"),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("login_method", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("contact", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("image_path", null));
    }

    public void logout() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
