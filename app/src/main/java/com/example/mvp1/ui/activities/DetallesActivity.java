package com.example.mvp1.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp1.R;
import com.example.mvp1.model.UsuarioModel;
import com.example.mvp1.presenter.UsuarioPresenter;

import org.json.JSONObject;

public class DetallesActivity extends AppCompatActivity implements UsuarioPresenter.View {

    private String typeUser;
    private String id;
    private TextView textViewId;
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewEmail;
    private Button buttonEliminar;
    private Button buttonEditar;

    private UsuarioPresenter presentador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("idUsuario");
        super.onCreate(savedInstanceState);

        presentador=new UsuarioPresenter(this);
        setContentView(R.layout.activity_detalles);
        textViewId=findViewById(R.id.textViewId);
        textViewFirstName=findViewById(R.id.textViewFirstName);
        textViewLastName=findViewById(R.id.textViewLastName);
        textViewEmail=findViewById(R.id.textViewEmail);
        buttonEliminar=findViewById(R.id.buttonEliminar);
        buttonEditar=findViewById(R.id.buttonEditar);
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentador.eliminarUsuario(id);
            }
        });
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditarUsuarioActivity.class);
                intent.putExtra("idUsuario",id);
                startActivity(intent);
            }
        });
        presentador.obtenerUsuario(id);




    }

    @Override
    public void obtenerList(JSONObject response) {

    }

    @Override
    public void getUser(UsuarioModel usuario) {
        textViewId.setText("ID: "+usuario.getId());
        textViewFirstName.setText("Nombre: "+usuario.getFirstName());
        textViewLastName.setText("Apellido: "+usuario.getLastName());
        textViewEmail.setText("Email: "+usuario.getEmail());
    }

    @Override
    public void eliminarUsuario(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Usuario eliminado",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), ListaUsuariosActivity.class);
        startActivity(intent);
    }

    @Override
    public void actualizarUsuario(JSONObject response) {

    }

    @Override
    public void crearUsuario(JSONObject response) {

    }

}
