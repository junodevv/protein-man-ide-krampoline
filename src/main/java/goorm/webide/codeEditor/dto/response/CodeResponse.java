package goorm.webide.codeEditor.dto.response;

import goorm.webide.codeEditor.dto.CodeDto;

import java.time.LocalDateTime;

public record CodeResponse(String text, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static CodeResponse from(CodeDto dto) {
        return new CodeResponse(dto.text(), dto.createdAt(), dto.updatedAt());
    }

}
