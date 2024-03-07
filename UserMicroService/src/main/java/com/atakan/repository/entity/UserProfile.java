package com.atakan.repository.entity;


import com.atakan.utility.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserProfile implements Serializable {
    @Id
    String id;
    Long authId;
    String userName;
    String email;
    String name;
    String photo;
    String phone;
    State state;

}
