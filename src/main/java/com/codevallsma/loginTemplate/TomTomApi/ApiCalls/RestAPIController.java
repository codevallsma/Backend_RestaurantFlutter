package com.codevallsma.loginTemplate.TomTomApi.ApiCalls;

import com.codevallsma.loginTemplate.TomTomApi.Model.CategorySet;
import com.codevallsma.loginTemplate.TomTomApi.Model.Example;
import com.codevallsma.loginTemplate.model.Category;
import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.repositories.CategoryRepository;
import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RestAPIController {

    private static String SERVER_URL = "https://api.tomtom.com/search/2/nearbySearch/";
    private static RestAPIController instance;
    private Retrofit retrofit;
    private RestAPI restApi;
    private String tokenApiTomTom;
    private RestaurantRepository restaurantRepository;
    private CategoryRepository categoryRepository;

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
        tokenApiTomTom  = "f1jMQAPM2IFGWxOqF2UdFYWnkK1RgjWG";
    }

    public void getNearestRestaurant(Restaurant restaurant, String queryLimit) throws IOException {
        Call<Example> callSync = restApi.getNearestRestaurant(String.valueOf(restaurant.getLatitud()), String.valueOf(restaurant.getLongitud()), queryLimit, 7315, tokenApiTomTom);
        Response<Example> response = callSync.execute();
        Example apiResponse = response.body();
        //get element
        assert apiResponse != null;
        //get categories
        List<String> categories = apiResponse.getResults().get(0).getPoi().getCategories();
        Set<Category> dbCategory= null;
        if(categories!=null) {
            //getting the existing categories and storing then in the database
            dbCategory = getDbCategories(categories);
        }
        //getting the name of the nearest restaurant
        String name = apiResponse.getResults().get(0).getPoi().getName();
        //setting the name to store it in the database
        restaurant.setRestaurantName(name);
        //storing the relationship between the restaurant and the category
        restaurant.setCategoria_restaurant(dbCategory);
        restaurantRepository.save(restaurant);
        //API response
        System.out.println(apiResponse);
    }

    private HashSet<Category> getDbCategories(List<String> categories){
        HashSet<Category> categoriesDB = new HashSet<Category>();
        for (String categoria_TOMTOM:
                categories) {
            Category resultSearch = categoryRepository.findByCategoria(categoria_TOMTOM);
            if (resultSearch == null){
                resultSearch = new Category(categoria_TOMTOM);
                //saving the category
                categoriesDB.add(categoryRepository.save(resultSearch));
            } else {
                categoriesDB.add(resultSearch);
            }
        }
        return categoriesDB;
    }

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}