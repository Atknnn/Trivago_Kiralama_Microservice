package com.atakan.controller;

import com.atakan.dto.request.GetProfileByTokenRequestDto;
import com.atakan.dto.request.UpdateProfiliRequestDto;
import com.atakan.dto.request.UserProfileSaveRequestDto;
import com.atakan.dto.response.UserProfileResponseDto;
import com.atakan.repository.entity.UserProfile;
import com.atakan.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.atakan.constants.RestApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/getmessage")
    public String getMessage(){
        return "Bu UserProfile Servistir";
    }

    @GetMapping("/upper-name")
    public String getUpperName(String userName){
        return userProfileService.getUpperName(userName);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserProfile>> getAllUserProfile(){
        return ResponseEntity.ok(
                userProfileService.getAllUserProfile());
    }

    @GetMapping("/clear-key")
    public ResponseEntity<Void> clearKey(String key){
        userProfileService.clearKey(key);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Valid UserProfileSaveRequestDto dto){
       UserProfile user =   userProfileService.save(dto);
       return ResponseEntity.ok().build();
    }

    @PostMapping("/get-profile")
    public ResponseEntity<UserProfileResponseDto> getProfileByToken(@RequestBody @Valid GetProfileByTokenRequestDto dto){
        return ResponseEntity.ok(userProfileService.getProfileByToken(dto));
    }

    @PostMapping("/update-profile")
    public ResponseEntity<Boolean> updateProfile(@RequestBody UpdateProfiliRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateProfile(dto));
    }

    @GetMapping("/find-all-page")
    public ResponseEntity<Page<UserProfile>> findAllPage(int page, int size, String sortParameter, String sortDirection){
        return ResponseEntity.ok(userProfileService.findAll(page,size,sortParameter,sortDirection));
    }

}
