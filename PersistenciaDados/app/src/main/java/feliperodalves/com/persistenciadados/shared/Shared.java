package feliperodalves.com.persistenciadados.shared;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import feliperodalves.com.persistenciadados.R;

public class Shared extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences("app_preferencias",MODE_PRIVATE);
        String usuario = sp.getString("usuario",null);

        TextView tv_principal = (TextView) findViewById(R.id.tv_principal);
        Button bt_acao = (Button) findViewById(R.id.bt_acao);

        if(usuario != null){
            tv_principal.setText("Bem vindo, " + usuario.toString() + "!");
            bt_acao.setText("Trocar de nome");
        }else{
            tv_principal.setText("Informe seu nome...");
            bt_acao.setText("Adicionar nome");
        }

        bt_acao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Shared.this, AdicionaNome.class);
                startActivity(i);
            }
        });
    }
}
