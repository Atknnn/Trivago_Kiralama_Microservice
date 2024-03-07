package com.atakan.service;

import com.atakan.dto.request.UserProfileRequestDto;
import com.atakan.repository.UserProfileRepository;
import com.atakan.repository.entity.UserProfile;
import com.atakan.utility.enums.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserProfileElasticServiceUnitTest {

    /**
     * İçerisine constructor üzerinden nesne inject edilecek sınıfa ilgili anotasyonu ekliyouz @InjectMocks
     */
    @InjectMocks
    private UserProfileElasticService userProfileElasticService;

    /**
     * Eğer bir sınıf nesnesi başka bir sınıf içine parametre olarak verilecek ise @Mock anotasyonu ekliyoruz
     */
    @Mock
    private UserProfileRepository userProfileRepository;

    @Test
    void updateForUserProfileEmpty(){
        userProfileElasticService.update(UserProfileRequestDto.builder()
                        .id("1243")
                .build());
        // userProfileRepository nin  findOptionalByAuthId methodu herhangi bir
        // long değer ile 1 kez çağırılmalıdır.
        verify(userProfileRepository).findOptionalByUserId(anyString());
        // userProfileRepository nin  findOptionalByAuthId methodu herhangi bir
        // UserProfile nesnesi ile 1 kez çağırımı yapılmalıdır.
        verify(userProfileRepository).save(any(UserProfile.class));
    }

    @Test
    void updateForUserProfilePresent(){
        UserProfile profile = UserProfile.builder()
                .userId("1243")
                .userName("Muhammet")
                .authId(2L)
                .email("muhammet@gmail.com")
                .name("muhammet")
                .phone("0 88 99 55442")
                .photo("")
                .state(State.AKTIF)
                .build();
        when(userProfileRepository.findOptionalByUserId(anyString()))
                .thenReturn(Optional.of(profile));
        userProfileElasticService.update(UserProfileRequestDto.builder()
                .id("1243")
                        .userName("Ali")
                        .authId(2L)
                        .email("ali@veli.com")
                        .name("ali veli")
                        .phone("0 4444444")
                        .photo("fdfdsfsdssd")
                        .state(State.PASIF)
                .build());
        verify(userProfileRepository).findOptionalByUserId(anyString());
        verify(userProfileRepository).save(any(UserProfile.class));
    }

}
