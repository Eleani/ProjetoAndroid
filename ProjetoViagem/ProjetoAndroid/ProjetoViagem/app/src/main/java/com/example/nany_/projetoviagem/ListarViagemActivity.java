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

import com.example.nany_.projetoviagem.bd.ViagemBD;
import com.example.nany_.projetoviagem.domain.Viagem;

import java.util.ArrayList;

public class ListarViagemActivity extends AppCompatActivity {

    private ViagemBD viagemBD;
    private GridView gridView;
    public static ArrayList<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_viagem);

        Log.d(null, "onCreate ");

        viagemBD = new ViagemBD(this, "ProjetoViagem", 1);
        listarViagem();

        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int linha, long idViagem) {
                // Já possui 4 colunas, porem tive que comentar pois pego o id
                // da coluna do grid, pra pegar de 4, tem que fazer um calculo ou ver
                // outra forma de obter o id
                idViagem = linha+1;
                Log.d(null, "onItemClick "+ idViagem);
                // Teria que ver uma forma de chamar a Activity do ListarGasto,
                // por hora, fiz pra carregar todos os gastos e lá ter a Viagem TODO mudar
            }
        });

    }

    private void listarViagem(){

        lista.clear();

        lista.add("Viagem");
        try{
            Cursor cr = viagemBD.getWritableDatabase().rawQuery("SELECT * FROM viagem", null);
            if (cr != null){
                if (cr.moveToFirst()){
                    do {
                        String destino = cr.getString(cr.getColumnIndex("destino"));
                        //int tipoViagem = cr.getInt(cr.getColumnIndex("tipoViagem"));
                        //String dataChegada = cr.getString(cr.getColumnIndex("dataChegada"));
                        //String dataPartida = cr.getString(cr.getColumnIndex("dataPartida"));

                        lista.add(destino);

                        /*
                        if (tipoViagem == 0){
                            lista.add("Lazer");
                        } else if (tipoViagem == 1) {
                            lista.add("Negócio");
                        } else {
                            lista.add(" ");
                        }

                        if (dataChegada != null){
                            lista.add(dataChegada);
                        } else {
                            lista.add(" ");
                        }

                        if (dataPartida != null){
                            lista.add(dataPartida);
                        } else {
                            lista.add(" ");
                        }
                        */
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

        gridView = (GridView) findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        gridView.setAdapter(adapter);

    }



}
