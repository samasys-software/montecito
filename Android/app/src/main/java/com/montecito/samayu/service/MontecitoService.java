package com.montecito.samayu.service;

import com.montecito.samayu.dto.ConsumptionDTO;
import com.montecito.samayu.domain.ChangePassword;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.LoginDTO;
import com.montecito.samayu.domain.LoginInput;
import com.montecito.samayu.domain.Status;
import com.montecito.samayu.dto.UserProfileDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Preethiv on 1/13/2018.
 */

public interface MontecitoService {
    @POST("auth/local")
    public Call<LoginDTO> authenticate(@Body LoginInput loginInput);

    @GET("api/items/consumption/today/category")
    public Call<List<ConsumptionDTO>> getConsumptionInfoCategory(@Header("Authorization") String token);

    @GET("api/replenishtasks/")
    public Call<List<ItemAvailabilityDTO>> getItemAvailablityDTO(@Header("Authorization") String token);


    @GET("api/items/consumption/today/item")
    public Call<List<ConsumptionDTO>> getConsumptionInfoItems(@Header("Authorization") String token);


    @GET("api/items/consumption/today/floor")
    public Call<List<ConsumptionDTO>> getConsumptionInfoFloor(@Header("Authorization") String token);

    @GET("api/itembins")
    public Call<List<ItemBinDTO>> getItemBinDTO(@Header("Authorization") String token );

    @GET("api/itembins/{itemBinId}/summary")
    public Call<ItemBinDetailsDTO> getItemBinDetails( @Path("itemBinId") String itemBinId, @Header("Authorization") String token);

    @PUT("api/itembins/{itemBinId}/itemAlert")
    public Call<ItemBinDetailsDTO> itemAlert(@Path("itemBinId") String itemBinId,@Body Status enable,@Header("Authorization") String token);

    @PUT("api/itembins/{itemBinId}/stockAlert")
    public Call<ItemBinDetailsDTO> stockAlert(@Path("itemBinId") String itemBinId, @Body Status enable, @Header("Authorization") String token);



    @PUT("api/users/{userId}/password")
    public Call<ResponseBody> changePassword(@Path("userId") String userId, @Body ChangePassword changePassword, @Header("Authorization") String token);



    @GET("api/devices/active/count")
    public Call<ResponseBody> getDevicesActiveCount(@Header("Authorization") String token);

    @GET("api/users/me")
    public Call<UserProfileDTO> getUserProfile(@Header("Authorization") String token);



}
