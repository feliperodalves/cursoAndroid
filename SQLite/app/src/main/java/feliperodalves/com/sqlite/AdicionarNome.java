package feliperodalves.com.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarNome extends AppCompatActivity {

    private PessoasDAO acesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nome);

        acesso = new PessoasDAO(this);
        acesso.open();

        final EditText et_nome = (EditText) findViewById(R.id.et_nome);
        final EditText et_sobrenome = (EditText) findViewById(R.id.et_sobrenome);
        Button bt_salvar = (Button) findViewById(R.id.bt_salvar);
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoas pessoa = new Pessoas();
                pessoa.setNome(et_nome.getText().toString());
                pessoa.setSobrenome(et_sobrenome.getText().toString());
                acesso.create(pessoa);
                finish();
            }
        });
    }

}
