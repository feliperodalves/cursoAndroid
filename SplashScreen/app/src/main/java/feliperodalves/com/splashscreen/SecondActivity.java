package feliperodalves.com.splashscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("email");
        String senha = extras.getString("senha");

        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        tv_email.setText(email);
        TextView tv_senha = (TextView) findViewById(R.id.tv_senha);
        tv_senha.setText(senha);
    }

    public void enviarEmail(View view){
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:" + tv_email.getText().toString()));
        intent.putExtra(Intent.EXTRA_SUBJECT, "ASSUNTO");
        intent.putExtra(Intent.EXTRA_TEXT, "Olá, este é um email automatico.");
        startActivity(Intent.createChooser(intent,"Enviar email"));
    }

    public void tirarFoto(View view){


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File arquivoFoto = null;
        try {
            arquivoFoto = criarArquivoImagem();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        if(arquivoFoto != null){
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
            Log.i("IMAGEM",arquivoFoto.toString());
            startActivity(intent);
        }
    }

    private File criarArquivoImagem() throws IOException {
        String nomeFoto = "TESTE-" + new SimpleDateFormat("yyyyMMdd").format(new Date());
        File pastaArmazenamento = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem = File.createTempFile(nomeFoto, ".jpg", pastaArmazenamento);

        return imagem;
    }
}
