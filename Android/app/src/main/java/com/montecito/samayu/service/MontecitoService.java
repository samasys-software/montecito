package com.montecito.samayu.service;

import com.montecito.samayu.domain.Consumption;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.LoginDTO;
import com.montecito.samayu.dto.LoginInput;
import com.montecito.samayu.ui.ItemBinDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Preethiv on 1/13/2018.
 */

public interface MontecitoService {
    @POST("auth/local")
    public Call<LoginDTO> authenticate(@Body LoginInput loginInput);

    @GET("api/items/consumption/today/category")
    public Call<List<Consumption>> getConsumptionInfoCategory(@Header("Authorization") String token);

    @GET("api/replenishtasks/")
    public Call<List<ItemAvailabilityDTO>> getItemAvailablityDTO(@Header("Authorization") String token);


    @GET("api/items/consumption/today/item")
    public Call<List<Consumption>> getConsumptionInfoItems(@Header("Authorization") String token);


    @GET("api/items/consumption/today/floor")
    public Call<List<Consumption>> getConsumptionInfoFloor(@Header("Authorization") String token);

    @GET("api/itembins")
    public Call<List<ItemBinDTO>> getItemBinDTO(@Header("Authorization") String token );

    @GET("api/itembins/{itemBinId}/summary")
    public Call<ItemBinDetailsDTO> getItemBinDetails( @Path("itemBinId") String itemBinId, @Header("Authorization") String token);

}
