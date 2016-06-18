package feliperodalves.com.persistenciadados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import feliperodalves.com.persistenciadados.external.ExtStorage;
import feliperodalves.com.persistenciadados.internal.IntStorage;
import feliperodalves.com.persistenciadados.shared.Shared;
import feliperodalves.com.persistenciadados.sql.Sql;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_sp = (Button) findViewById(R.id.bt_sp);
        Button bt_sql = (Button) findViewById(R.id.bt_sql);
        Button bt_is = (Button) findViewById(R.id.bt_is);
        Button bt_es = (Button) findViewById(R.id.bt_es);

        bt_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Shared.class);
                startActivity(i);
            }
        });

        bt_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sql.class);
                startActivity(i);
            }
        });

        bt_is.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IntStorage.class);
                startActivity(i);
            }
        });

        bt_es.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ExtStorage.class);
                startActivity(i);
            }
        });

    }
}
