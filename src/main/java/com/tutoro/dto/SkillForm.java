package com.tutoro.dto;

import com.tutoro.entities.Skill;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wojci on 4/25/2017.
 */

public class SkillForm {

    private int skillId;
    private String name;
    private String tag;
    Set<String> tags = new HashSet<>();

    public Set<String> getTags() {
        return tags;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public Skill toSkill() {
        Skill skill = new Skill();
        skill.setName(this.name);

        return skill;
    }

    @Override
    public String toString() {
        return "SkillForm{" +
                "skillId=" + skillId +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", tags=" + tags +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
