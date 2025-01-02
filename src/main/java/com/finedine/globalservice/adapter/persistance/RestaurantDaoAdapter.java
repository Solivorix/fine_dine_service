package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.RestaurantEntity;
import com.finedine.globalservice.adapter.persistance.mapper.RestaurantEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.RestaurantRepository;
import com.finedine.globalservice.hexagon.application.port.spi.RestaurantDao;
import com.finedine.globalservice.hexagon.domain.model.RestaurantModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantDaoAdapter implements RestaurantDao {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
        return Optional.ofNullable(restaurantModel)
                .map(RestaurantEntityMapper.INSTANCE::toRestaurantEntity)
                .map(restaurantRepository::save)
                .map(RestaurantEntityMapper.INSTANCE::toRestaurantModel)
                .orElseThrow(() -> new IllegalArgumentException("RestaurantModel cannot be null"));
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantEntityMapper.INSTANCE::toRestaurantModel)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantModel getRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .map(RestaurantEntityMapper.INSTANCE::toRestaurantModel)
                .orElse(null);
    }

    @Override
    public boolean deleteRestaurant(String id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public RestaurantModel updateRestaurant(String id, RestaurantModel restaurantModel) {
        return restaurantRepository.findById(id)
                .map(existingEntity -> {
                    restaurantModel.setRestId(existingEntity.getRestId());
                    RestaurantEntity entity = restaurantRepository.save(RestaurantEntityMapper.INSTANCE.toRestaurantEntity(restaurantModel));
                    return RestaurantEntityMapper.INSTANCE.toRestaurantModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
    }

    @Override
    @Transactional
    public RestaurantModel patchRestaurant(String id, RestaurantModel restaurantModel) {
        RestaurantEntity existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        // Update only the fields that are not null in the DTO
        Optional.ofNullable(restaurantModel.getName()).ifPresent(existingRestaurant::setName);
        Optional.ofNullable(restaurantModel.getGstNo()).ifPresent(existingRestaurant::setGstNo);
        Optional.ofNullable(restaurantModel.getAddress()).ifPresent(existingRestaurant::setAddress);
        Optional.ofNullable(restaurantModel.getPrimaryEmailId()).ifPresent(existingRestaurant::setPrimaryEmailId);
        Optional.ofNullable(restaurantModel.getPrimaryContactNumber()).ifPresent(existingRestaurant::setPrimaryContactNumber);
        Optional.ofNullable(restaurantModel.getModifiedBy()).ifPresent(restaurantModel::setModifiedBy);

        existingRestaurant = restaurantRepository.save(existingRestaurant);
        return RestaurantEntityMapper.INSTANCE.toRestaurantModel(existingRestaurant);
    }
}
