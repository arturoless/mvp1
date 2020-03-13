package com.example.mvp1.presenter;

import android.view.View;

import com.example.mvp1.model.TableModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class TableFragmentPresenter {
    private View view;
    private TableModel tablaModel;

    public TableFragmentPresenter(View v){
        this.view= v;
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


    public interface View {
        void obtenerList(JSONObject response);
    }



}
