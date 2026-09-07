package com.kh.bowling.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.bowling.model.BillingDTO;
import com.kh.bowling.model.MemberDTO;

@Mapper
public interface MemberMapper {

    // 신규 볼러 등록 (BOWLER_ID는 시퀀스로 채번, STATUS는 'Y' 고정)
    void insertBowler(MemberDTO dto);

    // 현재 이용중(STATUS='Y')인 볼러 목록 조회
    List<MemberDTO> selectActiveList();

    // 정산 시 요금 계산에 필요한 GAME_COUNT, GRADE를 보기 위한 단건 조회
    MemberDTO selectOne(int bowlerId);

    // 정산 완료 처리: 삭제하지 않고 STATUS만 'N'으로 변경
    void updateStatusToN(int bowlerId);

    // 회원 물리 삭제 (FK의 ON DELETE CASCADE로 BILLING 이력도 같이 삭제됨)
    void deleteBowler(int bowlerId);

    // 정산 내역 insert (BILLING_ID는 시퀀스 채번, 결제일자는 SYSDATE 기본값)
    void insertBilling(BillingDTO dto);

    // 전체 리셋 1단계: BOWLER 전부 삭제 (CASCADE로 BILLING도 같이 삭제)
    void deleteAllBowlers();

    // 전체 리셋 2단계: BOWLER_ID 시퀀스 초기화
    void resetBowlerSequence();

    // 전체 리셋 3단계: BILLING_ID 시퀀스 초기화
    void resetBillingSequence();

    // 정산 건별 전체 조회 (BOWLER와 조인해서 회원명도 같이, 최신순)
    List<BillingDTO> selectAllBilling();
}
