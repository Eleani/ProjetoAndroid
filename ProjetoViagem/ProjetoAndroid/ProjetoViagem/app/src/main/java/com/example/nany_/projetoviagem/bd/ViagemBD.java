package com.example.nany_.projetoviagem.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;
import android.widget.Toast;

import com.example.nany_.projetoviagem.domain.Viagem;

/**
 * Created by nany_ on 19/03/2017.
 */

public class ViagemBD extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "viagem";
    private static final String ID = "id_viagem";
    private static final String DESTINO = "destino";
    private static final String TIPOVIAGEM = "tipoViagem";
    private static final String DATACHEGADA = "dataChegada";
    private static final String DATAPARTIDA = "dataPartida";
    private static final String VALORCAMENTO = "valorOrcamento";
    private static final String QTDEPESSOA = "qtdePessoa";
    private static final String FOTO = "foto";

    private GastoBD gastoDB;

    public ViagemBD(Context context, String name, int version) { super(context, name, null, version);}

    @Override
    public void onCreate(SQLiteDatabase sqld) {

        Log.d(null, "onCreate Viagem");

        sqld.execSQL("CREATE TABLE "+TABELA+"("
                + ID + " INTEGER PRIMARY KEY autoincrement,"
                + DESTINO + " varchar(45) NOT NULL,"
                + TIPOVIAGEM + " integer,"
                + DATACHEGADA + " varchar(8),"
                + DATAPARTIDA + " varchar(8),"
                + VALORCAMENTO + " double,"
                + QTDEPESSOA + " integer,"
                + FOTO + " varchar(100)"
                + ");");

        sqld.execSQL("CREATE TABLE gasto("
                + "id_gasto INTEGER PRIMARY KEY autoincrement,"
                + "tipoGasto varchar(30) NOT NULL,"
                + "valor double,"
                + "data varchar(8),"
                + "descricao varchar (45),"
                + "local varchar(30),"
                + "id_viagem integer,"
                + "FOREIGN KEY (id_viagem) REFERENCES viagem(id_viagem)"
                + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

        Log.d(null, "onUpgrade");

    }

    public void buscarUltimaViagem(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

        Log.d(null, "onUpgrade");

    }
}
