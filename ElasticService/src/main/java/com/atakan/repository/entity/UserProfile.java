package com.atakan.repository.entity;

import com.atakan.utility.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * DİKKATT
 * camelCase notasyonunda yazmayınız
 * userProfile yazılmaz.
 */
@Document(indexName= "userprofile")
public class UserProfile {
    @Id
    String id;
    String userId;
    Long authId;
    String userName;
    String email;
    String name;
    String photo;
    String phone;
    State state;

}
