package com.rdc.cekkoneksi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    LinearLayout linearError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearError = (LinearLayout)findViewById(R.id.error);


        cekKoneksi();
    }
    private void cekKoneksi() {
        boolean jikaKonek = ConnectivityReceiver.isConnected();
        munculSnackBar(jikaKonek);
    }

    private void munculSnackBar(boolean jikaKonek) {
        String message;
        int color;
        if (jikaKonek){
            linearError.setVisibility(View.GONE);
            message = "Sip! Tersambung ke Internet";
            color = Color.WHITE;
        } else {
            linearError.setVisibility(View.VISIBLE);
            message = "Maaf, Coba Cek Koneksi Internetnya";
            color = Color.YELLOW;
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView)sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        munculSnackBar(isConnected);
    }
}
