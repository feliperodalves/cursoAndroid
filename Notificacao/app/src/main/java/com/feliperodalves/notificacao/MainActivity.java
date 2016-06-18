package com.feliperodalves.notificacao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_notification = (Button) findViewById(R.id.bt_notfication);
        bt_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarNotificacao();
            }
        });
    }

    public void criarNotificacao(){
        NotificationCompat.Builder nb = new NotificationCompat.Builder(MainActivity.this);

        nb.setContentTitle("Titulo da Notificação");
        nb.setContentText("Texto da minha notificação");
        nb.setSmallIcon(R.drawable.ic_stat_name);

        Intent i = new Intent(MainActivity.this, NotificationActivity.class);

        TaskStackBuilder tsb = TaskStackBuilder.create(this);
        tsb.addParentStack(NotificationActivity.class);
        tsb.addNextIntent(i);

        PendingIntent pi = tsb.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);

        nb.setContentIntent(pi);
        Notification notif = nb.build();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        nm.notify(1, notif);
    }
}
