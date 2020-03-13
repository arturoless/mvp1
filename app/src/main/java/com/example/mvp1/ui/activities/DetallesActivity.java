package com.example.mvp1.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp1.R;
import com.example.mvp1.model.PokemonModel;
import com.example.mvp1.model.UsuarioModel;
import com.example.mvp1.presenter.DetallesPresenter;

public class DetallesActivity extends AppCompatActivity implements DetallesPresenter.View {

    private String typeUser;
    private String id;
    private TextView textViewId;
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewEmail;
    private TextView textViewName;
    private TextView textViewHeight;
    private TextView textViewWeight;

    private DetallesPresenter presentador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("id");
        typeUser = myIntent.getStringExtra("typeUser");
        super.onCreate(savedInstanceState);

        presentador=new DetallesPresenter(this);
        if (typeUser.equals("admin")){
            setContentView(R.layout.activity_detalles);
            textViewId=findViewById(R.id.textViewId);
            textViewFirstName=findViewById(R.id.textViewFirstName);
            textViewLastName=findViewById(R.id.textViewLastName);
            textViewEmail=findViewById(R.id.textViewEmail);
            presentador.obtenerUsuario(id);
        }
        else if(typeUser.equals("user")){
            textViewId=findViewById(R.id.textViewId);
            setContentView(R.layout.pokemon_detalles);
            textViewName=findViewById(R.id.textViewName);
            textViewHeight=findViewById(R.id.textViewHeight);
            textViewWeight=findViewById(R.id.textViewWeight);
            presentador.obtenerPokemon(id);
        }



    }

    @Override
    public void getUser(UsuarioModel usuario) {
        textViewId.setText("ID: "+usuario.getId());
        textViewFirstName.setText("Nombre: "+usuario.getFirstName());
        textViewLastName.setText("Apellido: "+usuario.getLastName());
        textViewEmail.setText("Email: "+usuario.getEmail());
    }

    @Override
    public void getPokemon(PokemonModel pokemon) {
        textViewName.setText("Nombre: "+pokemon.getName());
        textViewHeight.setText("Altura: "+pokemon.getHeight());
        textViewWeight.setText("Peso: "+pokemon.getWeight());

    }
}
