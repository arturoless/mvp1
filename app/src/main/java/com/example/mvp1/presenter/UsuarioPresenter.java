package com.example.mvp1.presenter;

import com.example.mvp1.model.TableModel;
import com.example.mvp1.model.UsuarioModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UsuarioPresenter {

    private View view;
    private UsuarioModel usuario;
    private TableModel tablaModel;

    public UsuarioPresenter(View v){
        this.view=v;
        this.usuario = new UsuarioModel();
        tablaModel = new TableModel();
    }
    public void obtenerLista(String url){
        tablaModel.setUrl(url);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(tablaModel.getUrl(), null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                view.obtenerList(response);

            }
        });

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
    public void eliminarUsuario(String id){
        String url = "https://reqres.in/api/users/"+id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.delete(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                view.eliminarUsuario(response);
            }
        });
    }

    public void actualizarUsuario(UsuarioModel usuario){
        String url = "https://reqres.in/api/users/"+usuario.getId();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("first_name",usuario.getFirstName());
        params.put("last_name", usuario.getLastName());
        params.put("email", usuario.getEmail());
        client.put(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                view.actualizarUsuario(response);
            }
        });
    }

    public void crearUsuario(UsuarioModel usuario){
        String url = "https://reqres.in/api/users/";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("first_name",usuario.getFirstName());
        params.put("last_name", usuario.getLastName());
        params.put("email", usuario.getEmail());
        client.post(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                view.crearUsuario(response);
            }
        });

    }

    public interface View{
        void obtenerList(JSONObject response);
        void getUser(UsuarioModel usuario);
        void eliminarUsuario(JSONObject response);
        void actualizarUsuario(JSONObject response);
        void crearUsuario(JSONObject response);
    }
}
