package com.analyze.analyst.member.service;
import org.springframework.stereotype.Service;

import com.analyze.analyst.member.dto.MemberDTO;

@Service
public interface MemberService {
    public void save(MemberDTO memberDTO);
}
