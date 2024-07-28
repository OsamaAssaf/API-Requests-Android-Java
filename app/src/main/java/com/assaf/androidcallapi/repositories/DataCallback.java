package com.assaf.androidcallapi.repositories;

public interface DataCallback<T> {

    void onSuccess(T data);

    void onFailure( String errorMessage);
}
