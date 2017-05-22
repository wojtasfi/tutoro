package com.tutoro;

import com.tutoro.dao.TutorRepository;
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
//        Tutor tutor = new Tutor();
//        tutor.setUsername("tutor");
//        tutor.setEmail("tutor@tutor.pl");
//        tutor.setName("Wojciech");
//        tutor.setLastName("Figas");
//        tutor.setSkype("skypeTutor");
//        tutor.setPassword("pollop");
//        tutor.setStory("I like playing guitar. I have a great tate in music and I love talk about it. Moreover I am a great" +
//                "cook- have read many books and been to many lectures. I would be very happy to share my knowledge about" +
//                "my passions and meet new people to connect with.");
//
//        Tutor tutor2 = new Tutor();
//        tutor2.setUsername("agatka");
//        tutor2.setEmail("monkey@monkey.pl");
//        tutor2.setName("Agata");
//        tutor2.setLastName("Figas");
//        tutor2.setSkype("skypeAgatka");
//        tutor2.setPassword("pollop");
//        tutor2.setStory("I love Paris and my husband! ;D");
//
//        Tutor tutor3 = new Tutor();
//        tutor3.setUsername("janek");
//        tutor3.setEmail("janek@janek.pl");
//        tutor3.setName("Jan");
//        tutor3.setLastName("Kowalski");
//        tutor3.setSkype("johnKowal");
//        tutor3.setPassword("pollop");
//        tutor3.setStory("I am a mechanic for 20 year. I know a lot about Renault, BMW and Audi. " +
//                "I would be happy to talk about cars.");
//
//        Tutor tutor4 = new Tutor();
//        tutor4.setUsername("justJusti");
//        tutor4.setEmail("just@justi.pl");
//        tutor4.setName("Justyna");
//        tutor4.setLastName("Malicka");
//        tutor4.setSkype("jMal");
//        tutor4.setPassword("pollop");
//        tutor4.setStory("I am a florist and I love interior design. I own a flower shop and just " +
//                "started my own business as interior designer");
//
//
//        tutorRepository.save(tutor);
//        tutorRepository.save(tutor2);
//        tutorRepository.save(tutor3);
//        tutorRepository.save(tutor4);
    }
}
