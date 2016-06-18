package feliperodalves.com.persistenciadados.shared;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import feliperodalves.com.persistenciadados.R;

public class AdicionaNome extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_nome);

        sp = getSharedPreferences("app_preferencias", MODE_PRIVATE);

        final EditText et_nome = (EditText) findViewById(R.id.et_nome);
        Button bt_salvar = (Button) findViewById(R.id.bt_salvar);

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = et_nome.getEditableText().toString();
                SharedPreferences.Editor editor = sp.edit();
                if(usuario.matches("")){
                    editor.remove("usuario");
                }else{
                    editor.putString("usuario", usuario);
                }
                editor.apply();
                finish();
            }
        });
    }
}
