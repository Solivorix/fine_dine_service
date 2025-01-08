package com.finedine.globalservice.adapter.web;

import com.finedine.api.ServiceTypesApi;
import com.finedine.model.ServiceTypeDto;
import com.finedine.model.ServiceTypePartialDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ServiceTypeController implements ServiceTypesApi {
    @Override
    public ResponseEntity<ServiceTypeDto> createServiceType(ServiceTypeDto serviceTypeDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteServiceTypeById(String serviceTypeId) {
        return null;
    }

    @Override
    public ResponseEntity<List<ServiceTypeDto>> getAllServiceTypes() {
        return null;
    }

    @Override
    public ResponseEntity<ServiceTypeDto> getServiceTypeById(String serviceTypeId) {
        return null;
    }

    @Override
    public ResponseEntity<ServiceTypeDto> partiallyUpdateServiceTypeById(String serviceTypeId, ServiceTypePartialDto serviceTypePartialDto) {
        return null;
    }

    @Override
    public ResponseEntity<ServiceTypeDto> updateServiceTypeById(String serviceTypeId, ServiceTypeDto serviceTypeDto) {
        return null;
    }
}
