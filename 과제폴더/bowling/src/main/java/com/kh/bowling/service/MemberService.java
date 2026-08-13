package com.kh.bowling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.bowling.model.BillingDTO;
import com.kh.bowling.model.MemberDTO;
import com.kh.bowling.repository.MemberRepository;

@Service
public class MemberService {

    // 요금표: 일반 6000원/게임, VIP는 2000원 할인 -> 4000원/게임
    private static final int NORMAL_PRICE = 6000;
    private static final int VIP_PRICE = 4000;

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 신규 볼러 등록
    // SQL은 1건이지만, 서비스 계층의 트랜잭션 경계를 통일하고 이후 검증/부가 로직이 추가되어도
    // 같은 트랜잭션 안에서 실행되도록 하기 위해 @Transactional을 유지한다.
    @Transactional
    public void insertMember(MemberDTO dto) {
        repository.insertBowler(dto);
    }

    // 현재 이용중인 볼러 목록 조회
    // 데이터 변경이 없는 단순 조회이므로 readOnly = true로 지정해 실수로 이 메서드 안에서 쓰기 작업이 들어가는 것을 방지한다.
    @Transactional(readOnly = true)
    public List<MemberDTO> getActiveList() {
        return repository.selectActiveList();
    }

    // 정산 처리: 요금 계산 -> BILLING insert -> BOWLER.STATUS를 'N'으로 변경
    // insertBilling()과 updateStatusToN() 두 SQL이 하나의 작업 단위여야 한다.
    // 둘 중 하나만 실행되면 "정산 내역은 없는데 상태만 바뀌거나", 반대로 "요금은 청구됐는데
    // 상태는 그대로 남는" 데이터 불일치가 생기므로, @Transactional로 묶어 예외 발생 시
    // 전체 롤백되도록 한다.
    @Transactional
    public void payMember(int bowlerId) {
        MemberDTO member = repository.selectOne(bowlerId);
        if (member == null) {
            return;
        }
        int unitPrice = "VIP".equals(member.getGrade()) ? VIP_PRICE : NORMAL_PRICE;
        int totalFee = member.getGameCount() * unitPrice;

        repository.insertBilling(new BillingDTO(bowlerId, totalFee));
        repository.updateStatusToN(bowlerId);
    }

    // 회원 물리 삭제 (CASCADE로 BILLING 이력도 같이 삭제됨)
    // 실제 삭제는 DB의 ON DELETE CASCADE가 처리하지만, 삭제 도중 예외가 발생했을 때
    // 서비스 계층에서 일관되게 롤백을 보장하기 위해 @Transactional을 붙였다.
    @Transactional
    public void deleteMember(int bowlerId) {
        repository.deleteBowler(bowlerId);
    }

    // 전체 데이터 리셋 (BOWLER 삭제 -> CASCADE로 BILLING도 삭제, 시퀀스 초기화)
    // resetAll()은 "BOWLER 삭제 + 시퀀스 2개 초기화"로 SQL 3건을 순차 실행한다.
    // 예를 들어 BOWLER 삭제 후 시퀀스 초기화 중 하나가 실패하면 데이터는 지워졌는데
    // 시퀀스는 이전 값 그대로인 상태가 남을 수 있으므로, 반드시 하나의 트랜잭션으로 묶어
    // 전부 성공하거나 전부 롤백되도록 한다.
    @Transactional
    public void resetAll() {
        repository.resetAll();
    }

    // 정산 건별 전체 조회 (최신순)
    // 조회 전용 메서드이므로 readOnly = true로 지정했다.
    @Transactional(readOnly = true)
    public List<BillingDTO> getSalesReport() {
        return repository.selectAllBilling();
    }
}
