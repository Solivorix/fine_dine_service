package com.finedine.globalservice.adapter.web;

import com.finedine.api.ServiceTypesApi;
import com.finedine.globalservice.adapter.web.mapper.ServiceTypeDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.ServiceTypePort;
import com.finedine.model.ServiceTypeDto;
import com.finedine.model.ServiceTypePartialDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ServiceTypeController implements ServiceTypesApi {

    private final ServiceTypePort serviceTypePort;
    @Override
    public ResponseEntity<ServiceTypeDto> createServiceType(ServiceTypeDto serviceTypeDto) {
        return Optional.ofNullable(serviceTypeDto)
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeModel)
                .map(serviceTypePort::createServiceType)
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("ServiceTypeDto cannot be null"));
    }

    @Override
    public ResponseEntity<Void> deleteServiceTypeById(String serviceTypeId) {
        return serviceTypePort.deleteServiceTypeById(serviceTypeId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<ServiceTypeDto>> getAllServiceTypes() {
        return ResponseEntity.ok(
                Optional.ofNullable(serviceTypePort.getAllServiceTypes())
                        .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<ServiceTypeDto> getServiceTypeById(String serviceTypeId) {
        return Optional.ofNullable(serviceTypePort.getServiceTypeById(serviceTypeId))
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ServiceTypeDto> partiallyUpdateServiceTypeById(String serviceTypeId, ServiceTypePartialDto serviceTypePartialDto) {
        return Optional.ofNullable(serviceTypePartialDto)
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeModel)
                .map(model -> serviceTypePort.patchServiceType(serviceTypeId, model))
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("ServiceTypePartialDto cannot be null"));
    }

    @Override
    public ResponseEntity<ServiceTypeDto> updateServiceTypeById(String serviceTypeId, ServiceTypeDto serviceTypeDto) {
        return Optional.ofNullable(serviceTypeDto)
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeModel)
                .map(model -> serviceTypePort.updateServiceType(serviceTypeId, model))
                .map(ServiceTypeDtoMapper.INSTANCE::toServiceTypeDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("ServiceTypeDto cannot be null"));
    }
}
