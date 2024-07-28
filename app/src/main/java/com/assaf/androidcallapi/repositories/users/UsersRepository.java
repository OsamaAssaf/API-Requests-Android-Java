package com.assaf.androidcallapi.repositories.users;

import com.assaf.androidcallapi.models.User;
import com.assaf.androidcallapi.repositories.DataCallback;

import java.util.ArrayList;

public interface UsersRepository {
    void getUsers(DataCallback<ArrayList<User>> callback);
}
