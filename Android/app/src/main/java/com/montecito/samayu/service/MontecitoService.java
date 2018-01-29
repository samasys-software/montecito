package com.montecito.samayu.service;

import com.montecito.samayu.domain.Consumption;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.LoginDTO;

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
    public Call<LoginDTO> authenticate(@Field("userId") String userId, @Field("ic_password")String password);

    @GET("montecito/api/items/consumption/today/category")
    public Call<List<Consumption>> getConsumptionInfoCategory(@Header("Authorization") String token);

    @GET("montecito/api/tasks/user")
    public Call<List<ItemAvailabilityDTO>> getItemAvailablityDTO(@Header("Authorization") String token);


    @GET("montecito/api/items/consumption/today/items")
    public Call<List<Consumption>> getConsumptionInfoItems(@Header("Authorization") String token);


    @GET("montecito/api/items/consumption/today/floor")
    public Call<List<Consumption>> getConsumptionInfoFloor(@Header("Authorization") String token);

}
