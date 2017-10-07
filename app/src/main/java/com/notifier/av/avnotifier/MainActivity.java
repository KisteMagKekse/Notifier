package com.notifier.av.avnotifier;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random r = new Random();
        String name = "mainbg" + (r.nextInt(3) + 1);
        int resource = getResources().getIdentifier(name, "drawable", getPackageName());
        LinearLayout llayout = (LinearLayout)findViewById(R.id.top_buttons);
        llayout.setBackgroundResource(resource);
    }

    public void searchJAV(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void changeSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.GeneralPreferenceFragment.class.getName());
        intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
        String CHANNEL_ID = "testchannel01";
        Intent resultIntent = new Intent(this, SearchActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("deine mama")
                        .setContentText("die ist voll die geile")
                        .setChannel(CHANNEL_ID)
                        .setContentIntent(resultPendingIntent);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
        //startActivity(intent);
    }
}
