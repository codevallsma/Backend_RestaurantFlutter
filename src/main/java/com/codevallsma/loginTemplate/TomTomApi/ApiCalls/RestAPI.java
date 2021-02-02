package com.codevallsma.loginTemplate.TomTomApi.ApiCalls;

import com.codevallsma.loginTemplate.TomTomApi.Model.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestAPI {

    @GET
    Call<Example> getNearestRestaurant(@Query("lat") String latitude, @Query("lon")String longitude, @Query("limit") String queryLimit, @Query("categorySet") Integer categoryNum, @Query("key") String apiKey);

}
