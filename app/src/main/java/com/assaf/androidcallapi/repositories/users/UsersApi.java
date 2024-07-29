package com.assaf.androidcallapi.repositories.users;

import com.assaf.androidcallapi.models.User;
import com.assaf.androidcallapi.repositories.DataCallback;
import com.assaf.androidcallapi.repositories.NetworkCallback;
import com.assaf.androidcallapi.repositories.NetworkService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Response;

public class UsersApi implements UsersRepository {


    @Override
    public void getUsers(DataCallback<ArrayList<User>> callback) {


        NetworkService.get("https://jsonplaceholder.typicode.com/users", new NetworkCallback() {

            @Override
            public void onSuccess(Response response) throws IOException {
                assert response.body() != null;
                final String responseData = response.body().string();
                final Gson gson = new Gson();
                final Type type = new TypeToken<ArrayList<User>>() {
                }.getType();
                final ArrayList<User> users = gson.fromJson(responseData, type);
                callback.onSuccess(users);
            }

            @Override
            public void onFailure(String errorMessage) {
                callback.onFailure(errorMessage);
            }
        });
    }


}
