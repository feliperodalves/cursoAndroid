package feliperodalves.com.persistenciadados.sql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import feliperodalves.com.persistenciadados.R;

public class DadosAdaptador extends RecyclerView.Adapter<DadosViewHolder> {

    private AcessoDados acesso;
    private Context context;
    private ArrayList<Dados> dados;

    public DadosAdaptador(Context context, ArrayList<Dados> dados){
        this.context = context;
        this.dados = dados;
    }


    @Override
    public DadosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        DadosViewHolder vh = new DadosViewHolder(context, v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DadosViewHolder holder, int position) {
        final Dados dado = dados.get(position);

        holder.tv_dado.setText(dado.getDado());
        holder.tv_dado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acesso = new AcessoDados(context);
                acesso.open();
                acesso.delete(dado);
                dados.clear();
                dados.addAll(acesso.getAll());
                notifyDataSetChanged();
                acesso.close();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
