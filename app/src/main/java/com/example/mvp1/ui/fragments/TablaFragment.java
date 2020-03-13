package com.example.mvp1.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp1.ui.activities.DetallesActivity;
import com.example.mvp1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mvp1.presenter.TableFragmentPresenter;

public class TablaFragment extends Fragment implements TableFragmentPresenter.View {


    private TableFragmentPresenter presenter;
    JSONArray listaJson = new JSONArray();
    Button botonCrear;
    String label;
    public static TablaFragment newInstance() {
        return new TablaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabla_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new TableFragmentPresenter(this);

        label = getArguments().getString("userType");
        if ("admin".equals(label)){
            String url = "https://reqres.in/api/users";
            Toast.makeText(getActivity().getApplicationContext(),label,Toast.LENGTH_SHORT).show();
            presenter.obtenerLista(url);
        }
        else if ("user".equals(label)){
            String url = "https://pokeapi.co/api/v2/pokemon/";
            Toast.makeText(getActivity().getApplicationContext(),label,Toast.LENGTH_SHORT).show();
            presenter.obtenerLista(url);
        }


    }

    @Override
    public void obtenerList(JSONObject response) {
        TableLayout tabla = (TableLayout) getActivity().findViewById(R.id.tabla);
        try{
            if ("admin".equals(label)){
                final JSONArray lista = response.getJSONArray("data");
                for (int i=0; i < lista.length(); i++) {
                    final JSONObject thing = lista.getJSONObject(i);
                    TableRow row= new TableRow(getActivity().getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    TextView tv = new TextView(getActivity().getApplicationContext());
                    tv.setText(String.valueOf(thing.get("email")));
                    row.addView(tv);
                    TextView tv2 = new TextView(getActivity().getApplicationContext());
                    tv2.setText(String.valueOf(thing.get("first_name")));
                    row.addView(tv2);
                    final Button detalles = new Button(getActivity().getApplicationContext());
                    detalles.setBackgroundColor(Color.TRANSPARENT);
                    detalles.setText("VER DETALLES");
                    detalles.setTag(String.valueOf(thing.get("id")));
                    detalles.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String tag = (String) detalles.getTag();
                            Intent intent = new Intent(getActivity().getBaseContext(), DetallesActivity.class);
                            intent.putExtra("typeUser",label);
                            intent.putExtra("id",tag);
                            startActivity(intent);
                        }
                    });
                    row.addView(detalles);
                    tabla.addView(row);
                }
            }
            else if ("user".equals(label)){
                final JSONArray lista = response.getJSONArray("results");
                for (int i=0; i < lista.length(); i++) {
                    final JSONObject thing = lista.getJSONObject(i);
                    TableRow row= new TableRow(getActivity().getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    TextView tv = new TextView(getActivity().getApplicationContext());
                    tv.setText(String.valueOf(thing.get("name")));
                    row.addView(tv);
                    TextView tv2 = new TextView(getActivity().getApplicationContext());
                    tv2.setText(String.valueOf(thing.get("url")));
                    row.addView(tv2);
                    final Button detalles = new Button(getActivity().getApplicationContext());
                    detalles.setBackgroundColor(Color.TRANSPARENT);
                    detalles.setText("VER DETALLES");
                    detalles.setTag(String.valueOf(thing.get("name")));
                    detalles.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String tag = (String) detalles.getTag();
                            Intent intent = new Intent(getActivity().getBaseContext(), DetallesActivity.class);
                            intent.putExtra("typeUser",label);
                            intent.putExtra("id",tag);
                            startActivity(intent);
                        }
                    });
                    row.addView(detalles);
                    tabla.addView(row);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
