package com.okeechobee.rivareus24.mindpalace.J___Service_Bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

public class ServiceBind extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {  // 2
        return mBinder;
    }

    public class LocalBinder extends Binder {
        ServiceBind getService() {  // 4
            return ServiceBind.this;
        }
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
