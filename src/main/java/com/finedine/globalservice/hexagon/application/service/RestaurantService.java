package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.RestaurantServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.RestaurantDao;
import com.finedine.globalservice.hexagon.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService implements RestaurantServicePort {

    private final RestaurantDao restaurantDao;

    @Override
    public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
        return restaurantDao.createRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantDao.getAllRestaurants();
    }

    @Override
    public RestaurantModel getRestaurantById(String id) {
        return restaurantDao.getRestaurantById(id);
    }

    @Override
    public boolean deleteRestaurant(String id) {
        return restaurantDao.deleteRestaurant(id);
    }

    @Override
    public RestaurantModel updateRestaurant(String id, RestaurantModel restaurantModel) {
        return restaurantDao.updateRestaurant(id, restaurantModel);
    }

    @Override
    public RestaurantModel patchRestaurant(String id, RestaurantModel restaurantModel) {
        return restaurantDao.patchRestaurant(id,restaurantModel);
    }
}
