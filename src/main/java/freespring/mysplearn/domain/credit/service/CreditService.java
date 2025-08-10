package freespring.mysplearn.domain.credit.service;

import freespring.mysplearn.domain.common.exception.ErrorCode;
import freespring.mysplearn.domain.common.exception.SplearnException;
import freespring.mysplearn.domain.credit.dto.CreditNicknameRequestDto;
import freespring.mysplearn.domain.credit.dto.CreditRequestDto;
import freespring.mysplearn.domain.credit.dto.CreditResponseDto;
import freespring.mysplearn.domain.credit.entity.Bank;
import freespring.mysplearn.domain.credit.entity.Credit;
import freespring.mysplearn.domain.credit.repository.CreditRepository;
import freespring.mysplearn.domain.user.entity.User;
import freespring.mysplearn.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<CreditResponseDto> createCredit(Long userId, CreditRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        if(!Pattern.matches( "^(0[1-9]|1[0-2])/\\d{2}$", requestDto.getDate())) throw new SplearnException(ErrorCode.INVALID_DATE_FORMAT);
        if(!(requestDto.getCvc().length() ==3)) throw new SplearnException(ErrorCode.INVALID_CVC);
        //메인카드로 지정했는데 이미 있는경우 그 카드의 main값을 false로 변경
        if(requestDto.getMain() && creditRepository.existsByUserAndMain(user, true)) {
            Credit mainCredit = creditRepository.findByUserAndMain(user, true);
            mainCredit.deleteMain();
            creditRepository.save(mainCredit);
        }
        Credit credit = new Credit(Bank.matchBank(requestDto.getBank()),
                                requestDto.getNumber(),
                                requestDto.getDate(),
                                requestDto.getCvc(),
                                requestDto.getMain(),
                                user);
        Credit newCredit = creditRepository.save(credit);
        CreditResponseDto creditResponseDto = new CreditResponseDto(newCredit);
        return ResponseEntity.ok(creditResponseDto);
    }

    //메인카드 조회
    public ResponseEntity<CreditResponseDto> getCreditMain(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Credit mainCredit = creditRepository.findByUserAndMain(user, true);
        if(mainCredit == null) throw new SplearnException(ErrorCode.NO_MAIN_CARD);
        return ResponseEntity.ok(new CreditResponseDto(mainCredit));
    }

    //전체 카드 조회
    public ResponseEntity<Page<CreditResponseDto>> getAllCredits(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Pageable pageable = PageRequest.of(page, size);
        Page<CreditResponseDto> credits = creditRepository.findAllByUser(user, pageable)
                .map(CreditResponseDto::new);
        return ResponseEntity.ok(credits);
    }


    //특정 카드 조회
    public ResponseEntity<CreditResponseDto> getCredit(Long userId, Long creditId) {
        userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Credit credit = creditRepository.findById(creditId).orElseThrow(() -> new SplearnException(ErrorCode.NO_CREDIT));
        return ResponseEntity.ok(new CreditResponseDto(credit));
    }

    //카드 닉네임 수정
    @Transactional
    public ResponseEntity<CreditResponseDto> updateNicknameCredit(Long userId, Long creditId, CreditNicknameRequestDto requestDto) {
        userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Credit credit = creditRepository.findById(creditId).orElseThrow(() -> new SplearnException(ErrorCode.NO_CREDIT));

        credit.updateNickname(requestDto.getNickname());
        creditRepository.save(credit);

        return ResponseEntity.ok(new CreditResponseDto(credit));
    }

    //메인카드 변경
    @Transactional
    public ResponseEntity<CreditResponseDto> updateMainCredit(Long userId, Long creditId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Credit mainCredit = creditRepository.findByUserAndMain(user, true);
        if(mainCredit != null && Objects.equals(mainCredit.getId(), creditId)) return ResponseEntity.ok(new CreditResponseDto(mainCredit));
        Credit newCredit = creditRepository.findById(creditId).orElseThrow(() -> new SplearnException(ErrorCode.NO_CREDIT));
        // 기존 메인카드 메인 삭제
        if (mainCredit != null) {
            mainCredit.deleteMain();
            creditRepository.save(mainCredit);
        }
        //새로운 카드 메인으로 설정
        newCredit.updateMain();
        creditRepository.save(newCredit);

        return ResponseEntity.ok(new CreditResponseDto(newCredit));
    }

    @Transactional
    public ResponseEntity<Void> deleteCredit(Long userId, Long creditId) {
        userRepository.findById(userId).orElseThrow(() -> new SplearnException(ErrorCode.NO_USER));
        Credit credit = creditRepository.findById(creditId).orElseThrow(() -> new SplearnException(ErrorCode.NO_CREDIT));
        creditRepository.delete(credit);
        return ResponseEntity.noContent().build();
    }
}
