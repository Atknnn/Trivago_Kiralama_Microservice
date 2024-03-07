package com.atakan.repository;

import com.atakan.repository.entity.UserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile,String> {

    Optional<UserProfile> findOptionalByAuthId(Long id);

    List<UserProfile> findAllByUserNameContaining(String userName, Pageable pageable);

}
