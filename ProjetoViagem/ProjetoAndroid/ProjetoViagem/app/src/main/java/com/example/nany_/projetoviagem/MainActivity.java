package com.example.nany_.projetoviagem;

import android.content.ContentValues;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nany_.projetoviagem.bd.ViagemBD;

public class MainActivity extends AppCompatActivity {

    private ViagemBD viagemBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void criarViagem(View view){
        Intent viagemActivity = new Intent(this, ViagemActivity.class);
        startActivity(viagemActivity);
    }

    public void listarViagem(View view){
        Intent listarViagemActivity = new Intent(this, ListarViagemActivity.class);
        startActivity(listarViagemActivity);
    }

    public void listarGasto(View view){
        Intent listarGastoActivity = new Intent(this, ListarGastoActivity.class);
        startActivity(listarGastoActivity);
    }

}
