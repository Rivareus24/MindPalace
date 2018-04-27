package com.okeechobee.rivareus24.mindpalace.A___FireStore_Singleton;

/**
 * Created by rivareus24 on 29/03/2018.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.okeechobee.rivareus24.mindpalace.Utilities.Callback;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * 1) [AndroidStudio\Tools\Firebase\] and "Connect to Firebase"
 *
 * 2) Download google-services.json and put in [MindPalace\app\](switch to project view)
 *
 * 3) Add "classpath 'com.google.gms:google-services:3.2.0'" to gradle MindPalace
 *
 * 4) Add
 "maven {
 url "https://maven.google.com" // Google's Maven repository
 }"
 * in [gradleMindPalace\allprojects\repositories\]
 *
 * 5) Add "apply plugin: 'com.google.gms.google-services'" in [gradleApp\] at the bottom
 *
 * 6) Add "'com.google.firebase:firebase-firestore:12.0.1'" in [gradleApp\dependencies\]
 *
 * */

public class Singleton {

    private static Singleton singleton;

    // TODO https://firebase.google.com/docs/firestore/security/rules-structure?authuser=0
    private FirebaseFirestore db;
    private FirebaseFirestoreSettings settings;

    private Singleton(){
        setUpDb();
    }

    public synchronized static Singleton getInstance(){
        if(singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    private void setUpDb(){
        db = FirebaseFirestore.getInstance();

        settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
    }

    public void addData() {
        // come si aggiungono due utenti?
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Lollo");
        user.put("last", "Riva");
        user.put("born", 1993);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void addData2() {
        CollectionReference users = db.collection("users");

        Map<String, Object> user1 = new HashMap<>();
        user1.put("first", "Francesco");
        user1.put("last", "Prete");
        user1.put("born", "1995");
        users.document("user1").set(user1);

        Map<String, Object> user2 = new HashMap<>();
        user2.put("first", "Mirko");
        user2.put("last", "Rima");
        user2.put("born", "1995");
        users.document("user2").set(user2);
    }

    // https://firebase.google.com/docs/firestore/query-data/get-data?authuser=0
    public void getData(final Callback callback) {
        db.collection("users")
                //.whereEqualTo("first", "Lollo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                callback.onSuccess(document.getData());
                                // TODO capire se document.getData() si può castare all'oggetto che ti serve
                            }
                        } else {
                            // Cosa cambia da fare le cose qui o qui
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
