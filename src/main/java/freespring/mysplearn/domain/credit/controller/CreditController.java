package freespring.mysplearn.domain.credit.controller;

import freespring.mysplearn.domain.auth.AuthUser;
import freespring.mysplearn.domain.credit.dto.CreditNicknameRequestDto;
import freespring.mysplearn.domain.credit.dto.CreditRequestDto;
import freespring.mysplearn.domain.credit.dto.CreditResponseDto;
import freespring.mysplearn.domain.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/my/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    //카드 생성
    @PostMapping("/credit")
    public ResponseEntity<CreditResponseDto> creatCredit(@AuthenticationPrincipal AuthUser authUser, @RequestBody @Validated CreditRequestDto requestDto) {
        return creditService.createCredit(authUser.getUserId(), requestDto);
    }

    //메인카드 조회
    @GetMapping("/main")
    public ResponseEntity<CreditResponseDto> getCreditMain(@AuthenticationPrincipal AuthUser authUser) {
        return creditService.getCreditMain(authUser.getUserId());
    }

    //카드 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Page<CreditResponseDto>> getAllCredits(@AuthenticationPrincipal AuthUser authUser, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return creditService.getAllCredits(authUser.getUserId(), page, size);
    }

    //특정 카드 조회
    @GetMapping("/{creditId}")
    public ResponseEntity<CreditResponseDto> getCredit( @AuthenticationPrincipal AuthUser authUser, @PathVariable Long creditId) {
        return creditService.getCredit(authUser.getUserId(), creditId);
    }
    
    //카드 닉네임 수정
    @PutMapping("/{creditId}")
    public ResponseEntity<CreditResponseDto> updateCredit(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long creditId, @RequestBody @Validated CreditNicknameRequestDto requestDto) {
        return creditService.updateNicknameCredit(authUser.getUserId(), creditId, requestDto);
    }

    //메인카드 변경
    @PutMapping("/{creditId}/main")
    public ResponseEntity<CreditResponseDto> updateMainCredit(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long creditId) {
        return creditService.updateMainCredit(authUser.getUserId(), creditId);
    }

    //카드 삭제
    @DeleteMapping("/{creditId}")
    public ResponseEntity<Void> deleteCredit(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long creditId) {
        return creditService.deleteCredit(authUser.getUserId(), creditId);
    }
}
