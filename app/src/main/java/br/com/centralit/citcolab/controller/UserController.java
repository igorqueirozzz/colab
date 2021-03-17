package br.com.centralit.citcolab.controller;

import java.util.List;

import br.com.centralit.citcolab.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserController {

    @GET("/api/users/{id}")
    Call<List<User>> getUser(@Path("id") Integer id);
}
