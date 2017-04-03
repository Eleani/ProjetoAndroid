package com.example.nany_.projetoviagem;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nany_.projetoviagem.bd.GastoBD;
import com.example.nany_.projetoviagem.bd.ViagemBD;

import java.util.ArrayList;

public class ListarGastoActivity extends AppCompatActivity {

    private GastoBD gastoBD;
    private GridView gridView;
    public static ArrayList<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_gasto);

        gastoBD = new GastoBD(this, "ProjetoViagem", 1);
        listarGasto();

    }

    private void listarGasto(){

        lista.clear();

        lista.add("Tipo Gasto");
        lista.add("Descrição");
        lista.add("Local");
        lista.add("Viagem");

        try{
            Cursor cr = gastoBD.getWritableDatabase().rawQuery("SELECT * FROM gasto", null);
            if (cr != null){
                if (cr.moveToFirst()){
                    do {
                        String tipoGasto = cr.getString(cr.getColumnIndex("tipoGasto"));
                        String descricao = cr.getString(cr.getColumnIndex("descricao"));
                        String local = cr.getString(cr.getColumnIndex("local"));
                        String idViagem = cr.getString(cr.getColumnIndex("id_viagem"));

                        lista.add(tipoGasto);
                        lista.add(descricao);
                        lista.add(local);
                        lista.add(idViagem);

                    } while (cr.moveToNext());
                } else {
                    Toast.makeText(getApplicationContext(), "No Data to show", Toast.LENGTH_LONG).show();
                }
            }
            Log.d(null, "listagem 1 ");
            cr.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        gridView = (GridView) findViewById(R.id.gastoGridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        gridView.setAdapter(adapter);
    }
}
