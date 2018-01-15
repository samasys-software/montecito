package com.montecito.samayu.service;

import com.dto.LoginDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Preethiv on 1/13/2018.
 */

public interface MontecitoService {
@POST("montecito/auth/local")
    @FormUrlEncoded
    public Call<LoginDTO> authenticate(@Field("userId") String userId, @Field("password")String password);

}
