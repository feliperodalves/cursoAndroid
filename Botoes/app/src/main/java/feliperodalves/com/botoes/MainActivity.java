package feliperodalves.com.botoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao2 = (Button) findViewById(R.id.bt_2);
        botao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast2();
            }
        });

        Button botao3 = (Button) findViewById(R.id.bt_3);
        botao3.setOnClickListener(this);
    }

    public void showToast1(View view){
        Toast.makeText(MainActivity.this, "Botão 1 apertado", Toast.LENGTH_SHORT).show();
    }

    private void showToast2(){
        Toast.makeText(MainActivity.this, "Botão 2 apertado", Toast.LENGTH_SHORT).show();
    }

    private void showToast3(){
        Toast.makeText(MainActivity.this, "Botão 3 apertado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_3){
            showToast3();
        }
    }
}
