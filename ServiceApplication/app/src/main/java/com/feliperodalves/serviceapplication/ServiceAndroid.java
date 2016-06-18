package com.feliperodalves.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceAndroid extends Service {

    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ServiceAndroid.this, "Timer !!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new Timer();
        timer.schedule(timerTask,2000,5000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
