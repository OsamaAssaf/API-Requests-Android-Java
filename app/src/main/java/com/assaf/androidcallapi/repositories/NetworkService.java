package com.assaf.androidcallapi.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkService {

    private static final OkHttpClient client = new OkHttpClient();

    public static void get(String url, NetworkCallback callback) {
        final Request getRequest = new Request.Builder()
                .url(url)
                .build();

        client.newCall(getRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response);
                } else {
                    callback.onFailure("Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("onFailure: " + e.getMessage());
                callback.onFailure("Response not successful or body is null");
            }
        });
    }


}
