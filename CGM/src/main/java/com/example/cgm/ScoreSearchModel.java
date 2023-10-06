package com.example.cgm;

public class ScoreSearchModel {
    Integer scoreId;
    String score;
    String userName;

    public ScoreSearchModel(Integer scoreId, String score, String userName) {
        this.scoreId = scoreId;
        this.score = score;
        this.userName = userName;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public String getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
