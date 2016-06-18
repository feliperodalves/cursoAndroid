package feliperodalves.com.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PessoasDAO acesso;
    private RecyclerView rv;
    private ArrayList<Pessoas> pessoas;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acesso = new PessoasDAO(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        acesso.open();
        incluirAdaptador();
    }

    private void incluirAdaptador(){
        pessoas = new ArrayList<Pessoas>();
        pessoas = acesso.selectAll();

        adaptador = new Adaptador(this, pessoas);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setAdapter(adaptador);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        acesso.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_adicionar){
            Intent i = new Intent(this, AdicionarNome.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
