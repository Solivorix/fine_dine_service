package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.ServiceTypePort;
import com.finedine.globalservice.hexagon.application.port.spi.ServiceTypeDao;
import com.finedine.globalservice.hexagon.domain.model.ServiceTypeModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceTypeService implements ServiceTypePort {

    private final ServiceTypeDao serviceTypeDao;

    @Override
    public ServiceTypeModel createServiceType(ServiceTypeModel serviceTypeModel) {
        return serviceTypeDao.createServiceType(serviceTypeModel);
    }

    @Override
    public boolean deleteServiceTypeById(String serviceTypeId) {
        return serviceTypeDao.deleteServiceTypeById(serviceTypeId);
    }

    @Override
    public List<ServiceTypeModel> getAllServiceTypes() {
        return serviceTypeDao.getAllServiceTypes();
    }

    @Override
    public ServiceTypeModel getServiceTypeById(String serviceTypeId) {
        return serviceTypeDao.getServiceTypeById(serviceTypeId);
    }

    @Override
    public ServiceTypeModel patchServiceType(String serviceTypeId, ServiceTypeModel model) {
        return serviceTypeDao.patchServiceType(serviceTypeId, model);
    }

    @Override
    public ServiceTypeModel updateServiceType(String serviceTypeId, ServiceTypeModel model) {
        return serviceTypeDao.updateServiceType(serviceTypeId, model);
    }
}
