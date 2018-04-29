package com.okeechobee.rivareus24.mindpalace.H___Service_Start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.okeechobee.rivareus24.mindpalace.R;

public class ServiceStartActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_start);

        intent = new Intent(this, ServiceStart.class);
        intent.setAction(ServiceStart.ACTION1);

        startService(intent);
    }
}
