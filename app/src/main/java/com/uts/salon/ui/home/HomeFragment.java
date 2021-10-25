package com.uts.salon.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.uts.salon.Model.User;
import com.uts.salon.Preferences.UserPreferences;
import com.uts.salon.R;
import com.uts.salon.ui.Pesan.PesanActivity;
import com.uts.salon.ui.Pesan.TampilActivity;
import com.uts.salon.ui.auth.LoginActivity;
import com.google.android.material.button.MaterialButton;
import com.uts.salon.ui.maps.MapsActivity;
import com.uts.salon.ui.maps.MapActivity;

public class HomeFragment extends Fragment {
    private TextView tvWelcome;
    private MaterialButton btnLogout, btnLokasi, btnPesan, btnTampil, btnMap;
    private User user;
    private UserPreferences userPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        userPreferences = new UserPreferences(getContext());
        tvWelcome = root.findViewById(R.id.tvWelcome);
        btnLogout = root.findViewById(R.id.btnLogout);
        btnLokasi = root.findViewById(R.id.btnLokasi);
        btnPesan = root.findViewById(R.id.btnPesan);
        btnTampil = root.findViewById(R.id.btnTampil);
        //btnMap = root.findViewById(R.id.btnMap);

        user = userPreferences.getUserLogin();

        checkLogin();

        tvWelcome.setText("Selamat datang, "+user.getName());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(getContext(), "Baiii baiii", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MapActivity.class));
            }
        });

        /*btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PesanActivity.class));
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TampilActivity.class));
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MapActivity.class));
            }
        });

         */

        return root;
    }

    private void checkLogin(){
        /* this function will check if user login , akan memunculkan toast jika tidak redirect ke login activity */
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }else {
            Toast.makeText(getContext(), "Welcome back !", Toast.LENGTH_SHORT).show();
        }
    }
}