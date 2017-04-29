package com.tutoro;

import com.tutoro.dao.TutorRepository;
import com.tutoro.entities.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TutoroApplication {

    @Autowired
    TutorRepository tutorRepository;

    public static void main(String[] args) {

        Tutor tutor = new Tutor();
        tutor.setLogin("tutor");
        tutor.setEmail("tutor@tutor.pl");
        tutor.setLastName("tutored");
        tutor.setSkype("skype11");
        tutor.setPassword("pollop");

        SpringApplication.run(TutoroApplication.class, args);


    }

    @PostConstruct
    public void addTutor() {
        Tutor tutor = new Tutor();
        tutor.setLogin("tutor");
        tutor.setEmail("tutor@tutor.pl");
        tutor.setLastName("tutored");
        tutor.setSkype("skype11");
        tutor.setPassword("pollop");

        tutorRepository.save(tutor);
    }
}
