package com.okeechobee.rivareus24.mindpalace.G___ContentProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.okeechobee.rivareus24.mindpalace.D___Intent.IntentActivity;
import com.okeechobee.rivareus24.mindpalace.R;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final int ID = 987654;
    private ProgressBar progressBarCP;
    private TextView tvContactName;

    private List<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        progressBarCP = (ProgressBar) findViewById(R.id.progressBarCP);
        tvContactName = (TextView) findViewById(R.id.tvContactName);

        // permission? quindi dobbiamo gestirle sempre?
        mAskPermission();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(ContentProviderActivity.this){
            @Override
            protected void onStartLoading() {
                // TODO se ruoto lo schermo la rotellina sparisce, ma il task viene completato correttamente
                progressBarCP.setVisibility(View.VISIBLE);
                forceLoad();    // --> LoadInBackground
            }

            @Override
            public String loadInBackground() {

                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(
                        ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);

                int columnIndex;
                String value = "";
                values = new ArrayList<>();
                String columnName = "DISPLAY_NAME_PRIMARY";

                while(cursor.moveToNext()){
                    // La classe Contract organizza le variabili in classi di classi
                    columnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY);
                    value = cursor.getString(columnIndex);
                    values.add(value);
                    // save locally
                }

                SystemClock.sleep(7000);

                return values.get(0);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        progressBarCP.setVisibility(View.INVISIBLE);
        tvContactName.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void mAskPermission() {
        if (ContextCompat.checkSelfPermission(ContentProviderActivity.this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED){
            getSupportLoaderManager().initLoader(ID, null, this);
        } else{
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)){
                Toast.makeText(ContentProviderActivity.this, "Content Provider permission denied", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 2000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 2000){
            // TODO grantResults capire come funzia
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getSupportLoaderManager().initLoader(ID, null, this);
            } else {
                Toast.makeText(ContentProviderActivity.this, "Permission not granted", Toast.LENGTH_LONG).show();
            }
        } else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
