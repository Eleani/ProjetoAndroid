package com.example.nany_.projetoviagem;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nany_.projetoviagem.bd.GastoBD;
import com.example.nany_.projetoviagem.bd.ViagemBD;
import com.example.nany_.projetoviagem.domain.Gasto;
import com.example.nany_.projetoviagem.domain.TipoGasto;
import com.example.nany_.projetoviagem.domain.TipoViagem;
import com.example.nany_.projetoviagem.domain.Viagem;

import java.util.ArrayList;
import java.util.List;

public class GastoActivity extends AppCompatActivity {

    private GastoBD gastoBD;
    private Long idViagem;
    private Double valorOrcamento;
    private Spinner tipoGasto;
    private List<String> tipoGastos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto);

        // Obtem o id da viagem
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idViagem = bundle.getLong("idViagem");
        valorOrcamento = bundle.getDouble("valorViagem");

        // Carrega os dados do Spinner
        tipoGastos.add(TipoGasto.Alimentacao.toString());
        tipoGastos.add(TipoGasto.Brinde.toString());
        tipoGastos.add(TipoGasto.Deslocamento.toString());

        //Identifica o Spinner no layout
        tipoGasto = (Spinner) findViewById(R.id.tipoSpinner);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList tipoGastos
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipoGastos);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tipoGasto.setAdapter(spinnerArrayAdapter);
    }

    public void gravarGasto(View view){

        // Cria a viagem
        Gasto gasto = new Gasto();

        tipoGasto = (Spinner) findViewById(R.id.tipoSpinner);
        int tpGasto = tipoGasto.getSelectedItemPosition();

        if (tpGasto == 0){
            gasto.setTipoGasto(TipoGasto.Alimentacao);
        } else if (tpGasto == 1) {
            gasto.setTipoGasto(TipoGasto.Brinde);
        } else {
            gasto.setTipoGasto(TipoGasto.Deslocamento);
        }

        EditText valor = (EditText)findViewById(R.id.valorEditText);
        EditText data = (EditText)findViewById(R.id.dataEditText);
        EditText descricao = (EditText)findViewById(R.id.descricaoEditText5);
        EditText local = (EditText)findViewById(R.id.localEditText6);

        gasto.setValor(Double.parseDouble(valor.getText().toString()));
        gasto.setData(data.getText().toString());
        gasto.setDescricao(descricao.getText().toString());
        gasto.setLocal(local.getText().toString());

        boolean teste = validarPercentualGasto(valorOrcamento, gasto.getValor());

        if (validarPercentualGasto(valorOrcamento, gasto.getValor()) == false){
            Toast.makeText(getApplicationContext(), "Percental maior que o permitido!", Toast.LENGTH_LONG).show();
        }

        // Grava Viagem no Banco
        inserirGastoBd(gasto);
        finish();
    }

    public boolean validarPercentualGasto(Double valorViagem, Double valorGasto){

        // Implementar para buscar todos os gastos já cadastrados da viagem

        Double percentualGasto = 0.0;

        percentualGasto = (valorGasto * 100) / valorViagem;

        Toast.makeText(getApplicationContext(), "Percental: "+ percentualGasto, Toast.LENGTH_LONG).show();

        if (percentualGasto > 75.0){
            return false;
        }

        return true;
    }

    public void inserirGastoBd(Gasto gasto){

        gastoBD = new GastoBD(this, "ProjetoViagem", 1);  // Mudei o nome do banco
        ContentValues contentValues = new ContentValues();
        contentValues.put("tipoGasto", gasto.getTipoGasto().toString());
        contentValues.put("valor", gasto.getValor());
        contentValues.put("data", gasto.getData());
        contentValues.put("descricao", gasto.getDescricao());
        contentValues.put("local", gasto.getLocal());
        contentValues.put("id_viagem", idViagem);

        gastoBD.getWritableDatabase().insert("gasto", null, contentValues);

        Toast.makeText(getApplicationContext(), "Gasto Salvo com Sucesso!", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "id "+idViagem, Toast.LENGTH_LONG).show();

    }

}
