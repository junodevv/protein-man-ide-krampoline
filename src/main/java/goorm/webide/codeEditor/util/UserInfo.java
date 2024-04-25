package goorm.webide.codeEditor.util;

import goorm.webide.user.dto.UserDetailDto;
import goorm.webide.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class UserInfo {

    public static Long getUserInfo() {
        UserDetailDto user = (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserNo();
    }

}
