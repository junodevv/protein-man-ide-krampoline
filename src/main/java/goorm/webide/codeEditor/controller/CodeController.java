package goorm.webide.codeEditor.controller;

import goorm.webide.codeEditor.dto.request.CodeRequest;
import goorm.webide.codeEditor.dto.response.CodeResponse;
import goorm.webide.codeEditor.exception.CustomMessage;
import goorm.webide.codeEditor.service.CodeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static goorm.webide.codeEditor.exception.CustomBody.errorResponse;
import static goorm.webide.codeEditor.exception.CustomBody.successResponse;

@RequiredArgsConstructor
@RestController
public class CodeController {

    public final CodeService codeService;

    @GetMapping("/code/{codeNo}")
    public ResponseEntity<Object> getCode(@PathVariable("codeNo") Long codeNo) {
        try {
            CodeResponse response = CodeResponse.from(codeService.getCode(codeNo));
            return successResponse(CustomMessage.OK, response);
        } catch (EntityNotFoundException e) {
            return errorResponse(CustomMessage.NO_CODE);
        }
    }

    @PostMapping("/code")
    public ResponseEntity<Object> saveCode(@RequestBody CodeRequest request) {
        try {
            Long codeNo = codeService.saveCode(request.toDto());
            CodeResponse response = CodeResponse.from(codeService.getCode(codeNo));
            return successResponse(CustomMessage.OK, response);
        } catch (Exception e) {
            return errorResponse(CustomMessage.BAD_DUPLICATE_CODE);
        }



    }

    @PutMapping("/code/{codeNo}")
    public ResponseEntity<Object> updateCode(@PathVariable("codeNo") Long codeNo, @RequestBody CodeRequest request) {
        codeService.updateCode(codeNo, request.toDto());
        CodeResponse response = CodeResponse.from(codeService.getCode(codeNo));
        return successResponse(CustomMessage.OK, response);
    }


    /* Todo : 코드 컴파일 결과 추가 */
}
