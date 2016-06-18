package feliperodalves.com.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PessoasDAO {

    private SQLiteDatabase db;
    private DatabaseOpenHelper dboh;
    private String[] colunas = {"_id","nome","sobrenome"};

    public PessoasDAO(Context c) {
        dboh = new DatabaseOpenHelper(c);
    }

    public void open(){
        db  = dboh.getWritableDatabase();
    }

    public void close(){
        dboh.close();
    }

    public Pessoas create(Pessoas pessoa){

        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("sobrenome", pessoa.getSobrenome());
        long id = db.insert("users", null, valores);
        Cursor cursor = db.query("users", colunas, "_id", null, null, null, null);
        cursor.moveToFirst();
        Pessoas novoPessoa = new Pessoas();
        novoPessoa.setId(cursor.getLong(0));
        novoPessoa.setNome(cursor.getString(1));
        novoPessoa.setSobrenome(cursor.getString(2));
        cursor.close();

        return novoPessoa;
    }

    public void delete(Pessoas pessoa){
        long id = pessoa.getId();
        db.delete("users", "_id = " + id, null);
    }

    public ArrayList<Pessoas> selectAll(){
        ArrayList<Pessoas> infos = new ArrayList<Pessoas>();

        Cursor cursor = db.query("users", colunas, null, null, null, null, null);
        cursor.moveToFirst();
        while( !cursor.isAfterLast() ){
            Pessoas pessoa = new Pessoas();
            pessoa.setId(cursor.getLong(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setSobrenome(cursor.getString(2));
            infos.add(pessoa);
            cursor.moveToNext();
        }
        cursor.close();

        return infos;
    }
}
