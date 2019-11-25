package br.edu.vicente.cadastropet.view;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.vicente.cadastropet.R;

import android.os.Bundle;

public class Cadastro_PetActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__pet);
        setTitle("CADASTRO DE ANIMAIS");
    }
}
