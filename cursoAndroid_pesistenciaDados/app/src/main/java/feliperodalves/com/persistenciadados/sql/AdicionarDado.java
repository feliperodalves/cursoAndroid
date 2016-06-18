package feliperodalves.com.persistenciadados.sql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import feliperodalves.com.persistenciadados.R;

public class AdicionarDado extends AppCompatActivity {

    private AcessoDados acesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_dado);

        acesso = new AcessoDados(this);
        acesso.open();

        Button bt_salvar = (Button) findViewById(R.id.bt_salvar);
        final EditText et_dado = (EditText) findViewById(R.id.et_dado);

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dado = et_dado.getEditableText().toString();
                acesso.create(dado);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        acesso.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        acesso.close();
    }
}
