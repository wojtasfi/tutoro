package com.tutoro.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wojci on 5/23/2017.
 */
@Entity
public class LearnRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Tutor teacher;
    private Tutor student;
    private Skill skill;

}
