package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.ServiceTypeEntity;
import com.finedine.globalservice.adapter.persistance.mapper.ServiceTypeEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.ServiceTypeRepository;
import com.finedine.globalservice.hexagon.application.port.spi.ServiceTypeDao;
import com.finedine.globalservice.hexagon.domain.model.ServiceTypeModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceTypeAdapter implements ServiceTypeDao {

    private final ServiceTypeRepository serviceTypeRepository;

    @Override
    public ServiceTypeModel createServiceType(ServiceTypeModel serviceTypeModel) {
        return Optional.ofNullable(serviceTypeModel)
                .map(ServiceTypeEntityMapper.INSTANCE::toServiceTypeEntity)
                .map(serviceTypeRepository::save)
                .map(ServiceTypeEntityMapper.INSTANCE::toServiceTypeModel)
                .orElseThrow(() -> new IllegalArgumentException("ServiceTypeModel cannot be null"));
    }

    @Override
    public boolean deleteServiceTypeById(String serviceTypeId) {
        if (serviceTypeRepository.existsById(serviceTypeId)) {
            serviceTypeRepository.deleteById(serviceTypeId);
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceTypeModel> getAllServiceTypes() {
        return serviceTypeRepository.findAll().stream()
                .map(ServiceTypeEntityMapper.INSTANCE::toServiceTypeModel)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceTypeModel getServiceTypeById(String serviceTypeId) {
        return serviceTypeRepository.findById(serviceTypeId)
                .map(ServiceTypeEntityMapper.INSTANCE::toServiceTypeModel)
                .orElse(null);
    }

    @Override
    public ServiceTypeModel patchServiceType(String serviceTypeId, ServiceTypeModel model) {
        ServiceTypeEntity existingItem = serviceTypeRepository.findById(serviceTypeId)
                .orElseThrow(() -> new EntityNotFoundException("ServiceType not found"));

        Optional.ofNullable(model.getTypeName()).ifPresent(existingItem::setTypeName);
        Optional.ofNullable(model.getRestaurantId()).ifPresent(existingItem::setRestaurantId);
        Optional.ofNullable(model.getUpdatedBy()).ifPresent(existingItem::setUpdatedBy);

        existingItem = serviceTypeRepository.save(existingItem);
        return ServiceTypeEntityMapper.INSTANCE.toServiceTypeModel(existingItem);
    }

    @Override
    public ServiceTypeModel updateServiceType(String serviceTypeId, ServiceTypeModel model) {
        return serviceTypeRepository.findById(serviceTypeId)
                .map(existingEntity -> {
                    model.setServiceTypeId(existingEntity.getServiceTypeId());
                    ServiceTypeEntity entity = serviceTypeRepository.save(ServiceTypeEntityMapper.INSTANCE.toServiceTypeEntity(model));
                    return ServiceTypeEntityMapper.INSTANCE.toServiceTypeModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("ServiceType not found with id: " + serviceTypeId));
    }
}
