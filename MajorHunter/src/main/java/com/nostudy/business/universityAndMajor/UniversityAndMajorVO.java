package com.nostudy.business.universityAndMajor;

/**
 * Created by Lance on 7/16/16.
 */

public class UniversityAndMajorVO {
    private String schoolid;
    private String majorid;

    private String specialtyname;

    private String specialtytype;
    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getSpecialtyname() {
        return specialtyname;
    }

    public void setSpecialtyname(String specialtyname) {
        this.specialtyname = specialtyname;
    }

    public String getSpecialtytype() {
        return specialtytype;
    }

    public void setSpecialtytype(String specialtytype) {
        this.specialtytype = specialtytype;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid;
    }
}
