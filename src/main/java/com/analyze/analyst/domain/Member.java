package com.analyze.analyst.domain;

import java.time.LocalDate;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    @Column(name = "member_id")
    private String memberId;
    
    @Column(name = "member_pw")
    private String memberPw;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_role")
    private String memberRole;

    @Column(name ="create_dt")
    private LocalDate createDt;

    @Column(name="upd_dt")
    private LocalDate updDt;

}
