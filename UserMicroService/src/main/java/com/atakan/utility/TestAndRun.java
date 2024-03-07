package com.atakan.utility;

import com.atakan.manager.ElasticSearchUserProfileManager;
import com.atakan.mapper.UserProfileMapper;
import com.atakan.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class TestAndRun {

    private final UserProfileRepository repository;
    private final ElasticSearchUserProfileManager manager;
    //@PostConstruct
    public void init(){
       repository.findAll().forEach(u->{
           manager.update(UserProfileMapper.INSTANCE.toUserProfileRequestDto(u));
           System.out.println("gönderildi... "+ u.getUserName());
       });
    }
}
