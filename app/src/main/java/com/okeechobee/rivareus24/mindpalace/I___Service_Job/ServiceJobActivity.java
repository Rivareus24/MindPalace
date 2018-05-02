package com.okeechobee.rivareus24.mindpalace.I___Service_Job;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.okeechobee.rivareus24.mindpalace.R;

public class ServiceJobActivity extends AppCompatActivity {

    private FirebaseJobDispatcher dispatcher;
    private static boolean mScheduled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_job);

        schedule();
    }

    synchronized private void schedule(){

        if(mScheduled) return;

        dispatcher = new FirebaseJobDispatcher(
                new GooglePlayDriver(ServiceJobActivity.this));

        // GUARDA la doc
        // https://github.com/firebase/firebase-jobdispatcher-android
        Job myJob = dispatcher.newJobBuilder()
                .setService(ServiceJob.class)
                .setTag("job-service-example")
                .build();

        dispatcher.mustSchedule(myJob);

        mScheduled = true;
    }

}
