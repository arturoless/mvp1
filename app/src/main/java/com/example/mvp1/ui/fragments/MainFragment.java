package com.example.mvp1.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvp1.R;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    Button loginButton;
    EditText password;
    EditText user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        password = (EditText) getView().findViewById(R.id.password);
        user = (EditText) getView().findViewById(R.id.user);
        loginButton = (Button) getView().findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("admin".equals(String.valueOf(password.getText()))){
                    Bundle bundle = new Bundle();
                    bundle.putString("userType", "admin");
                    TablaFragment tabla = new TablaFragment();
                    tabla.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, tabla)
                            .commitNow();

                }
                else if("user".equals(String.valueOf(password.getText()))){
                    Bundle bundle = new Bundle();
                    bundle.putString("userType", "user");
                    TablaFragment tabla = new TablaFragment();
                    tabla.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, tabla)
                            .commitNow();
                }


            }
        });

    }

}
