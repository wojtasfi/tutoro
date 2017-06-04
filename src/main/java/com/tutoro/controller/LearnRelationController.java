package com.tutoro.controller;

import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.LearnRelationService;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wojci on 5/23/2017.
 */
@Controller
@RequestMapping("/relations")
public class LearnRelationController {

    private static Logger LOGGER = LoggerFactory.getLogger(LearnRelationController.class);
    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private LearnRelationService learnRelationService;


    @RequestMapping(value = "/save", method = GET)
    private String saveLearnRelation(@RequestParam String studentName,
                                     @RequestParam Long skillId,
                                     Model model) {

        Skill skill = skillService.getSkillById(skillId);
        Tutor teacher = tutorService.findOne(skill.getTutor().getId());
        Tutor student = tutorService.findByUsername(studentName);

        LearnRelation learnRelation = new LearnRelation();
        learnRelation.setTeacher(teacher);
        learnRelation.setStudent(student);
        learnRelation.setSkill(skill);

        learnRelationService.saveLearnRelation(learnRelation);
        model.addAttribute("tutor", student);
        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(student.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }
        return "redirect:/tutor/profile/" + encodedUsername;

    }

}
