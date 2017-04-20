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

    @RequestMapping(value = "/register", method = GET)
    public String register(Model model) {

        Tutor tutor = new Tutor();
        System.out.println(tutor.toString());
        model.addAttribute("tutor", tutor);
        return "registration";
    }

    @RequestMapping(value = "/register", method = POST)
    public String registerNewTutor(@ModelAttribute Tutor tutor) {

        tutorService.saveTutor(tutor);
        return "redirect:/myProfile/" + tutor.getLogin();
    }

    @RequestMapping(value = "myProfile/{login}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("login") String login, Model model) {

        if (!model.containsAttribute("tutor")) {
            Tutor tutor = tutorService.findByLogin(login);
            model.addAttribute("tutor", tutor);
        }

        return "myProfile";

    }
}
