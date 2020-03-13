package com.example.mvp1.model;

import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp1.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TableModel {
    private static JSONObject objetoResponse = new JSONObject();

    private String url;

    public void setUrl(String url){
        this.url=url;
    }

    public String getUrl(){
        return url;
    }
    public void hacerGetParaLista(String url) {


    }

    public void guardarJSON(JSONObject response){
        this.objetoResponse=response;
    }

    public JSONObject obtenerJSON(){
        return objetoResponse;
    }
}
