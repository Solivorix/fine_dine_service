package com.finedine.globalservice.util.mapper;

import org.mapstruct.*;

@MapperConfig(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommonMappingConfiguration {

}
