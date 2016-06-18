package feliperodalves.com.persistenciadados.sql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import feliperodalves.com.persistenciadados.R;

public class DadosViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    public TextView tv_dado;

    public DadosViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;

        tv_dado = (TextView) itemView.findViewById(R.id.tv_dado);
    }
}
