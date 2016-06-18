package feliperodalves.com.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void validarAcesso(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        EditText et_email = (EditText) findViewById(R.id.et_email);
        EditText et_senha = (EditText) findViewById(R.id.et_senha);
        intent.putExtra("email", et_email.getText().toString());
        intent.putExtra("senha", et_senha.getText().toString());
        startActivity(intent);
    }
}
