package br.edu.vicente.cadastropet.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.cadastropet.R;
import br.edu.vicente.cadastropet.dao.CadastroDAO;
import br.edu.vicente.cadastropet.model.Cadastro;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListarPetActivity extends AppCompatActivity {

    //obj utilizado para vincular com o componente activity
    ListView listaCadastros;
    CadastroDAO cadastroDAO;
    List<Cadastro>cadastros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pet);
        setTitle("LISTA PET");
        listaCadastros = (ListView) findViewById(R.id.lista_animais);

        cadastroDAO = new CadastroDAO(this);

        cadastros = cadastroDAO.obterCadastro();

        ArrayAdapter adapter = new ArrayAdapter<Cadastro>(
                this, android.R.layout.simple_list_item_1,
                cadastros
        );
        //atribuir os valores para o listview
        listaCadastros.setAdapter(adapter);
        registerForContextMenu(listaCadastros);
    }

    public void novoCadastro(View view) {
        //redireciona para a tela que realiza o cadastro
        startActivity(new Intent(this, Cadastro_PetActivity.class));
    }
    @Override
    public void onResume(){
        super.onResume();
        cadastros =  cadastroDAO.obterCadastro();
        //adapatador para o listView
        ArrayAdapter adapter = new ArrayAdapter<Cadastro>(
                this, android.R.layout.simple_list_item_1,
                cadastros
        );
        listaCadastros.setAdapter(adapter);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contexto, menu);
        /*menu.add(Menu.NONE,1,Menu.NONE,"EDITAR");
        menu.add(Menu.NONE,2,Menu.NONE,"EXCLUIR");*/
    }

    public void editarCadastro(MenuItem item){
        //obter o item da lista qye foi clicado
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //transformar num obj do tipo cadastro
        Cadastro cadastroEditar = cadastros.get(menuInfo.position);

        Intent intent = new Intent(this, Cadastro_PetActivity.class);
        intent.putExtra("cadastro", cadastroEditar);
        startActivity(intent);
    }

    public void excluirCadastro(MenuItem item){
        //criar um adaptar view para ver qual item foi clicado
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //transformar num obj do tipo tarefa o item clicado
        final Cadastro cadastroExcluir = cadastros.get(menuInfo.position);

        //criar uma caixa de dialogo para confirmar a exclusão
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("DESEJA REALMENTE EXCLUIR O CADASTRO")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //remover da ListView ou lista de tarefas
                        cadastros.remove(cadastroExcluir);
                        //remover do BD
                        cadastroDAO.excluirCadastro(cadastroExcluir);
                        //atualizar a listView, reovendo o item selecionado para organizar
                        listaCadastros.invalidateViews();
                    }
                }).create();
        //mostrar a caixa de dialogo ao usuario
        dialog.show();
    }
}
