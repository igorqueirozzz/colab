package br.com.app.colab.services;

import java.util.ArrayList;

import br.com.app.colab.dto.UpdatePhotoDTO;
import br.com.app.colab.dto.UserCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServices {

    @POST("/api/users/auth")
    Call<ArrayList> getUser(@Body UserCredentials userCredentials);

    @POST("/api/users/update-photo")
    Call<String> updatePhoto(@Body UpdatePhotoDTO updatePhotoDTO);

}
