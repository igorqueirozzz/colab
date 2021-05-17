package br.com.app.colab.services;





import br.com.app.colab.dto.GetRegisterMonthListDTO;
import br.com.app.colab.dto.RegisterDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

import retrofit2.http.POST;


public interface PointRegisterServices {

    @POST("/api/point-controller/get-registers-month")
    Call<ArrayList> getRegistersByMonth(@Body GetRegisterMonthListDTO reference);

    @POST("/api/point-controller/register")
    Call<Void> registerNewPoint(@Body RegisterDTO pointRegister);

    @GET("/api/point-controller/teste")
    Call<String> getTest();
}
