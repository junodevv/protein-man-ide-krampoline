package goorm.webide.common.jwt;

import goorm.webide.user.dto.UserDetailDto;
import goorm.webide.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 요청 헤더에서 토큰 가져오기
        String authorization = request.getHeader("Authorization");
        // authorization 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            String requestURI = request.getRequestURI();
            if(tokenNullAllow(requestURI)){
                log.info("token null");
            }
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];
        if (jwtUtil.isExpired(token)) {
            log.info("token expired");
            filterChain.doFilter(request, response);
            return;
        }

//        String username = jwtUtil.getUsername(token);
        String email = jwtUtil.getEmail(token);
        Long userNo = jwtUtil.getUserNo(token);


        User user = User.builder()
                .userNo(userNo)
                .email(email)
                .password("tempPassword")
                .build();
        UserDetailDto userDetailDto = new UserDetailDto(user);

        // 시큐리티 임시 인증 토큰생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetailDto, null,
                userDetailDto.getAuthorities());
        // 세션에 사용자 임시 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }

    private boolean tokenNullAllow(String requestURI){
        if(requestURI.startsWith("/user") || requestURI.matches("/")){
            return false;
        }
        return true;
    }
}
