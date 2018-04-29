package com.okeechobee.rivareus24.mindpalace.H___Service_Start;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class ServiceStart extends IntentService {

    public static final String ACTION1 = "action1";
    public static final String ACTION2 = "action2"

    public ServiceStart() {
        super("ServiceStart");
    }


    // quello che viene chiamato qui dentro viene automaticamente runnato su un thread diverso da
    // quello principale, e tutte le richiesto sono eseguite su questo nuovo thread,
    // una dopo l'altra in successione.
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action_name = intent.getAction();

        mExecuteTask(action_name, this);
    }

    public static void mExecuteTask(String action_name, Context context){
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
