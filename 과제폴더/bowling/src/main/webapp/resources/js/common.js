// 정산 처리 전 확인
function confirmPay() {
    return confirm('정산 처리하시겠습니까?');
}

// 회원 삭제 전 확인
function confirmDelete() {
    return confirm('회원 정보를 완전히 삭제합니다. 계속할까요?');
}

// 전체 데이터 리셋 전 확인
function confirmReset() {
    return confirm('전체 데이터를 초기화합니다. 계속할까요?');
}