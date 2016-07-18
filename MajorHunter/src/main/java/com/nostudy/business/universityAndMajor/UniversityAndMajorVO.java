package com.nostudy.business.universityAndMajor;

/**
 * Created by Lance on 7/16/16.
 */
public class UniversityAndMajorVO {
    private String schoolid;
    private String schoolname;
    private String specialtyname;
    private String schoolprovince;
    private String specialtytype;
    private int edudirectly;
    private int f985;
    private int f211;
    private String father_major;

    public UniversityAndMajorVO() {}

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSpecialtyname() {
        return specialtyname;
    }

    public void setSpecialtyname(String specialtyname) {
        this.specialtyname = specialtyname;
    }

    public String getSchoolprovince() {
        return schoolprovince;
    }

    public void setSchoolprovince(String schoolprovince) {
        this.schoolprovince = schoolprovince;
    }

    public String getSpecialtytype() {
        return specialtytype;
    }

    public void setSpecialtytype(String specialtytype) {
        this.specialtytype = specialtytype;
    }

    public int getEdudirectly() {
        return edudirectly;
    }

    public void setEdudirectly(int edudirectly) {
        this.edudirectly = edudirectly;
    }

    public int getF985() {
        return f985;
    }

    public void setF985(int f985) {
        this.f985 = f985;
    }

    public int getF211() {
        return f211;
    }

    public void setF211(int f211) {
        this.f211 = f211;
    }

    public String getFather_major() {
        return father_major;
    }

    public void setFather_major(String father_major) {
        this.father_major = father_major;
    }
}
