package com.feliperodalves.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneReceiver extends BroadcastReceiver {

    private static final String TAG = "PhoneReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras != null){
            String estado = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.i(TAG, estado);

            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String numero = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.i(TAG, numero);
            }
        }
    }
}
