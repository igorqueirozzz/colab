package br.com.centralit.citcolab.services;

import java.util.List;

import br.com.centralit.citcolab.model.PointRegisters;
import br.com.centralit.citcolab.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PointRegisterServices {

    @GET("api/point-register/search/{user}")
    Call<List<PointRegisters>> getRegisterList(@Path("user") User user);
}
