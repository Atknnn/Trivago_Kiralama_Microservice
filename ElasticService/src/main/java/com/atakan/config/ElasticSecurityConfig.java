package com.atakan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class ElasticSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenfilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * Spring Security gelen istekleri filtrelemek için belli methodları kullanır bunlar;
         * - requestMatcher -> filtrelenecek isteklerin URL bilgilerini işlemek için kullanılır.
         * - permitAll -> bütün istekler için izin ver. oturum zorunululuğu yoktur.
         * - authenticated -> oturum açmayı zorunlu kıl.
         * - anyRequest -> tüm istekler
         * - anyMatcher -> tüm eşleşmeler için
         */

         /**
         * Spring Boot 3.0 öncesi Kullanım
         */
//        httpSecurity.authorizeRequests()
//                .requestMatchers("/elastic-user-profile/find-all")
//                .permitAll()
//                .anyRequest().authenticated();
//        httpSecurity.formLogin();
        /**
         * Spring Boot 3.0 sonrası
         */
        httpSecurity.authorizeHttpRequests(req->
                req.requestMatchers(
                        "/elastic-user-profile/find-all",
                        "/elastic-user-profile/get-message",
                        "/swagger-ui/**", // /** diğer tüm uzantılar demektir.
                        "/v3/api-docs/**",
                        "/yetki/**"
                ).permitAll()
                        .requestMatchers("/elastic-user-profile/get-secret-message").hasAnyRole("Super_Admin")
                        .requestMatchers("/admin/**").hasAnyRole("Admin","Super_Admin")
                        .anyRequest().authenticated()

                );
        /**
         * _csrf respApi kullanılırken kapatılır. bir güvenlik önlemi türüdür.
         */
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        /**
         * spring 3.0 öncesi
         */
        //httpSecurity.csrf().disable();

        /**
         *
         * Roller, Yetkiler gibi kullanıcı bazlı işlemlerin kontrol edilebilmesi için filter içine Spring in yöneteceği
         * bir user tanımlanması ve bu işlemin türü belirlenmelidir.
         */
        httpSecurity.addFilterBefore(getJwtTokenfilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
