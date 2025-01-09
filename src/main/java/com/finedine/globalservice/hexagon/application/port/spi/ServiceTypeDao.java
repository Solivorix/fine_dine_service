package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.ServiceTypeModel;

import java.util.List;

public interface ServiceTypeDao {
    ServiceTypeModel createServiceType(ServiceTypeModel serviceTypeModel);

    boolean deleteServiceTypeById(String serviceTypeId);

    List<ServiceTypeModel> getAllServiceTypes();

    ServiceTypeModel getServiceTypeById(String serviceTypeId);

    ServiceTypeModel patchServiceType(String serviceTypeId, ServiceTypeModel model);

    ServiceTypeModel updateServiceType(String serviceTypeId, ServiceTypeModel model);
}
