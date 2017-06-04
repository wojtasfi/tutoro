package com.tutoro.service;

import com.tutoro.dao.LearnRelationRepository;
import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.entities.LearnRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wojci on 5/23/2017.
 */
@Service
public class LearnRelationService {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    LearnRelationRepository learnRelationRepository;

    public LearnRelation saveLearnRelation(LearnRelation learnRelation) {
        return learnRelationRepository.save(learnRelation);
    }
}
