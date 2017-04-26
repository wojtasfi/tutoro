package com.tutoro.controller;

import com.tutoro.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wojci on 4/15/2017.
 */

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private TutorService tutorService;

    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }




}
