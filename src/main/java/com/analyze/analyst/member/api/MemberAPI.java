package com.analyze.analyst.member.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MemberAPI {
    
    @GetMapping("/signUpPage")
    public ModelAndView signUpPage() throws Exception {
        ModelAndView mav = new ModelAndView("member/signUpPage");
        return mav;
    }


}
