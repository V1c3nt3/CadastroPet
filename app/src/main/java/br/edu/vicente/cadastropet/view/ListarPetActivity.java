package br.edu.vicente.cadastropet.view;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.cadastropet.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListarPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pet);
        setTitle("LISTA PET");
    }

    public void novoCadastro(View view) {
        //redireciona para a tela que realiza o cadastro
        startActivity(new Intent(this, Cadastro_PetActivity.class));
    }
}
