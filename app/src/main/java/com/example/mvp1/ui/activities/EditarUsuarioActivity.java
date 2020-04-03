package com.example.mvp1.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp1.R;
import com.example.mvp1.model.UsuarioModel;
import com.example.mvp1.presenter.UsuarioPresenter;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarUsuarioActivity extends AppCompatActivity implements UsuarioPresenter.View{

    private Button buttonGuardar;
    private Button buttonCancelar;
    private UsuarioPresenter presentador;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private UsuarioModel usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        Intent myIntent = getIntent();
        usuario = new UsuarioModel();
        usuario.setId(myIntent.getStringExtra("idUsuario"));
        buttonCancelar=findViewById(R.id.buttonCancelar);
        buttonGuardar=findViewById(R.id.buttonGuardar);
        nombre=findViewById(R.id.editTextNombre);
        apellido=findViewById(R.id.editTextApellido);
        email=findViewById(R.id.editTextEmail);
        presentador=new UsuarioPresenter(this);
        if(usuario.getId()!=null){
            presentador.obtenerUsuario(usuario.getId());
        }
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setFirstName(nombre.getText().toString());
                usuario.setLastName(apellido.getText().toString());
                usuario.setEmail(email.getText().toString());
                if(usuario.getId()!=null){
                    presentador.actualizarUsuario(usuario);
                }
                else{
                    presentador.crearUsuario(usuario);
                }
            }
        });
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void obtenerList(JSONObject response) {

    }

    @Override
    public void getUser(UsuarioModel usuario) {
        nombre.setText(usuario.getFirstName());
        apellido.setText(usuario.getLastName());
        email.setText(usuario.getLastName());
    }

    @Override
    public void eliminarUsuario(JSONObject response) {

    }

    @Override
    public void actualizarUsuario(JSONObject response) {
        try {
            Toast.makeText(getApplicationContext(),String.valueOf(response.get("updatedAt")),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public void crearUsuario(JSONObject response) {
        try {
            Toast.makeText(getApplicationContext(),String.valueOf(response.get("createdAt")),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getBaseContext(), ListaUsuariosActivity.class);
        startActivity(intent);
    }
}
