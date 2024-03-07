package com.atakan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileResponseDto {
    String id;
    String userName;
    String email;
    String name;
    String photo;
    String phone;
}
