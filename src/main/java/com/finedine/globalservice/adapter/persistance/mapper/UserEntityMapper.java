package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.UserEntity;
import com.finedine.globalservice.hexagon.domain.model.UserModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = CommonMappingConfiguration.class)
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserEntity toUserEntity(UserModel userModel);
    UserModel toUserModel(UserEntity userEntity);
}
