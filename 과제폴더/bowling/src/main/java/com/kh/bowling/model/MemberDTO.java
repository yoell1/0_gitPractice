package com.kh.bowling.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private int bowlerId;
    private String name;
    private int laneNumber;
    private int gameCount;
    private String grade;
    private String status;

    // 등록 폼에서 넘어온 값으로 신규 볼러를 만들 때 사용 (BOWLER_ID는 시퀀스 채번, STATUS는 'Y' 고정이라 제외)
    public MemberDTO(String name, int laneNumber, int gameCount, String grade) {
        this.name = name;
        this.laneNumber = laneNumber;
        this.gameCount = gameCount;
        this.grade = grade;
        this.status = "Y";
    }
}
