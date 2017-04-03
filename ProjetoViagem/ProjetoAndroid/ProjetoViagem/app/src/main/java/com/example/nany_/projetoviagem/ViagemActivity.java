package com.example.nany_.projetoviagem;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nany_.projetoviagem.bd.ViagemBD;
import com.example.nany_.projetoviagem.domain.Viagem;

import java.io.File;
import java.util.Date;

public class ViagemActivity extends AppCompatActivity {

    private ViagemBD viagemBd;
    private static final int CAPTURAR_IMAGEM = 1;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);

    }

    public void gravarFoto(View view){
        Intent fotoActivity = new Intent(this, FotoActivity.class);
        startActivity(fotoActivity);
    }

    public void gravarViagem(View view){
        Intent gastoActivity = new Intent(this, GastoActivity.class);

        // Cria a viagem
        Viagem viagem = new Viagem();

        EditText destino = (EditText)findViewById(R.id.destinoEditText);
        RadioGroup tipoViagem = (RadioGroup)findViewById(R.id.radioGroup);
        EditText dataPartida = (EditText)findViewById(R.id.dtChegadaEditText);
        EditText dataRetorno = (EditText)findViewById(R.id.dtRetornoEditText);
        EditText valorOrcamento = (EditText)findViewById(R.id.valorOrcamentoEditText);
        EditText qtdePessoa = (EditText)findViewById(R.id.qtdePessoaEditText);

        viagem.setDestino(destino.getText().toString());
        viagem.setDataPartida(dataPartida.getText().toString());
        viagem.setDataChegada(dataRetorno.getText().toString());

        switch (tipoViagem.getCheckedRadioButtonId()) {
            case R.id.lazerRadioButton:
                viagem.setTipoViagem(0);
                break;
            case R.id.negocioRadioButton:
                viagem.setTipoViagem(1);
                break;
        }

        viagem.setValorOrcamento(Double.parseDouble(valorOrcamento.getText().toString()));
        viagem.setQtdePessoa(Integer.parseInt(qtdePessoa.getText().toString()));

        // Grava Viagem no Banco
        inserirViagemBd(viagem);

        viagem.setId(listarUltimaViagem());

        Bundle bundle = new Bundle();
        bundle.putLong("idViagem", viagem.getId());
        bundle.putDouble("valorViagem", viagem.getValorOrcamento());
        gastoActivity.putExtras(bundle);

        // Chama a tela de Gasto
        startActivity(gastoActivity);
    }

    public void inserirViagemBd(Viagem viagem){

        viagemBd = new ViagemBD(this, "ProjetoViagem", 1);  // Mudei o nome do banco
        ContentValues contentValues = new ContentValues();
        contentValues.put("destino", viagem.getDestino());
        contentValues.put("tipoViagem", viagem.getTipoViagem());
        contentValues.put("dataChegada", viagem.getDataChegada());
        contentValues.put("dataPartida", viagem.getDataPartida());
        contentValues.put("valorOrcamento", viagem.getValorOrcamento());
        contentValues.put("qtdePessoa", viagem.getQtdePessoa());
        viagemBd.getWritableDatabase().insert("viagem", null, contentValues);

    }

    private void adicionarNaViagem(){


    }

    private Long listarUltimaViagem(){

        Long idViagem = 1L;

        try{
            Cursor cr = viagemBd.getWritableDatabase().rawQuery("SELECT * FROM viagem", null);
            if (cr != null){
                if (cr.moveToFirst()){
                    do {
                        idViagem = cr.getLong(cr.getColumnIndex("id_viagem"));
                    } while (cr.moveToNext());
                } else {
                    Toast.makeText(getApplicationContext(), "No Data to show", Toast.LENGTH_LONG).show();
                }
            }
            cr.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        return idViagem;

    }

}
