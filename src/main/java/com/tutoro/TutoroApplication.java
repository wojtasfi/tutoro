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
        tutor.setStory("I like playing guitar. I have a great tate in music and I love talk about it. Moreover I am a great" +
                "cook- have read many books and been to many lectures. I would be very happy to share my knowledge about" +
                "my passions and meet new people to connect with.");

        Tutor tutor2 = new Tutor();
        tutor2.setUsername("agatka");
        tutor2.setEmail("monkey@monkey.pl");
        tutor2.setName("Agata");
        tutor2.setLastName("Figas");
        tutor2.setSkype("skypeAgatka");
        tutor2.setPassword("pollop");
        tutor2.setStory("I love Paris and my husband! ;D");

        tutorRepository.save(tutor);
        tutorRepository.save(tutor2);
    }
}
