package br.com.centralit.citcolab.services;

import java.util.List;
import java.util.Optional;

import br.com.centralit.citcolab.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserServices {

    @GET("/api/users/{id}")
    Call<User> getUser(@Path("id") Integer id);

}
