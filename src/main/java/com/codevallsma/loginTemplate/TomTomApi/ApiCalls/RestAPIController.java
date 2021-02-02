package com.codevallsma.loginTemplate.TomTomApi.ApiCalls;

import com.codevallsma.loginTemplate.TomTomApi.Model.Example;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Query;

public class RestAPIController {

    private static String SERVER_URL = "https://api.tomtom.com/search/2/nearbySearch/.json";
    private static RestAPIController instance;
    private Retrofit retrofit;
    private RestAPI restApi;
    private String tokenApiTomTom;

    public static RestAPIController getInstance() {
        if (instance == null) {
            instance = new RestAPIController();
        }
        return instance;
    }


    private RestAPIController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        restApi = retrofit.create(RestAPI.class);
        tokenApiTomTom  = "L7WNbLd9EjhSSPUzeTbwsYJDBfg24c1M";
    }

    public void getNearestRestaurant(String latitude, String longitude, String queryLimit, Integer categoryNum, String apiKey){
        Call<Example> callSync = restApi.getNearestRestaurant(latitude, longitude, queryLimit, categoryNum, apiKey);
        try
        {
            Response<Example> response = callSync.execute();
            Example apiResponse = response.body();

            //API response
            System.out.println(apiResponse);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}