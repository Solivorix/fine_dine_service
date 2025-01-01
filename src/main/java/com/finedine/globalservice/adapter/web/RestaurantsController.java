package com.finedine.globalservice.adapter.web;

import com.finedine.api.RestaurantsApi;
import com.finedine.globalservice.adapter.web.mapper.RestaurantDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.RestaurantServicePort;
import com.finedine.model.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RestaurantsController implements RestaurantsApi {

    private final RestaurantServicePort restaurantServicePort;

    @Override
    public ResponseEntity<RestaurantDto> createRestaurant(RestaurantDto restaurantDto) {
        return Optional.ofNullable(restaurantDto)
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantModel)
                .map(restaurantServicePort::createRestaurant)
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("RestaurantDto cannot be null"));
    }

    @Override
    public ResponseEntity<Void> deleteRestaurant(String id) {
        return restaurantServicePort.deleteRestaurant(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(
                Optional.ofNullable(restaurantServicePort.getAllRestaurants())
                        .map(RestaurantDtoMapper.INSTANCE::toRestaurantDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<RestaurantDto> getRestaurantById(String id) {
        return Optional.ofNullable(restaurantServicePort.getRestaurantById(id))
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<RestaurantDto> patchRestaurant(String id, RestaurantDto restaurantDto) {
        return Optional.ofNullable(restaurantDto)
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantModel)
                .map(model -> restaurantServicePort.patchRestaurant(id, model))
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("RestaurantDto cannot be null"));
    }

    @Override
    public ResponseEntity<RestaurantDto> updateRestaurant(String id, RestaurantDto restaurantDto) {
        return Optional.ofNullable(restaurantDto)
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantModel)
                .map(model -> restaurantServicePort.updateRestaurant(id, model))
                .map(RestaurantDtoMapper.INSTANCE::toRestaurantDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("RestaurantDto cannot be null"));
    }
}
