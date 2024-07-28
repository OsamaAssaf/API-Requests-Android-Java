package com.assaf.androidcallapi;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assaf.androidcallapi.models.User;
import com.assaf.androidcallapi.repositories.DataCallback;
import com.assaf.androidcallapi.repositories.users.UsersApi;
import com.assaf.androidcallapi.repositories.users.UsersRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final UsersRepository usersRepository = new UsersApi();
    private RecyclerView usersRecyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        getUsers();
    }

    private void initViews() {
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void getUsers() {
        progressBar.setVisibility(View.VISIBLE);
        final Context context = this;

        usersRepository.getUsers(new DataCallback<ArrayList<User>>() {
            @Override
            public void onSuccess(ArrayList<User> data) {
                runOnUiThread(() -> {
                    final UsersAdapter usersAdapter = new UsersAdapter(context);
                    usersRecyclerView.setAdapter(usersAdapter);
                    usersRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    usersAdapter.setUsers(data);
                    progressBar.setVisibility(View.GONE);
                    System.out.println("DoneDone");
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                });
            }
        });

    }
}