package com.nostudy.business.university;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityVO {
    private String schoolid;
    private String schoolname;
    private String province;

    public UniversityVO() {
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}