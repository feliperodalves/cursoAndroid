package occamengenharia.com.br.helloworldoccam2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //criar objetos para receber os elementos da activity
    private EditText et_nome;
    private TextView tv_saudacao;
    private String saudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //atribuir os elementos aos objetos criados
        this.et_nome = (EditText) findViewById(R.id.et_nome);
        this.tv_saudacao = (TextView) findViewById(R.id.tv_saudacao);
        //como pegar um recurso do arquivo strings.xml
        this.saudacao = getResources().getString(R.string.saudacao);
    }

    public void alterarTexto(View v){
        this.tv_saudacao.setText(this.saudacao + " " + this.et_nome.getText().toString());
    }

    public void novaTela(View v){
        Intent intent = new Intent(Saudacao.ACTION_EXIBIR_SAUDACAO);
        intent.addCategory(Saudacao.CATEGORY_SAUDACAO);
        String texto = this.et_nome.getText().toString();
        intent.putExtra(Saudacao.EXTRA_NOME_USUARIO, texto);
        startActivity(intent);
    }
}
