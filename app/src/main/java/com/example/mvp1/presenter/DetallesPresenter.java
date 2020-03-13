package com.example.mvp1.presenter;

import android.view.View;

import com.example.mvp1.model.PokemonModel;
import com.example.mvp1.model.UsuarioModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetallesPresenter {

    private View view;
    private UsuarioModel usuario;
    private PokemonModel pokemon;

    public DetallesPresenter(View v){
        this.view=v;
        this.usuario = new UsuarioModel();
        this.pokemon= new PokemonModel();
    }

    public void obtenerUsuario(String id){
        String url = "https://reqres.in/api/users/"+id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject user = response.getJSONObject("data");
                    usuario.setId(String.valueOf(user.get("id")));
                    usuario.setFirstName(String.valueOf(user.get("first_name")));
                    usuario.setLastName(String.valueOf(user.get("last_name")));
                    usuario.setEmail(String.valueOf(user.get("email")));
                    view.getUser(usuario);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void obtenerPokemon(String id){
        String url = "https://pokeapi.co/api/v2/pokemon/"+id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    pokemon.setName(String.valueOf(response.get("name")));
                    pokemon.setHeight(String.valueOf(response.get("height")));
                    pokemon.setWeight(String.valueOf(response.get("weight")));
                    view.getPokemon(pokemon);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public interface View{
        void getUser(UsuarioModel usuario);
        void getPokemon(PokemonModel usuario);
    }
}
