package com.tutoro.controller;

import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by wojci on 4/26/2017.
 */
@Controller
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;


    @RequestMapping(value = "/register", method = GET)
    public String register(Model model) {

        Tutor tutor = new Tutor();
        model.addAttribute("tutor", tutor);
        return "registration";
    }

    @RequestMapping(value = "/register", method = POST)
    public String registerNewTutor(@ModelAttribute Tutor tutor, Model model) {

        tutorService.saveTutor(tutor);

        return "redirect:/tutor/profile/" + tutor.getLogin();
    }

    @RequestMapping(value = "profile/{login}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("login") String login, Model model) {

        if (!model.containsAttribute("tutor")) {
            Tutor tutor = tutorService.findByLogin(login);
            model.addAttribute("tutor", tutor);

        }

        return "profile";

    }
}
