package com.okeechobee.rivareus24.mindpalace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.okeechobee.rivareus24.mindpalace.E___ASyncTaskLoader_SavedInstance.AsyncTaskActivity;
import com.okeechobee.rivareus24.mindpalace.A___FireStore_Singleton.FirestoreActivity;
import com.okeechobee.rivareus24.mindpalace.D___Intent.IntentActivity;
import com.okeechobee.rivareus24.mindpalace.B___Menu_Preferences.MenuActivity;
import com.okeechobee.rivareus24.mindpalace.C___RecyclerView_Toast_Swipe.RecyclerViewActivity;
import com.okeechobee.rivareus24.mindpalace.F___SqlLite.SQLiteActivity;
import com.okeechobee.rivareus24.mindpalace.G___ContentProvider.ContentProviderActivity;
import com.okeechobee.rivareus24.mindpalace.H___Service_Start.ServiceStartActivity;
import com.okeechobee.rivareus24.mindpalace.I___Service_Job.ServiceJobActivity;
import com.okeechobee.rivareus24.mindpalace.J___Service_Bind.ServiceBindActivity;
import com.okeechobee.rivareus24.mindpalace.K___Notifications.NotificationsActivity;
import com.okeechobee.rivareus24.mindpalace.L___Broadcast_Receiver.BroadcastReceiverActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnFirestore;
    private Button btnMenu;
    private Button btnRecyclerView;
    private Button btnIntent;
    private Button btnAsyncTask;
    private Button btnSQLite;
    private Button btnContentProvider;
    private Button btnServiceStart;
    private Button btnServiceJob;
    private Button btnServiceBind;
    private Button btnNotifications;
    private Button btnBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO FireStore + Singleton
        btnFirestore = (Button) findViewById(R.id.btnFirestore);
        btnFirestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirestoreActivity.class));
            }
        });

        // TODO Menu / Preferences
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });

        // TODO RecyclerView + Toast + Swipe
        btnRecyclerView = (Button) findViewById(R.id.btnRecyclerView);
        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });

        // TODO Intent
        btnIntent = (Button) findViewById(R.id.btnIntent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IntentActivity.class);
                i.putExtra(Intent.EXTRA_TEXT, "Testo inviato tramite intent");
                startActivity(i);
            }
        });

        // TODO ASyncTaskLoader + SavedInstanceSet
        btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        btnAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
            }
        });

        // TODO SQLite
        btnSQLite = (Button) findViewById(R.id.btnSQLite);
        btnSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SQLiteActivity.class));
            }
        });

        // TODO ContentProvider (8.1)
        btnContentProvider = (Button) findViewById(R.id.btnContentProvider);
        btnContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
            }
        });

        // TODO startService()
        btnServiceStart = (Button) findViewById(R.id.btnServiceStart);
        btnServiceStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServiceStartActivity.class));
            }
        });

        // TODO scheduleService()
        btnServiceJob = (Button) findViewById(R.id.btnServiceJob);
        btnServiceJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServiceJobActivity.class));
            }
        });

        // TODO bindService()
        btnServiceBind = (Button) findViewById(R.id.btnServiceBind);
        btnServiceBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServiceBindActivity.class));
            }
        });

        // TODO Notifications
        btnNotifications = (Button) findViewById(R.id.btnNotifications);
        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
            }
        });

        // TODO BroadCastReceiver
        btnBroadcastReceiver = (Button) findViewById(R.id.btnBroadcastReceiver);
        btnBroadcastReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BroadcastReceiverActivity.class));
            }
        });

        // TODO Fragments

        // TODO Testing

        // TODO have a look
        // Driver driver = new GooglePlayDriver(MainActivity.this);
    }
}
