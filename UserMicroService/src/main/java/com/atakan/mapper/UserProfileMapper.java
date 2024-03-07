package com.atakan.mapper;

import com.atakan.dto.request.UserProfileRequestDto;
import com.atakan.dto.request.UserProfileSaveRequestDto;
import com.atakan.dto.response.UserProfileResponseDto;
import com.atakan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfile fromDto(final UserProfileSaveRequestDto dto);

    UserProfileResponseDto toUserProfileResponseDto(final UserProfile userProfile);

    UserProfile fromUserProfileRequestDto(final UserProfileRequestDto dto);
    UserProfileRequestDto toUserProfileRequestDto(final UserProfile userProfile);
}
