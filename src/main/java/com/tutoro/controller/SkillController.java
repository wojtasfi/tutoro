package com.tutoro.controller;

import com.tutoro.dto.SkillForm;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wojci on 4/26/2017.
 */
@Controller
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    private static Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    @RequestMapping(value = "addskill/{login}", method = RequestMethod.GET)
    public String addSkillPage(@PathVariable String login, Model model) {
        SkillForm skillForm = new SkillForm();
        Skill skill = new Skill();

        Tutor tutor = tutorService.findByLogin(login);
        skill.setTutor(tutor);
        Long skillId = skillService.saveSkill(skill).getId();

        skillForm.setSkillId(skillId);
        model.addAttribute("skillForm", skillForm);

        return "addSkill";
    }

    @RequestMapping(value = "editSkill", method = RequestMethod.GET)
    public String editSkillPage(@RequestParam Long skillId, Model model) {
        SkillForm skillForm = new SkillForm();
        Skill skill = skillService.getSkillById(skillId);

        skillForm.setSkillId(skillId);
        skillForm.setName(skill.getName());

        Set<String> tags = skill.getTags();
        String stringTags = tags.stream()
                .collect(Collectors.joining(","));

        skillForm.setTags(stringTags);
        model.addAttribute("skillForm", skillForm);

        return "addSkill";
    }

    @RequestMapping(value = "addskill", method = RequestMethod.POST)
    public String addSkill(@ModelAttribute SkillForm skillForm,
                           Model model) {

        Skill skill = skillService.getSkillById(skillForm.getSkillId());
        skill.setName(skillForm.getName());

        String[] stringTags = skillForm.getTags().split(",");
        Set<String> tags = new HashSet<>(Arrays.asList(stringTags));
        skill.setTags(tags);
        LOGGER.info(skill.toString());

        tutorService.addSkill(skill, skill.getTutor());
        skillService.saveSkill(skill);
        return "redirect:/tutor/profile/edit/" + skill.getTutor().getUsername();

    }

}
