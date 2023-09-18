package com.analyze.analyst.member.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.analyze.analyst.domain.Member;
import com.analyze.analyst.member.dto.MemberDTO;
import com.analyze.analyst.member.service.MemberService;
import com.analyze.analyst.repos.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO,Member.class);
        memberRepository.save(member);


    }
}
