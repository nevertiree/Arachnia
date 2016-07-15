package com.nostudy.business.major;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorVO {
    private String code;
    private String specialname;
    private String zycengci;
    private String zytype;
    private int rankingType;

    //Constructor
    public MajorVO() {}

    public MajorVO(String code, String specialname,String zycengci,String zytype, int rankingType) {
        this.code = code;
        this.specialname = specialname;
        this.zycengci=zycengci;
        this.zytype=zytype;
        this.rankingType = rankingType;}

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

    public String getZycengci() {
        return zycengci;
    }

    public void setZycengci(String zycengci) {
        this.zycengci = zycengci;
    }

    public String getZytype() {
        return zytype;
    }

    public void setZytype(String zytype) {
        this.zytype = zytype;
    }

    public int getRankingType() {
        return rankingType;
    }

    public void setRankingType(int rankingType) {
        this.rankingType = rankingType;
    }
}
