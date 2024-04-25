package goorm.webide.codeEditor.controller;

import goorm.webide.codeEditor.dto.request.CodeRequest;
import goorm.webide.codeEditor.dto.response.CodeResponse;
import goorm.webide.codeEditor.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CodeController {

    public final CodeService codeService;

    @GetMapping("/code/{codeNo}")
    public ResponseEntity<CodeResponse> getCode(@PathVariable("codeNo") Long codeNo) {
        CodeResponse response = CodeResponse.from(codeService.getCode(codeNo));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/code")
    public ResponseEntity<CodeResponse> saveCode(@RequestBody CodeRequest request) {
        Long codeNo = codeService.saveCode(request.toDto());
        CodeResponse response = CodeResponse.from(codeService.getCode(codeNo));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    /* Todo : 코드 컴파일 결과 추가 */
}
