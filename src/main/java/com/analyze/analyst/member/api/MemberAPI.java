package com.analyze.analyst.member.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.analyze.analyst.member.dto.MemberDTO;
import com.analyze.analyst.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberAPI {
    
    final MemberService memberService;

    @GetMapping("/member/signUpPage")
    public ModelAndView signUpPage() throws Exception {
        ModelAndView mav = new ModelAndView("member/signUpPage");
        return mav;
    }

    @PostMapping("/member/signUp")
    public ModelAndView signUp(MemberDTO memberDTO) throws Exception {
        try {
            BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
            memberDTO.setMemberPw(bpe.encode(memberDTO.getMemberPw()));
            memberService.save(memberDTO);
        } catch (Exception e) {
            log.error(e.toString());
        }
        
        ModelAndView mav = new ModelAndView("/index");
        return mav;
    }

}
