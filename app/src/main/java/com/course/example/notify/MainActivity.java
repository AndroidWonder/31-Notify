package com.course.example.notify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private NotificationManager NotificationManager;
    private NotificationCompat.Builder builder = null;
    private String textTitle = "Simple Notification Example";
    private String textContent = "Get back to Application by clicking me";
    private int SIMPLE_NOTFICATION_ID = 25;
    private String CHANNEL_ID = "11";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel();

        // Create an explicit intent for an Activity
        Intent intent = new Intent(this, Widgets.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
  
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 9, intent,0 );

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.droid)
                .setContentTitle("Android Notification")
                .setContentText("Tap to open new activity")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Button start = (Button) findViewById(R.id.btn_showsample);
        Button cancel = (Button) findViewById(R.id.btn_clear);

        //notify
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //notify() in response to button click.
                NotificationManager.notify(SIMPLE_NOTFICATION_ID, builder.build());
            }
        });

        //clear notification
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationManager.cancel(SIMPLE_NOTFICATION_ID);
            }
        });

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
