package com.okeechobee.rivareus24.mindpalace.B___Menu_Preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.okeechobee.rivareus24.mindpalace.R;

public class MenuActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Toast toast;
    private SharedPreferences sharedPreferences;

    private static final String COLOR_RED = "red";
    private static final String COLOR_BLUE = "blue";
    private static final String COLOR_GREEN = "green";

    private static final String NIGHT_MODE = "nightMode";
    private static final String BACKGROUND = "background";
    private static final String USERNAME = "username";

    private TextView tvUsername;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvUsername = (TextView) findViewById(R.id.tvUsername);

        setUpPreferences();
    }

    private void setUpPreferences(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Setta i colori solo di questa activity
        setNightMode(sharedPreferences.getBoolean(
                getString(R.string.pref_night_mode_key),
                getResources().getBoolean(R.bool.pref_night_mode_default)));

        setBackgroundColor(sharedPreferences.getString(
                getString(R.string.pref_background_key),
                getResources().getString(R.string.pref_background_default_value)));

        setUsername(sharedPreferences.getString(
                "username",
                getResources().getString(R.string.pref_username_default_value)
        ));

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key){
            case NIGHT_MODE:
                setNightMode(sharedPreferences.getBoolean(key,
                        getResources().getBoolean(R.bool.pref_night_mode_default)));
                break;
            case BACKGROUND:
                setBackgroundColor(sharedPreferences.getString(key,
                        getResources().getString(R.string.pref_background_default_value)));
                break;
            case USERNAME:
                setUsername(sharedPreferences.getString(key,
                        getResources().getString(R.string.pref_username_default_value)));
                break;
            default:
        }
    }

    private void setNightMode(boolean x){
        View view = this.getWindow().getDecorView();

        if (x) {
            view.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    private void setBackgroundColor(String s) {
        View view = this.getWindow().getDecorView();

        switch (s){
            case COLOR_RED:
                view.setBackgroundColor(Color.RED);
                break;
            case COLOR_BLUE:
                view.setBackgroundColor(Color.BLUE);
                break;
            case COLOR_GREEN:
                view.setBackgroundColor(Color.GREEN);
                break;
            default:
        }
    }

    private void setUsername(String username) {
        tvUsername.setText("Ciao " + username + "!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (toast != null) toast.cancel();

        switch (id){
            case R.id.itmCiao:
                toast = Toast.makeText(this, "Ciao", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.itmMirko:
                toast = Toast.makeText(this, "Mirko", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.itmLeso:
                toast = Toast.makeText(this, "Leso", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.preferences:
                startActivity(new Intent(this, PreferencesActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
