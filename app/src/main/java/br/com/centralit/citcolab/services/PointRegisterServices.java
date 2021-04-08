package br.com.centralit.citcolab.services;



import java.util.ArrayList;
import java.util.List;

import br.com.centralit.citcolab.dto.GetRegisterMonthListDTO;
import br.com.centralit.citcolab.dto.RegisterDTO;

import br.com.centralit.citcolab.model.PointRegister;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PointRegisterServices {

    @POST("/api/point-controller/get-registers-month")
    Call<List<PointRegister>> getRegistersByMonth(@Body GetRegisterMonthListDTO reference);
    @POST("/api/point-controller/register")
    Call<RegisterDTO> registerNewPoint(@Body RegisterDTO pointRegister);

    @GET("/api/point-controller/teste")
    Call<String> getTest();
}
