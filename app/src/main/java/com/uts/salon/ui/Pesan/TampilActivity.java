package com.uts.salon.ui.Pesan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.salon.Adapter.TodoAdapter;
import com.uts.salon.Database.DatabaseClient;
import com.uts.salon.Model.Todo;
import com.uts.salon.Preferences.UserPreferences;
import com.uts.salon.R;

import java.util.ArrayList;
import java.util.List;

public class TampilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);
    }
}

