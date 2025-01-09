package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.ServiceTypeEntity;
import com.finedine.globalservice.hexagon.domain.model.ServiceTypeModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface ServiceTypeEntityMapper {

    ServiceTypeEntityMapper INSTANCE = Mappers.getMapper(ServiceTypeEntityMapper.class);

    ServiceTypeEntity toServiceTypeEntity(ServiceTypeModel serviceTypeModel);
    ServiceTypeModel toServiceTypeModel(ServiceTypeEntity serviceTypeEntity);

    List<ServiceTypeEntity> toServiceTypeEntityList(List<ServiceTypeModel> serviceTypeModelList);
    List<ServiceTypeModel> toServiceTypeModelList(List<ServiceTypeEntity> serviceTypeEntityList);
}
