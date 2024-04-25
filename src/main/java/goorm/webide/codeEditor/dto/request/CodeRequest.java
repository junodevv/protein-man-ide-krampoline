package goorm.webide.codeEditor.dto.request;


import goorm.webide.codeEditor.dto.CodeDto;

public record CodeRequest(String text) {
    public CodeDto toDto() {
        return CodeDto.of(text);
    }
}
