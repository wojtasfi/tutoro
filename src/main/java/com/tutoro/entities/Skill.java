package com.tutoro.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wojci on 4/25/2017.
 */

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    private Set<SkillReview> reviews = new HashSet<>();

    @ElementCollection
    private Set<String> tags = new HashSet<>();

    @ManyToOne()
    private Tutor tutor;

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reviews=" + reviews +
                ", tags=" + tags +
                ", tutor=" + tutor +
                '}';
    }

    public Skill() {
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SkillReview> getReviews() {
        return reviews;
    }

    public void setReviews(Set<SkillReview> reviews) {
        this.reviews = reviews;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }
}
