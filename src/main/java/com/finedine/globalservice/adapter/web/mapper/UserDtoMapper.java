package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.UserModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.LoginRequestDto;
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


    @Mapping(target = "userName", source = "loginId")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "restaurantId", ignore = true)
    @Mapping(target = "passwordSalt", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "contactNumber", ignore = true)
    UserModel toUserModel(LoginRequestDto loginRequestDto);

    List<UserModel> toUserModelList(List<UserDto> userDtoList);

    List<UserDto> toUserDtoList(List<UserModel> userModelList);
}
