package com.example.myNotificationApp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private final String CHANEL_ID = "my_ch_id";
    private final int UNIQUE_ID = 3276;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setNotification(View view) {

        createNotification();

        Intent intent = new Intent(this, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANEL_ID);
        notification.setAutoCancel(true);

        //Build notifications
        notification.setSmallIcon(R.drawable.bird1);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("I am the title of the notification");
        notification.setContentText("I am the body of the notification");
        notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notification.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(UNIQUE_ID, notification.build());
    }

    public void createNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Chhayank's Personal Notifications";
            String description = "Include all the personal notifications";

            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}

