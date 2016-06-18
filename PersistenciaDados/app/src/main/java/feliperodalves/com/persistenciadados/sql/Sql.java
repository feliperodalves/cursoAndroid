package feliperodalves.com.persistenciadados.sql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import feliperodalves.com.persistenciadados.R;

public class Sql extends AppCompatActivity {

    private AcessoDados acesso;
    private RecyclerView rv;
    private ArrayList<Dados> dados;
    private DadosAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql);

        acesso = new AcessoDados(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        acesso.open();

        incluirAdaptador();
    }

    private void incluirAdaptador() {

        dados = new ArrayList<Dados>();
        dados = acesso.getAll();

        adaptador = new DadosAdaptador(this, dados);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
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
        getMenuInflater().inflate(R.menu.sqlite_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.adicionar){
            Intent i = new Intent(this, AdicionarDado.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
