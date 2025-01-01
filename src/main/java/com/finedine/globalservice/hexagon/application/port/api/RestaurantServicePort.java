package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.RestaurantModel;

import java.util.List;

public interface RestaurantServicePort {

    RestaurantModel createRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();

    RestaurantModel getRestaurantById(String id);

    boolean deleteRestaurant(String id);

    RestaurantModel updateRestaurant(String id, RestaurantModel restaurantModel);

    RestaurantModel patchRestaurant(String id, RestaurantModel restaurantModel);
}
