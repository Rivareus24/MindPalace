package com.okeechobee.rivareus24.mindpalace.J___Service_Bind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.okeechobee.rivareus24.mindpalace.J___Service_Bind.ServiceBind.LocalBinder;

import com.okeechobee.rivareus24.mindpalace.R;

public class ServiceBindActivity extends AppCompatActivity {

    private ServiceBind mService;
    private boolean mBound = false;
    private Button btnTalkToService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind);

        btnTalkToService = (Button) findViewById(R.id.btnTalkToService);
        btnTalkToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBound) return;

                int num = mService.getRandomNumber();
                Toast.makeText(ServiceBindActivity.this,
                        "number: " + num, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {  // 1
        super.onStart();
        Intent intent = new Intent(this, ServiceBind.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mBound = false;
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) { // 3
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
