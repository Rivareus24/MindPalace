package com.okeechobee.rivareus24.mindpalace.L___Broadcast_Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.okeechobee.rivareus24.mindpalace.R;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private ReceiverExample receiverExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        intentFilter = new IntentFilter();

        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);

        receiverExample = new ReceiverExample();
    }

    public class ReceiverExample extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case Intent.ACTION_POWER_CONNECTED:
                    Toast.makeText(
                            BroadcastReceiverActivity.this,
                            Intent.ACTION_POWER_CONNECTED,
                            Toast.LENGTH_LONG)
                            .show();
                    break;
                default:
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check se lo stato è constistente (stickyIntent)
        registerReceiver(receiverExample, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverExample);
    }
}
