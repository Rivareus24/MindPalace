package com.okeechobee.rivareus24.mindpalace.E___ASyncTaskLoader_SavedInstance;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.okeechobee.rivareus24.mindpalace.R;

public class AsyncTaskActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final int ID = 123456789;
    private ProgressBar progressBar;
    private EditText etInsertName;
    private Button btnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        etInsertName = (EditText) findViewById(R.id.etInsertName);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 3 + 4;
                Log.d("XXXXXXXXXXXXXXX","WWWWWWWWWWWWWWWWWWWWWWWW");
                startAsyncTask();
            }
        });

        if(savedInstanceState != null) {
            String s = savedInstanceState.getString("Banana");
            etInsertName.setText(s);
        }

        // --> onCreateLoader
        getSupportLoaderManager().initLoader(ID, null, this);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(AsyncTaskActivity.this) {
            @Override
            protected void onStartLoading() {
                if (args == null) return;
                else {
                    // Ruotando lo schermo sparisce la rotellina
                    progressBar.setVisibility(View.VISIBLE);
                    forceLoad();    // --> LoadInBackground
                }
            }

            @Override
            public String loadInBackground() {
                // code
                SystemClock.sleep(3000);
                // Questa stringa finisce in onLoadFinished come data
                return etInsertName.getText().toString();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        progressBar.setVisibility(View.INVISIBLE);
        btnStart.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) { }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Banana", etInsertName.getText().toString());
    }

    private void startAsyncTask(){
        Bundle bundle = new Bundle();

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> searchLoader = loaderManager.getLoader(ID);

        // Entrambe --> onCreateLoader
        if (searchLoader == null) {
            loaderManager.initLoader(ID, bundle, this);
        } else {
            loaderManager.restartLoader(ID, bundle, this);
        }
    }

}
