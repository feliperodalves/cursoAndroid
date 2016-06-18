package feliperodalves.com.persistenciadados.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AcessoDados {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private String[] colunas = {"_id","dado"};

    public AcessoDados(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Dados create(String dado){

        ContentValues valor = new ContentValues();
        valor.put("dado", dado);
        long id = db.insert("tabela", null, valor);
        Cursor cursor = db.query("tabela", colunas,"_id = " + id, null,null,null,null);
        cursor.moveToFirst();
        Dados novoDado = new Dados();
        novoDado.setId(cursor.getLong(0));
        novoDado.setDado(cursor.getString(1));
        cursor.close();

        return novoDado;
    }

    public void delete(Dados dado){
        long id = dado.getId();
        db.delete("tabela", "_id = " + id, null);
    }

    public ArrayList<Dados> getAll(){
        ArrayList<Dados> dados = new ArrayList<Dados>();

        Cursor cursor = db.query("tabela", colunas,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Dados dado = new Dados();
            dado.setId(cursor.getLong(0));
            dado.setDado(cursor.getString(1));
            dados.add(dado);
            cursor.moveToNext();
        }
        cursor.close();
        return dados;
    }
}
