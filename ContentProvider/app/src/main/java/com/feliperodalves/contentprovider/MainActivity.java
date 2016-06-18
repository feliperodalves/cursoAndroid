package com.feliperodalves.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                showMessageOKCancel("Precisamos de Permissão",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {Manifest.permission.READ_CONTACTS},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        showDados();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    showDados();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "READ_CONTACTS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showDados() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Contacts.CONTENT_URI;
        //Uri uri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, "A");

        String[] projection = new String[] {Contacts._ID, Contacts.DISPLAY_NAME};
        /*String[] projection = new String[] {Contacts.DISPLAY_NAME,
                                            Phone.NUMBER};*/
        String selection = null;
        /*String selection = Phone.CONTACT_ID + " = ?";*/
        String[] selectionArgs = null;
        /*String[] selectionArgs = new String[]{"10"};*/
        String sortOrder = null; // Contacts.DISPLAY_NAME + " ASC"

        Cursor cursor = contentResolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while (cursor.moveToNext()) {
            String saida = cursor.getString(cursor.getColumnIndex(Contacts._ID)) + " - "
                    + cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
            TextView tv = new TextView(this);
            tv.setText(saida);
            tv.setTextAppearance(android.R.style.TextAppearance_Large);
            linearLayout.addView(tv);
        }
        cursor.close();
    }
}
