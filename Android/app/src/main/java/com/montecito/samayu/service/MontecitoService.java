package com.montecito.samayu.service;

import com.dto.ConsumptionInfo;
import com.dto.ItemAvailabilityDTO;
import com.dto.LoginDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Preethiv on 1/13/2018.
 */

public interface MontecitoService {
@POST("montecito/auth/local")
    @FormUrlEncoded
    public Call<LoginDTO> authenticate(@Field("userId") String userId, @Field("password")String password);

    @GET("montecito/api/items/consumption/today/category")
    public Call<List<ConsumptionInfo>> getConsumptionInfo(@Header("Authorization") String token);

    @GET("montecito/api/tasks/user")
    public Call<List<ItemAvailabilityDTO>> getItemAvailablityDTO(@Header("Authorization") String token);

}
