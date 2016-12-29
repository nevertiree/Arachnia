package com.nevertiree.domain;

/**
 * Created by Lance Wang on 2016/12/27.
 */
public class BookVO {

    private String id;
    private String isbn;
    private String name;
    private int voteNum;
    private double score;
    private double scoreRank5;
    private double scoreRank4;
    private double scoreRank3;
    private double scoreRank2;
    private double scoreRank1;

    public String toString(){
        String result = "";
        result += "\nname:";
        result += this.getName();
        result += "\nreaderNumber:";
        result += this.getVoteNum();
        result += "\nscore:";
        result += this.getScore();
        result += "\nscoreRank5:";
        result += this.getScoreRank5();
        result += "\nscoreRank4:";
        result += this.getScoreRank4();
        result += "\nscoreRank3:";
        result += this.getScoreRank3();
        result += "\nscoreRank2:";
        result += this.getScoreRank2();
        result += "\nscoreRank1:";
        result += this.getScoreRank1();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScoreRank5() {
        return scoreRank5;
    }

    public void setScoreRank5(double scoreRank5) {
        this.scoreRank5 = scoreRank5;
    }

    public double getScoreRank4() {
        return scoreRank4;
    }

    public void setScoreRank4(double scoreRank4) {
        this.scoreRank4 = scoreRank4;
    }

    public double getScoreRank3() {
        return scoreRank3;
    }

    public void setScoreRank3(double scoreRank3) {
        this.scoreRank3 = scoreRank3;
    }

    public double getScoreRank2() {
        return scoreRank2;
    }

    public void setScoreRank2(double scoreRank2) {
        this.scoreRank2 = scoreRank2;
    }

    public double getScoreRank1() {
        return scoreRank1;
    }

    public void setScoreRank1(double scoreRank1) {
        this.scoreRank1 = scoreRank1;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
}


}