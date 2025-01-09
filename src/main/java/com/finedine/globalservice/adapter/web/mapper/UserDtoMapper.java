package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.UserModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetToLocal")
    UserModel toUserModel(UserDto userDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localToOffset")
    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(UserModel userModel);

    List<UserModel> toUserModelList(List<UserDto> userDtoList);
    List<UserDto> toUserDtoList(List<UserModel> userModelList);
}
