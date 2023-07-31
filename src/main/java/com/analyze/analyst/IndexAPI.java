package com.analyze.analyst;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import groovy.util.logging.Slf4j;

@Slf4j
@RestController
public class IndexAPI {
    
    @GetMapping("/index")
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
