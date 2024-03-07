package com.atakan.mapper;

import com.atakan.dto.request.UserProfileRequestDto;
import com.atakan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserProfileMapper {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Mapping(source = "id",target = "userId")
    UserProfile fromDto(final UserProfileRequestDto dto);
}
