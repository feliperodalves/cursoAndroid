package feliperodalves.com.sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<PessoasViewHolder> {

    private PessoasDAO acesso;
    private Context c;
    private ArrayList<Pessoas> pessoas;

    public Adaptador(Context c, ArrayList<Pessoas> pessoas) {
        this.c = c;
        this.pessoas = pessoas;
    }

    @Override
    public PessoasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item, parent, false);
        PessoasViewHolder vh = new PessoasViewHolder(c, v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PessoasViewHolder holder, int position) {
        final Pessoas pessoa = pessoas.get(position);

        holder.tv_nomeSobrenome.setText(pessoa.getNome() + " " + pessoa.getSobrenome());
        holder.tv_nomeSobrenome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acesso = new PessoasDAO(c);
                acesso.open();
                acesso.delete(pessoa);
                pessoas.clear();
                pessoas.addAll(acesso.selectAll());
                notifyDataSetChanged();
                acesso.close();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }
}
