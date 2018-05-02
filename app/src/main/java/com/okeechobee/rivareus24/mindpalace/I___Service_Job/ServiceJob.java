package com.okeechobee.rivareus24.mindpalace.I___Service_Job;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class ServiceJob extends JobService {

    private AsyncTask asyncTask;
    private static final String ACTION1 = "ACTION 1";
    private static final String ACTION2 = "ACTION 2";

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters job) {

        asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mExecuteJob(ACTION1, ServiceJob.this);

                return true;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                // Needs Reschedule
                jobFinished(job, false);
            }
        };

        asyncTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        return true;
    }

    public static void mExecuteJob(String action_name, Context context){
        switch (action_name){
            case ACTION1:
                // azione asincrona da fare in background tramite il service

                break;
            case ACTION2:
                // azione asincrona da fare in background tramite il service

            default:
        }
    }
}
