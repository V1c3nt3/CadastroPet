package br.edu.vicente.cadastropet.view;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.cadastropet.R;
import br.edu.vicente.cadastropet.dao.CadastroDAO;
import br.edu.vicente.cadastropet.model.Cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro_PetActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEspecie;
    private EditText campoMarca;
    private EditText campoDono;
    private EditText campoTratamento;

    private CadastroDAO cadastroDAO;

    private Cadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__pet);
        setTitle("CADASTRO DE ANIMAIS");

        campoNome = findViewById(R.id.edt_nome);
        campoEspecie = findViewById(R.id.edt_especie);
        campoMarca = findViewById(R.id.edt_marca);
        campoDono = findViewById(R.id.edt_dono);
        campoTratamento = findViewById(R.id.edt_tratamento);

        cadastroDAO = new CadastroDAO(this);

        Intent intent = getIntent();
        if (intent.hasExtra("cadastro")) {
            cadastro = (Cadastro) intent.getSerializableExtra("cadastro");
            campoNome.setText(cadastro.getNome());
            campoEspecie.setText(cadastro.getEspecie());
            campoMarca.setText(cadastro.getMarca());
            campoDono.setText(cadastro.getDono());
            campoTratamento.setText(cadastro.getTratamento());
        }
    }


    public void voltarInicio(View view) {
        startActivity(new Intent(this, ListarPetActivity.class));
    }

    public void inserirCadastro(View view) {
        if (cadastro == null){
            cadastro = new Cadastro();
            cadastro.setNome(campoNome.getText().toString().trim());
            cadastro.setEspecie(campoEspecie.getText().toString().trim());
            cadastro.setMarca(campoMarca.getText().toString().trim());
            cadastro.setDono(campoDono.getText().toString().trim());
            cadastro.setTratamento(campoTratamento.getText().toString().trim());

            long id = cadastroDAO.inserirCadastro(cadastro);
            Toast.makeText(this,"Cadastro Realizado : "+id,
                    Toast.LENGTH_LONG).show();
        }else {
            cadastro.setNome(campoNome.getText().toString().trim());
            cadastro.setEspecie(campoEspecie.getText().toString().trim());
            cadastro.setMarca(campoMarca.getText().toString().trim());
            cadastro.setDono(campoDono.getText().toString().trim());
            cadastro.setTratamento(campoTratamento.getText().toString().trim());

            cadastroDAO.atualizarCadastro(cadastro);

            Toast.makeText(this,"Cadastro Atualizado : ",
                    Toast.LENGTH_LONG).show();
        }
    }
}
