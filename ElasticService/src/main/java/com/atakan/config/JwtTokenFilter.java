package com.atakan.config;

import com.atakan.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenManager jwtTokenManager;
    @Autowired
    private JwtUserDetails jwtUserDetails;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        /**
         * Uygulamanız içine gelen her bir istek bıradan geçmek zorundadır. Bizde burada gelen isteklerin
         * kontrolünü yaparak yetkili bir tokenlarının olup olmdığına bakarız. Böylece oturum kontrolü
         * saplamış oluruz.
         * Burada ilk olarak gelen isteğin Header ı içinde Bearer Token bilgisi varmı kontrol edriz.
         * Sonra bu token ı ayrıştırarak geçerliliğini kontrol ederiz
         * Geçerli bir token ise bu token a sahip kullanıcının yetkilerini kontrol ederiz.
         * tüm bu işlemlerden sonra Spring için yetki kotrolünde kullanabileceği bir UserDetails
         * nesnesi oluşturarak Filtenin arasına yerleştiririz.
         */
        String bearer_Token = request.getHeader("Authorization");
        if(Objects.nonNull(bearer_Token) && bearer_Token.startsWith("Bearer ")){
            // Token okunur.
            String token = bearer_Token.substring(7);
            // Token içinden kullanıcı id si çekilir.
            Optional<Long> authId = jwtTokenManager.getIdByToken(token);
            // Eğer token içinden geçerli bir id dönmez ise hata fırlatıyoruz.
            if(authId.isEmpty())
                throw new RuntimeException("Geçersiz Token");
            /***
             * Spring Security nin yönetebileceği bir auth nesnesi tanımlıyoruz.
             */
            UserDetails userDetails = jwtUserDetails.findByAuthId(authId.get());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            /**
             * Auth nesnesini güvenlik kabının içine entegre ediyoruz. Böylece oturum oluşturack ve bunun
             * yetkileri üzerinden sayfalara erişimi açacaktır.
             */
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
    }
}
