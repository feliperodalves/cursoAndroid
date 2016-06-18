package feliperodalves.com.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

public class PessoasProvider extends ContentProvider{

    /*
        content://feliperodalves.com.sqlite.provider/pessoa - insercao ou pesquisa
        content://feliperodalves.com.sqlite.provider/pessoa/# - alteração
        # ou *
    */

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        uriMatcher.addURI("feliperodalves.com.sqlite.provider","pessoa", 1);
        uriMatcher.addURI("feliperodalves.com.sqlite.provider","pessoa" + "/#", 2);
    }

    private DatabaseOpenHelper dboh;

    @Override
    public boolean onCreate() {
        dboh = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dboh.getReadableDatabase();
        switch(uriMatcher.match(uri)){
            case 1:
                return db.query("users", projection, selection, selectionArgs, null, null, sortOrder);
            case 2:
                selection = "_id = ?";
                selectionArgs = new String[] {uri.getLastPathSegment()};
                return db.query("users", projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Uri desconhecida");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dboh.getWritableDatabase();
        long id;

        switch(uriMatcher.match(uri)){
            case 1:
                id = db.insert("users",null, values);
                return Uri.withAppendedPath(Uri.parse("content://feliperodalves.com.sqlite.provider/pessoa"),String.valueOf(id));
            default:
                throw new IllegalArgumentException("Uri desconhecida");
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dboh.getWritableDatabase();
        switch(uriMatcher.match(uri)){
            case 2:
                selection = "_id = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                return db.delete("users",selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Uri desconhecida");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dboh.getWritableDatabase();
        switch(uriMatcher.match(uri)){
            case 2:
                selection = "_id = ?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                return db.update("users",values,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Uri desconhecida");
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case 1:
                return "vnd.android.cursor.dir/vnd.feliperodalves.com.sqlite.provider/pessoa";
            case 2:
                return "vnd.android.cursor.item/vnd.feliperodalves.com.sqlite.provider/pessoa";
            default:
                throw new IllegalArgumentException("Uri desconhecida");
        }
    }
}
