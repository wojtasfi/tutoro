package com.tutoro.controller;

import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wojci on 4/26/2017.
 */
@Controller
@RequestMapping("/tutor")
public class TutorsController {

    @Autowired
    private TutorService tutorService;

    private static Logger LOGGER = LoggerFactory.getLogger(TutorsController.class);


    @RequestMapping(value = "/tutors", method = GET)
    public String findAllTutors(Model model) {

        List<Tutor> tutors = tutorService.findAll();
        model.addAttribute("tutors", tutors);

        return "registration";
    }

}
