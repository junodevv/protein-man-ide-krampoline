package goorm.webide.codeEditor.service;

import goorm.webide.codeEditor.dto.CodeDto;
import goorm.webide.codeEditor.entity.Code;
import goorm.webide.codeEditor.repository.CodeRepository;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static goorm.webide.codeEditor.util.UserInfo.getUserInfo;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CodeService {

    private final CodeRepository codeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CodeDto getCode(Long codeNo) {
        return codeRepository.findById(codeNo).map(CodeDto::from)
                .orElseThrow(() -> new EntityNotFoundException("코드가 없습니다"));
    }

    public Long saveCode(CodeDto dto) {
        Long userNo = getUserInfo();
        User user = userRepository.getReferenceById(userNo);
        Code code = dto.toEntity(user);
        Long codeNo = codeRepository.save(code).getCodeNo();
        return codeNo;
    }

    public void updateCode(Long codeNo, CodeDto dto) {
        Code code = codeRepository.getReferenceById(codeNo);
        code.setText(dto.text());
    }

    public void deleteCode(Long codeNo) {
        codeRepository.deleteById(codeNo);
    }


}
