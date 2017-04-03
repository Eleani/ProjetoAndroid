package com.example.nany_.projetoviagem.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by nany_ on 25/03/2017.
 */

public class GastoBD extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "gasto";
    private static final String ID = "id_gasto";
    private static final String TIPOGASTO = "tipoGasto";
    private static final String VALOR = "valor";
    private static final String DATA = "data";
    private static final String DESCRICAO = "descricao";
    private static final String LOCAL = "local";
    private static final String ID_VIAGEM = "id_viagem";

    public GastoBD(Context context, String name, int version) { super(context, name, null, version);}

    @Override
    public void onCreate(SQLiteDatabase sqld) {

        Log.d(null, "teste onCreate " +TABELA);

        sqld.execSQL("CREATE TABLE "+TABELA+"("
                + ID + " INTEGER PRIMARY KEY autoincrement,"
                + TIPOGASTO + " varchar(30) NOT NULL,"
                + VALOR + " double,"
                + DATA + "varchar(8),"
                + DESCRICAO + " varchar (45),"
                + LOCAL + " varchar(30),"
                + ID_VIAGEM + " integer,"
                + "FOREIGN KEY ("+ID_VIAGEM+") REFERENCES viagem(id_viagem))"
                + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
