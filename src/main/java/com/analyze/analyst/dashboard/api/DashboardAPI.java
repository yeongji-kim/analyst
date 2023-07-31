package com.analyze.analyst.dashboard.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class DashboardAPI {

    @GetMapping("/")
    public ModelAndView selectImageList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("dashBoard/test");
        log.info("test");
        return mav;
    }

   
}
