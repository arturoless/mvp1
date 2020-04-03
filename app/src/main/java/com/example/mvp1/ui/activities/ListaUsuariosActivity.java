package com.example.mvp1.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp1.R;
import com.example.mvp1.model.UsuarioModel;
import com.example.mvp1.presenter.TablePresenter;
import com.example.mvp1.presenter.UsuarioPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListaUsuariosActivity extends AppCompatActivity implements UsuarioPresenter.View {
    private UsuarioPresenter presenter;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        presenter = new UsuarioPresenter(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditarUsuarioActivity.class);
                startActivity(intent);
            }
        });
        String url = "https://reqres.in/api/users";
        presenter.obtenerLista(url);

    }

    @Override
    public void obtenerList(JSONObject response) {
        TableLayout tabla = (TableLayout) findViewById(R.id.tablaUsuarios);
        try{
            final JSONArray lista = response.getJSONArray("data");
            for (int i=0; i < lista.length(); i++) {
                final JSONObject thing = lista.getJSONObject(i);
                TableRow row= new TableRow(getApplicationContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                TextView tv = new TextView(getApplicationContext());
                tv.setText(String.valueOf(thing.get("first_name")));
                row.addView(tv);
                TextView tv2 = new TextView(getApplicationContext());
                tv2.setText(String.valueOf(thing.get("email")));
                row.addView(tv2);
                final Button detalles = new Button(getApplicationContext());
                detalles.setBackgroundColor(Color.TRANSPARENT);
                detalles.setText("DETALLES");
                detalles.setTag(String.valueOf(thing.get("id")));
                detalles.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String tag = (String) detalles.getTag();
                        Intent intent = new Intent(getBaseContext(), DetallesActivity.class);
                        intent.putExtra("idUsuario",tag);
                        startActivity(intent);
                    }
                });
                row.addView(detalles);
                tabla.addView(row);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getUser(UsuarioModel usuario) {

    }

    @Override
    public void eliminarUsuario(JSONObject response) {

    }

    @Override
    public void actualizarUsuario(JSONObject response) {

    }

    @Override
    public void crearUsuario(JSONObject response) {

    }
}
