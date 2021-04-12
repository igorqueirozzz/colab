package br.com.centralit.citcolab.services;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.centralit.citcolab.dto.UserCredentials;
import br.com.centralit.citcolab.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserServices {

    @POST("/api/users/auth")
    Call<ArrayList> getUser(@Body UserCredentials userCredentials);

}
