package goorm.webide.codeEditor.dto;


import goorm.webide.codeEditor.entity.Code;
import goorm.webide.user.entity.User;

import java.time.LocalDateTime;

import static goorm.webide.codeEditor.util.UserInfo.getUserInfo;

public record CodeDto(
        Long codeNo,
        User user,
        String text,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

    public static CodeDto of(User user, String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new CodeDto(null, user, text, createdAt, updatedAt);
    }

    public static CodeDto of(String text) {
        return new CodeDto(null, null, text, null, null);
    }

    public static CodeDto from(Code code) {
        return new CodeDto(code.getCodeNo(), code.getUser(), code.getText(), code.getCreatedAt(), code.getUpdatedAt());
    }

    public Code toEntity(User user) {
        return Code.of(user, text);
    }

}
