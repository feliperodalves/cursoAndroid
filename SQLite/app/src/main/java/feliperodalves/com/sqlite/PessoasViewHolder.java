package feliperodalves.com.sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PessoasViewHolder extends RecyclerView.ViewHolder{

    private Context c;
    public TextView tv_nomeSobrenome;

    public PessoasViewHolder(Context context, View itemView) {
        super(itemView);

        this.c = context;
        tv_nomeSobrenome = (TextView) itemView.findViewById(R.id.tv_nomeSobrenome);
    }
}
