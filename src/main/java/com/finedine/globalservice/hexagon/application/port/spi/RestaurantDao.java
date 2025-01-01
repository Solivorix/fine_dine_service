package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.RestaurantModel;

import java.util.List;

public interface RestaurantDao {
    RestaurantModel createRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();

    RestaurantModel getRestaurantById(String id);

    boolean deleteRestaurant(String id);

    RestaurantModel updateRestaurant(String id, RestaurantModel restaurantModel);

    RestaurantModel patchRestaurant(String id, RestaurantModel restaurantModel);
}
