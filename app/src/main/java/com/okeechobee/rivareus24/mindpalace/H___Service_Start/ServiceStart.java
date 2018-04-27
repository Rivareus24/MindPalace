package com.okeechobee.rivareus24.mindpalace.H___Service_Start;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class ServiceStart extends IntentService {

    public ServiceStart() {
        super("ServiceStart");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();

        TaskStart.executeTask(action, this);
    }
}
