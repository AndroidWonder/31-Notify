package com.course.example.notify;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final int SIMPLE_NOTFICATION_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = "Nice Try";
        String body = "foobar";

        //set up buttons for notifications and cancel
        Button start = (Button) findViewById(R.id.btn_showsample);
        Button cancel = (Button) findViewById(R.id.btn_clear);

        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        androidChannel.setVibrationPattern(new long[] { 100, 100 });
        // Sets whether notification posted to this channel should vibrate.
        //Needs permission in Manifest
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mManager.createNotificationChannel(androidChannel);

        //create Intent and PendingIntent
        Intent intent = new Intent(this, Widgets.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,0);

        //create notification
        final Notification.Builder nb = new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.droid)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        //mManager.notify(SIMPLE_NOTFICATION_ID, nb.build());

        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //notify() in response to button click.
                mManager.notify(SIMPLE_NOTFICATION_ID, nb.build());

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mManager.cancel(SIMPLE_NOTFICATION_ID);
            }
        });

    }

}