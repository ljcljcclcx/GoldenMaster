package com.clcx.goldenmaster.ui.appraisal;

/**
 * Created by ljc123 on 2016/10/29.
 */

public class AppraisalType {
    private String name;
    private float score;

    public AppraisalType(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
