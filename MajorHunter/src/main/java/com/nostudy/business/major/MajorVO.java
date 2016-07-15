package com.nostudy.business.major;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorVO {
    private String code;
    private String specialname;
    private int rankingType;
    private String zycengci;

    //Constructor
    public MajorVO() {
    }

    public MajorVO(String no, String name, int rank) {
        this.code = no;
        this.specialname = name;
        this.rankingType = rank;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpecialname() {
        return specialname;
    }

    public void setSpecialname(String specialname) {
        this.specialname = specialname;
    }

    public int getRankingType() {
        return rankingType;
    }

    public void setRankingType(int rankingType) {
        this.rankingType = rankingType;
    }
}
