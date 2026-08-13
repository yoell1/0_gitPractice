package com.kh.bowling.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.bowling.mapper.MemberMapper;
import com.kh.bowling.model.BillingDTO;
import com.kh.bowling.model.MemberDTO;

@Repository
public class MemberRepository {

    private final MemberMapper memberMapper;

    public MemberRepository(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    // 신규 볼러 등록 (BOWLER_ID는 시퀀스로 채번, STATUS는 'Y' 고정)
    public void insertBowler(MemberDTO dto) {
        memberMapper.insertBowler(dto);
    }

    // 현재 이용중(STATUS='Y')인 볼러 목록 조회
    public List<MemberDTO> selectActiveList() {
        return memberMapper.selectActiveList();
    }

    // 정산 시 요금 계산에 필요한 GAME_COUNT, GRADE를 보기 위한 단건 조회
    public MemberDTO selectOne(int bowlerId) {
        return memberMapper.selectOne(bowlerId);
    }

    // 정산 완료 처리: 삭제하지 않고 STATUS만 'N'으로 변경
    public void updateStatusToN(int bowlerId) {
        memberMapper.updateStatusToN(bowlerId);
    }

    // 회원 물리 삭제 (FK의 ON DELETE CASCADE로 BILLING 이력도 같이 삭제됨)
    public void deleteBowler(int bowlerId) {
        memberMapper.deleteBowler(bowlerId);
    }

    // 정산 내역 insert (BILLING_ID는 시퀀스 채번, 결제일자는 SYSDATE 기본값)
    public void insertBilling(BillingDTO dto) {
        memberMapper.insertBilling(dto);
    }

    // 전체 리셋: BOWLER 전부 삭제(CASCADE로 BILLING도 같이 삭제) + 시퀀스 초기화
    public void resetAll() {
        memberMapper.deleteAllBowlers();
        memberMapper.resetBowlerSequence();
        memberMapper.resetBillingSequence();
    }

    // 정산 건별 전체 조회 (BOWLER와 조인해서 회원명도 같이, 최신순)
    public List<BillingDTO> selectAllBilling() {
        return memberMapper.selectAllBilling();
    }
}
