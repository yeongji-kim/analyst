package com.analyze.analyst.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String memberId;
    private String memberPw;
    private String memberEmail;
    private String createDt;
    private String updDt;
}
