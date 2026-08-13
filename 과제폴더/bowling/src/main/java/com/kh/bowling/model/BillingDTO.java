package com.kh.bowling.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingDTO {

    private int billingId;
    private int bowlerId;
    private int totalFee;
    private Timestamp paymentDate;
    private String bowlerName;

    // 정산 처리 시 계산된 요금으로 BILLING insert용 객체를 만들 때 사용
    public BillingDTO(int bowlerId, int totalFee) {
        this.bowlerId = bowlerId;
        this.totalFee = totalFee;
    }
}
