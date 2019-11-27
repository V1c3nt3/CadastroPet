package br.edu.vicente.cadastropet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.vicente.cadastropet.model.Cadastro;

public class CadastroDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;
    public CadastroDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserirCadastro(Cadastro cadastro){
        //obj contendo os valores para salvar no bd
        ContentValues values = new ContentValues();
        values.put("nome", cadastro.getNome());
        values.put("especie", cadastro.getEspecie());
        values.put("marca", cadastro.getMarca());
        values.put("dono", cadastro.getDono());
        values.put("tratamento", cadastro.getTratamento());
        return banco.insert("cadastro", null, values);
    }

    public List<Cadastro> obterCadastro(){
        List<Cadastro> cadastros = new ArrayList<>();

        Cursor cursor = banco.query(
                "cadastro",
                new String[]{"id","nome","especie","marca","dono","tratamento"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()){
            Cadastro cadastro = new Cadastro();

            cadastro.setId(cursor.getInt(0));
            cadastro.setNome(cursor.getString(1));
            cadastro.setEspecie(cursor.getString(2));
            cadastro.setMarca(cursor.getString(3));
            cadastro.setDono(cursor.getString(4));
            cadastro.setTratamento(cursor.getString(5));

            cadastros.add(cadastro);
        }
        return cadastros;
    }

    public void excluirCadastro(Cadastro cadastro){
        banco.delete("cadastro","id==?",
                new String[]{String.valueOf(cadastro.getId())});
    }

    public void atualizarCadastro(Cadastro cadastro){
        ContentValues values = new ContentValues();

        //insere os valores
        values.put("nome", cadastro.getNome());
        values.put("especie",cadastro.getEspecie());
        values.put("marca",cadastro.getMarca());
        values.put("dono",cadastro.getDono());
        values.put("tratamento",cadastro.getTratamento());

        //atualiza os dados no banco após edição
        banco.update("cadastro", values,
                "id = ?",
                new String[]{String.valueOf(cadastro.getId())});
    }
}
