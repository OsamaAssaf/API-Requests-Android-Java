package com.assaf.androidcallapi.repositories;


import java.io.IOException;

import okhttp3.Response;

public interface NetworkCallback {
    void onSuccess(Response response) throws IOException;

    void onFailure(String errorMessage);
}
