package feliperodalves.com.persistenciadados.external;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import feliperodalves.com.persistenciadados.R;

public class ExtStorage extends AppCompatActivity {

    private TextView tv_nome;
    private EditText et_nome;
    private Button bt_salvar;
    private Button bt_apagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext_storage);

        // não esquecer de adicionar no android manifest:
        //<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_nome = (TextView) findViewById(R.id.tv_es_principal);
        et_nome = (EditText) findViewById(R.id.et_es_nome);
        bt_salvar = (Button) findViewById(R.id.bt_es_salvar);
        bt_apagar = (Button) findViewById(R.id.bt_es_apagar);

        acessarArquivo();

        //ao clicar no botão, salvar o dado no arquivo
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileWriter fileWriter = null;
                try {
                    //cria um arquivo
                    File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)),"/arquivo_1.txt");
                    fileWriter = new FileWriter(file, true);
                    //adiciona dados ao objeto
                    fileWriter.append(et_nome.getText());
                    fileWriter.append("\n");// Quebra de linha.
                    // Escreve no arquivo.
                    fileWriter.flush();
                    acessarArquivo();
                } catch (IOException e) {
                    Log.e("Erros", "Erro ao salvar usando External Storage", e);
                } finally {
                    if (fileWriter != null) {
                        try {fileWriter.close();}
                        catch(Exception e){}
                    }
                }
            }
        });

        //ao clicar no botão, apagar o arquivo
        bt_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)),"/arquivo_1.txt");
                file.delete();
                acessarArquivo();
            }
        });
    }

    private void acessarArquivo(){
        //verificar se existe arquivo salvo
        String linha, conteudo = "";
        BufferedReader br = null;

        try{
            // Acessa o arquivo.
            File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)),"/arquivo_1.txt");
            if(file.exists()){
                br = new BufferedReader(new FileReader(file));
                // Faz a leitura, uma linha por vez, até o fim do arquivo,
                // gerando um string com todo o conteúdo separado por linha.
                while ((linha = br.readLine()) != null) {
                    conteudo += linha;
                    conteudo += "\n";// Quebra de linha.
                }
                tv_nome.setText(conteudo);
            }else{
                tv_nome.setText("Arquivo ainda não existe");
            }
        }catch (Exception e){
            Log.e("Erros", "Erro ao ler arquivo do Internal Storage", e);
        }finally {
            if(br != null){
                try{br.close();}
                catch(Exception e){}
            }
        }
    }
}
