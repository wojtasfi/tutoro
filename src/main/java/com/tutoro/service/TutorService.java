package com.tutoro.service;

import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wojci on 4/16/2017.
 */

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    SkillRepository skillRepository;

    public Tutor saveTutor(Tutor tutor) {
        tutorRepository.save(tutor);
        return tutor;
    }


    public Tutor findByLogin(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        return tutor;
    }

    public void addSkill(Skill skill, Tutor tutor) {
        tutor.addSkill(skill);
        tutorRepository.save(tutor);
    }

    public boolean checkIfTutorExists(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        if (tutor == null) {
            return false;
        }
        return true;
    }

    public Tutor findByUsername(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        return tutor;
    }

    public List<Tutor> findAll() {
        return (List<Tutor>) tutorRepository.findAll();
    }
}
