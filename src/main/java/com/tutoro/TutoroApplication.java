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

        SpringApplication.run(TutoroApplication.class, args);


    }

    @PostConstruct
    public void addTutor() {
        Tutor tutor = new Tutor();
        tutor.setUsername("tutor");
        tutor.setEmail("tutor@tutor.pl");
        tutor.setName("Wojciech");
        tutor.setLastName("Figas");
        tutor.setSkype("skypeTutor");
        tutor.setPassword("pollop");

        tutorRepository.save(tutor);
    }
}
