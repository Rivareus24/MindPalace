package com.okeechobee.rivareus24.mindpalace.K___Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.okeechobee.rivareus24.mindpalace.C___RecyclerView_Toast_Swipe.RecyclerViewActivity;
import com.okeechobee.rivareus24.mindpalace.R;

public class NotificationsActivity extends AppCompatActivity {

    private Button btnNotifyMe;
    private static final String CHANNEL_X_ID = "1001";
    private static final String CHANNEL_NAME = "Channel name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        btnNotifyMe = (Button) findViewById(R.id.btnNotifyMe);
        btnNotifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNotification(NotificationsActivity.this);
            }
        });
    }

    private void makeNotification(Context context){
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_X_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_X_ID)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setContentTitle("Notification Title")
                        .setContentText("Notification Text")
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(openAppIntent(context));
        /*
                        .setLargeIcon(largeIcon(context))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                context.getString(R.string.charging_reminder_notification_body)))
        */

        // TODO tipi di notifica, pop up, badge, etc..

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        manager.notify(3, builder.build());
    }

    // se vuoi riaprire un'activity che è in background devi dichiararla singleTop (Launch Mode Manifest)

    private PendingIntent openAppIntent(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);

        return PendingIntent.getActivity(
                context,
                6,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

}
