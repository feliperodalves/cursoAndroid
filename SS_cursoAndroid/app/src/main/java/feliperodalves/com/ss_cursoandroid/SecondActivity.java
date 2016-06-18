package feliperodalves.com.ss_cursoandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button bt_enviar2 = (Button) findViewById(R.id.bt_enviar2);
        bt_enviar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "BOTÃO 2 PRESSIONADO", Toast.LENGTH_SHORT).show();
                EditText et_email = (EditText) findViewById(R.id.et_email);
                String email = et_email.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("mailto:" + email));
                i.putExtra(Intent.EXTRA_SUBJECT, "Curso de Android");
                i.putExtra(Intent.EXTRA_TEXT, "Este é um email automatico do curso de android");
                startActivity(Intent.createChooser(i, "Enviar Email........"));;
            }
        });

        Button bt_enviar3 = (Button) findViewById(R.id.bt_enviar3);
        bt_enviar3.setOnClickListener(this);
    }

    public void enviar1(View view){
        Toast.makeText(SecondActivity.this, "BOTÃO 1 PRESSIONADO", Toast.LENGTH_LONG).show();
        EditText et_email = (EditText) findViewById(R.id.et_email);
        String email = et_email.getText().toString();
        Intent i = new Intent(this, ThirdActivity.class);
        i.putExtra("EMAIL",email);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_enviar3){
            Toast.makeText(SecondActivity.this, "BOTÃO 3 PRESSIONADO", Toast.LENGTH_SHORT).show();

            File arquivoFoto = null;
            try {
                arquivoFoto = criarArquivoFoto();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if( arquivoFoto != null){
                Toast.makeText(SecondActivity.this, "fiz", Toast.LENGTH_SHORT).show();
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivity(i);
            }

        }
    }

    private File criarArquivoFoto() throws IOException {
        File pastaArquivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem = File.createTempFile("Foto curso Android", ".jpg", pastaArquivo);
        return imagem;
    }
}
