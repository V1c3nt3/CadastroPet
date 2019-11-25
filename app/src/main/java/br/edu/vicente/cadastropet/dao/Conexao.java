package br.edu.vicente.cadastropet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "cadastro.db";
    private static final int version = 1;

    public Conexao(Context context){
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cadastro(" +
                "id integer primary key autoincrement," +
                "nome varchar(50),"+
                "especie varchar(50),"+
                "marca varchar(50),"+
                "dono varchar(50),"+
                "tratamento varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
