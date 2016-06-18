package feliperodalves.com.persistenciadados.sql;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class DadosProvider extends ContentProvider{

    /*
        content://'pacote'.provider/'classeDado' - insercao ou consulta
        content://'pacote'.provider/'classeDado'/# - alteracao ou delete
     */

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI("feliperodalves.com.persistenciadados.sql.provider","dados",1); // insert consulta
        uriMatcher.addURI("feliperodalves.com.persistenciadados.sql.provider","dados/#",2); // update delete - consulta de 1 unico valor
    }

    private DatabaseHelper dbhelper;

    @Override
    public boolean onCreate() {
        dbhelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case 1:
                return db.query("tabela", projection, selection, selectionArgs, null, null, sortOrder);
            case 2:
                selection = "_id = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                return db.query("tabela", projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("URI desconhecida");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long id;

        switch (uriMatcher.match(uri)) {
            case 1:
                id = db.insert("tabela", null, values);
                return Uri.withAppendedPath(Uri.parse("content://feliperodalves.com.persistenciadados.sql.provider/dados"), String.valueOf(id));
            default:
                throw new IllegalArgumentException("URI desconhecida");
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case 2:
                selection = "_id = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                return db.delete("tabela", selection, selectionArgs);
            default:
                throw new IllegalArgumentException("URI desconhecida");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case 2:
                selection = "_id = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                return db.update("tabela", values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("URI desconhecida");
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case 1:
                return "vnr.android.cursor.dir/vnr.feliperodalves.com.persistenciadados.sql.provider/dados";
            case 2:
                return "vnr.android.cursor.item/vnr.feliperodalves.com.persistenciadados.sql.provider/dados";
            default:
                throw new IllegalArgumentException("URI desconhecida");
        }
    }
}
