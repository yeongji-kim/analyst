package com.analyze.analyst.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analyze.analyst.domain.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
    Optional <Member> findByMemberEmail(String memberEmail);                             // 이메일로 회원정보 찾기 
    Optional <Member> findByMemberIdAndMemberEmail(String memberId, String memberEmail);  // 이메일과 아이디로 회원정보 찾기
    boolean existsByMemberId(String memberId);          // 중복아이디 검사
    boolean existsByMemberEmail(String memberEmail);    // 중복이메일 검사

}
