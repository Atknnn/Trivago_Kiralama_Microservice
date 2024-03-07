package com.atakan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProfiliRequestDto {
    String token;
    String email;
    String name;
    String photo;
    String phone;
}
