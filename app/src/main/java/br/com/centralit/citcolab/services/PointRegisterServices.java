package br.com.centralit.citcolab.services;





import br.com.centralit.citcolab.dto.GetRegisterMonthListDTO;
import br.com.centralit.citcolab.dto.RegisterDTO;
import com.google.gson.*;

import org.json.JSONArray;

import java.util.ArrayList;

import br.com.centralit.citcolab.model.PointRegister;
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
