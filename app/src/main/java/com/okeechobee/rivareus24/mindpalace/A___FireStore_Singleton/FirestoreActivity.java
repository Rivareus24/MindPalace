package com.okeechobee.rivareus24.mindpalace.A___FireStore_Singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.okeechobee.rivareus24.mindpalace.R;
import com.okeechobee.rivareus24.mindpalace.Utilities.Callback;

public class FirestoreActivity extends AppCompatActivity {

    private Button btnShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore);

        btnShowData = (Button) findViewById(R.id.btnShowData);
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().addData();

                Singleton.getInstance().getData(new Callback() {
                    @Override
                    public void onSuccess(Object data) {
                        // qui arriva l'Object che abbiamo castato
                        // TODO mostriamo il ritorno sotto il bottone
                    }

                    @Override
                    public void onFailure(Error error, String message) {
                        // Cosa cambia da fare le cose qui o qui
                    }
                });

            }
        });
    }
}
