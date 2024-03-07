package com.atakan.controller;

import com.atakan.dto.request.LoginRequestDto;
import com.atakan.dto.request.RegisterRequestDto;
import com.atakan.dto.response.BaseResponseDto;
import com.atakan.dto.response.LoginResponseDto;
import com.atakan.dto.response.RegisterResponseDto;
import com.atakan.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.atakan.constants.RestApiUrls.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Value("${buraya-canimin-istedigini-yazarim.okuyabilirim}")
    private String ifade;

    @GetMapping("/getmessage")
    public String getMessage(){
        return "Bu Auth Servistir";
    }

    @PostMapping(REGISTER)
    /**
     * Login.html:1 Access to fetch at 'http://34.163.201.190:9090/api/v1/auth/register' from origin
     * 'http://localhost:63342' has been blocked by CORS policy: Response to preflight request doesn't pass access
     * control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
     * If an opaque response serves your needs, set the request's mode to 'no-cors' to fetch the resource with
     * CORS disabled.
     *
     * DİKKAT!!!
     * Yukarıda bulunan hata, sunucuya gelen isteği sunucunun dışından bir yerden gelmesi sonucunda oluşan bir hatadır.
     * bunu aşmak için sınıf,method yada secutiry ayarlarında istek atabilecek end-point leri tanımlamak gereklidir.
     *
     */
    @CrossOrigin("*")
    public ResponseEntity<BaseResponseDto<RegisterResponseDto>> register(@RequestBody @Valid RegisterRequestDto dto){
        authService.register(dto);
        System.out.println("okunan ifade....: "+ ifade);
        return ResponseEntity.ok(BaseResponseDto.<RegisterResponseDto>builder()
                        .responseCode(200)
                        .data(RegisterResponseDto.builder()
                                .isRegister(true)
                                .message("Üyelik Başarı ile gerçekleşti")
                                .build())
                .build());
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<BaseResponseDto<LoginResponseDto>> login(@RequestBody @Valid LoginRequestDto dto){
        String token = authService.login(dto);
        return ResponseEntity.ok(BaseResponseDto.<LoginResponseDto>builder()
                        .responseCode(200)
                        .data(LoginResponseDto.builder()
                                .isLogin(true)
                                .token(token)
                                .build())
                .build());
    }
}
