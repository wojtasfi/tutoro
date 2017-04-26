package com.tutoro.controller;

import com.tutoro.dto.SkillForm;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "addskill/{login}", method = RequestMethod.GET)
    public String addSkillPage(@PathVariable String login, Model model) {
        SkillForm skillForm = new SkillForm();
        Skill skill = new Skill();

        Tutor tutor = tutorService.findByLogin(login);
        skill.setTutor(tutor);
        int skillId = skillService.saveSkill(skill).getId();

        skillForm.setSkillId(skillId);
        System.out.println(skillForm.toString());
        model.addAttribute("skillForm", skillForm);

        return "addSkill";
    }

    @RequestMapping(value = "addskill", method = RequestMethod.POST)
    public String addSkill(@ModelAttribute SkillForm skillForm,
                           @RequestParam String action,
                           Model model) {

        Skill skill = skillService.getSkillById(skillForm.getSkillId());

        if (action.equals("tag")) {

            skill.addTag(skillForm.getTag());
            skill.setName(skillForm.getName());
            skillService.saveSkill(skill);

            skillForm.setTag(null);
            skillForm.setTags(skill.getTags());
            model.addAttribute("skillForm", skillForm);

            return "addskill";

        } else if (action.equals("skill")) {
            tutorService.addSkill(skill, skill.getTutor());
            skillService.saveSkill(skill);
            return "redirect:/tutor/profile/" + skill.getTutor().getLogin();
        }
        return "addskill";
    }
}
