package com.tutoro.controller;

import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by wojci on 4/26/2017.
 */
@Controller
@RequestMapping("/")
public class TutorsController {

    @Autowired
    private TutorService tutorService;

    private static Logger LOGGER = LoggerFactory.getLogger(TutorsController.class);

    @RequestMapping(value = "/tutors", method = POST)
    public String findAllTutorstAfterLogin(Model model) {
        return "redirect:/tutors";
    }

    @RequestMapping(value = "/tutors", method = GET)
    public String findAllTutors(Model model) {

        List<Tutor> tutors = tutorService.findAll();

        for (Tutor tutor : tutors) {
            Set<Skill> skills = tutor.getSkills();

            Set limitedSkills = (Set) skills
                    .stream()
                    .limit(3)
                    .collect(Collectors.toSet());

            tutor.setSkills(limitedSkills);

        }
        model.addAttribute("tutors", tutors);

        return "tutors";
    }

}
