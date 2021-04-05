package br.com.centralit.citcolab.services;

import java.util.List;

import br.com.centralit.citcolab.dto.RegisterDTO;
import br.com.centralit.citcolab.model.PointRegister;
import br.com.centralit.citcolab.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PointRegisterServices {

    @POST("/api/users/register-point/")
    Call<RegisterDTO> registerNewPoint(@Body RegisterDTO pointRegister);
}
