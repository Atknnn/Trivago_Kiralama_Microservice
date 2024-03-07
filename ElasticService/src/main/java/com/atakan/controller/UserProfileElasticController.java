package com.atakan.controller;

import com.atakan.dto.request.UserProfileRequestDto;
import com.atakan.repository.entity.UserProfile;
import com.atakan.service.UserProfileElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elastic-user-profile")
@RequiredArgsConstructor
public class UserProfileElasticController {
    private final UserProfileElasticService userProfileElasticService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserProfileRequestDto dto){
        userProfileElasticService.save(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UserProfileRequestDto dto){
        userProfileElasticService.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-all")
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileElasticService.findAll());
    }

    /**
     *
     * -> find-by-id -> all fields
     * -> find-by-id -> only id,username and photo
     * -> find-by-username
     * -> find-by-email
     * -> find-by-phone
     * -> find-by-username-and-email -> contains
     *
     */

    @GetMapping("/find-all-page")
    public ResponseEntity<Page<UserProfile>> findAllPage(int page,int size,String sortParameter,String sortDirection){
        return ResponseEntity.ok(userProfileElasticService.findAll(page,size,sortParameter,sortDirection));
    }

    @GetMapping("/get-message")
    public String getMessage(){
        return "Elastik Servis";
    }

    @GetMapping("/get-secret-message")
    public String getSecretMessage(){
        return "Elastik Servis:  gizli bir mesaj";
    }


    @GetMapping("/get-user-message")
    public String getUserMessage(){
        return "kullanıcının gizli mesajı";
    }

    @GetMapping("/get-ahmet")
    @PreAuthorize("hasAnyAuthority('Ahmet_Amca','Super_Admin')")
    public String getAhmetAmcaMesaji(){
        return "Bugün nasılsın yeğen ?";
    }

    @GetMapping("/gizli")
    @PreAuthorize("hasAnyAuthority('GIZLI')")
    public String getGizliBirMesaj(){
        return "Çooooookk g*****li bir mesaj";
    }

}
