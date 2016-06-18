package occamengenharia.com.br.helloworldoccam2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Saudacao extends AppCompatActivity {

    public static final String EXTRA_NOME_USUARIO = "EXTRA_NOME_USUARIO";
    public static final String ACTION_EXIBIR_SAUDACAO = "ACTION_EXIBIR_SAUDACAO";
    public static final String CATEGORY_SAUDACAO = "CATEGORY_SAUDACAO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saudacao);

        TextView tv_saudacao = (TextView) findViewById(R.id.tv_saudacao2);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_NOME_USUARIO)){
            String saudacao = getResources().getString(R.string.saudacao);
            tv_saudacao.setText(saudacao + " " + intent.getStringExtra(EXTRA_NOME_USUARIO));
        }else{
            tv_saudacao.setText("Não foi informado nome do usuário.");
        }
    }
}
