package com.feliperodalves.serviceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_iniciar = (Button) findViewById(R.id.bt_iniciar);
        Button bt_iniciar2 = (Button) findViewById(R.id.bt_iniciar2);
        Button bt_musicPlayer = (Button) findViewById(R.id.bt_musicPlayer);
        Button bt_finalizar = (Button) findViewById(R.id.bt_finalizar);

        bt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), ServiceAndroid.class));
            }
        });

        bt_iniciar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), Service2Android.class));
            }
        });

        bt_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), ServiceAndroid.class));
            }
        });
    }
}
