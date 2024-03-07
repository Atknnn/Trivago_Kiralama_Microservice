package com.atakan.service;

import com.atakan.dto.request.GetProfileByTokenRequestDto;
import com.atakan.dto.request.UpdateProfiliRequestDto;
import com.atakan.dto.request.UserProfileSaveRequestDto;
import com.atakan.dto.response.UserProfileResponseDto;
import com.atakan.exception.ErrorType;
import com.atakan.exception.UserException;
import com.atakan.manager.ElasticSearchUserProfileManager;
import com.atakan.mapper.UserProfileMapper;
import com.atakan.repository.UserProfileRepository;
import com.atakan.repository.entity.UserProfile;
import com.atakan.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final CacheManager cacheManager;
    private final ElasticSearchUserProfileManager manager;
    public UserProfile save(UserProfileSaveRequestDto dto){
        UserProfile result = repository.save(UserProfileMapper.INSTANCE.fromDto(dto));
        Objects.requireNonNull(cacheManager.getCache("userprofilefindall")).clear();
        manager.save(UserProfileMapper.INSTANCE.toUserProfileRequestDto(result));
        return result;
//        return repository.save(UserProfile.builder()
//                        .userName(dto.getUserName())
//                        .authId(dto.getAuthId())
//                .build());
    }

    public UserProfileResponseDto getProfileByToken(GetProfileByTokenRequestDto dto) {
        /**
         * Kullanıcı token bilgisini gönderiyor, jwtManager ile token bilgisini doğruluyor ve içinden
         * kişinin authId bilgisini almaya çalışıyoruz.
         */
        Optional<Long> authId = jwtTokenManager.getIdByToken(dto.getToken());
        if(authId.isEmpty())
            throw new UserException(ErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = repository.findOptionalByAuthId(authId.get());
        if(userProfile.isEmpty())
            throw new UserException(ErrorType.USER_NOT_FOUND);
        return UserProfileMapper.INSTANCE.toUserProfileResponseDto(userProfile.get());
    }

    public Boolean updateProfile(UpdateProfiliRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.getIdByToken(dto.getToken());
        if(authId.isEmpty())
            throw new UserException(ErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = repository.findOptionalByAuthId(authId.get());
        if(userProfile.isEmpty())
            throw new UserException(ErrorType.USER_NOT_FOUND);
        UserProfile updateProfile = userProfile.get();
        updateProfile.setName(dto.getName());
        updateProfile.setEmail(dto.getEmail());
        updateProfile.setPhoto(dto.getPhoto());
        updateProfile.setPhone(dto.getPhone());
        repository.save(updateProfile);
        Objects.requireNonNull(cacheManager.getCache("userprofilefindall")).clear();
        manager.update(UserProfileMapper.INSTANCE.toUserProfileRequestDto(updateProfile));
        return true;
    }

    /**
     * Burada method için ir cache oluşturup bunun redis tarafından kayıt edilmesini sağlayacağınz.
     * redis bu methodu şu şekilde kayıt edecek;
     *  KEY                     VALUE
     * getUpperName:muhammet -> MUHAMMET
     * getUpperName:canan    -> CANAN
     * @param userName
     * @return
     */
    @Cacheable(value = "getuppername")
    public String getUpperName(String userName){
        String result = userName.toUpperCase();
        try{
            Thread.sleep(4000L);
        }catch (Exception exception){
            System.out.println("Hata oldu");
            return "Error...: "+ exception;
        }
        return result;
    }

    public void clearKey(String key){
        Objects.requireNonNull(cacheManager.getCache("getuppername")).evict(key);
    }

    @Cacheable(value = "userprofilefindall")
    public List<UserProfile> getAllUserProfile() {
        return repository.findAll();
    }


    /***
     *
     * @param page -> Hangi sayfayı görmek istediğinizi belirtir.
     * @param size -> bir sayfada kaç kayıt görmek istediğinizi belirtir.
     * @param sortParameter -> hangi parametreye göre sıralama yapmak istediğinizi belirtir. (ad, id, userName)
     * @param sortDirection -> sıralama yönünü belirtir. (ASC, DESC)
     * @return
     */
    public Page<UserProfile> findAll(int page, int size, String sortParameter, String sortDirection){
        Pageable pageable;
        if(sortParameter !=null && !sortParameter.isEmpty()){
            /**
             * Sıralama için bir nesne yaratmak,
             * Sort nesnesi gerekli,
             * Sort.Direction -> ASC(a...z, 0...9), DESC(z...a, 9...0)
             */
            Sort sort =  Sort.by(Sort.Direction.fromString(sortDirection == null ? "ASC" : sortDirection), sortParameter);
            pageable = PageRequest.of(page, size, sort);

        }else
            pageable = Pageable.ofSize(size).withPage(page);

        return repository.findAll(pageable);
    }


}
