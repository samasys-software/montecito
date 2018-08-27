package com.matriot.cbin.service;

import com.matriot.cbin.domain.PushNotification;
import com.matriot.cbin.dto.AverageDTO;
import com.matriot.cbin.dto.ConsumptionCategoryDTO;

import com.matriot.cbin.domain.ChangePassword;
import com.matriot.cbin.dto.ConsumptionItemDTO;
import com.matriot.cbin.dto.CountDTO;
import com.matriot.cbin.dto.ItemAvailabilityDTO;
import com.matriot.cbin.dto.ItemBinDTO;
import com.matriot.cbin.dto.ItemBinDetailsDTO;
import com.matriot.cbin.dto.LoginDTO;
import com.matriot.cbin.domain.LoginInput;
import com.matriot.cbin.domain.Status;
import com.matriot.cbin.dto.OnTimeDTO;
import com.matriot.cbin.dto.RegisterPushNotificationDTO;
import com.matriot.cbin.dto.TopItemsDTO;
import com.matriot.cbin.dto.UserProfileDTO;

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

    @POST("api/users/{currentUserId}/accessdevice/register")
    public Call<RegisterPushNotificationDTO> registerDevice(@Path("currentUserId") String currentUserId, @Body PushNotification pushNotification,@Header("Authorization") String token);

    @POST("api/users/{currentUserId}/accessdevice/unregister")
    public Call<RegisterPushNotificationDTO> unRegisterDevice(@Path("currentUserId") String currentUserId, @Body PushNotification pushNotification,@Header("Authorization") String token);

    @GET("api/items/consumption/today/category")
    public Call<List<ConsumptionCategoryDTO>> getConsumptionInfoCategory(@Header("Authorization") String token);

    @GET("api/replenishtasks/")
    public Call<List<ItemAvailabilityDTO>> getItemAvailablityDTO(@Header("Authorization") String token);


    @GET("api/items/consumption/today/item")
    public Call<List<ConsumptionItemDTO>> getConsumptionInfoItems(@Header("Authorization") String token);


    @GET("api/items/consumption/today/floor")
    public Call<List<ConsumptionItemDTO>> getConsumptionInfoFloor(@Header("Authorization") String token);

    @GET("api/itembins")
    public Call<List<ItemBinDTO>> getItemBinDTO(@Header("Authorization") String token );

    @GET("api/itembins/{itemBinId}")
    public Call<ItemBinDTO> getItemBinChangesDTO(@Path("itemBinId") String itemBinId ,@Header("Authorization") String token );

    @GET("api/itembins/{itemBinId}/summary")
    public Call<ItemBinDetailsDTO> getItemBinDetails( @Path("itemBinId") String itemBinId, @Header("Authorization") String token);

    @PUT("api/itembins/{itemBinId}/itemAlert")
    public Call<ItemBinDetailsDTO> itemAlert(@Path("itemBinId") String itemBinId,@Body Status enable,@Header("Authorization") String token);

    @PUT("api/itembins/{itemBinId}/stockAlert")
    public Call<ItemBinDetailsDTO> stockAlert(@Path("itemBinId") String itemBinId, @Body Status enable, @Header("Authorization") String token);



    @PUT("api/users/{userId}/password")
    public Call<ResponseBody> changePassword(@Path("userId") String userId, @Body ChangePassword changePassword, @Header("Authorization") String token);



    @GET("api/devices/active/cBin/count")
    public Call<CountDTO> getDevicesActiveCount(@Header("Authorization") String token);

    @GET("api/replenishments/ontime")
    public Call<OnTimeDTO> getOnTime(@Header("Authorization") String token);

    @GET("api/replenishments/batch/average")
    public Call<AverageDTO> getAverage(@Header("Authorization") String token);


    @GET("api/replenishments/top/item")
    public Call<List<TopItemsDTO>> getTopItems(@Header("Authorization") String token);


    @GET("api/users/me")
    public Call<UserProfileDTO> getUserProfile(@Header("Authorization") String token);

    @GET("api/itembins/sort/{value}/order/{order}")
    public Call<List<ItemBinDTO>> getSortByDetails(@Path("value") String value,@Path("order") String order,@Header("Authorization") String token);




}
