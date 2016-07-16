package com.nostudy.business.university;

import java.util.List;

/**
 * Created by Lance on 7/15/16.
 */
public class UniversityRespVO {

    final public class Num {
        int num;
        public int getNum() {
            return num;
        }
    }

    private Num totalRecord;
    private List<UniversityRowVO> school;

    public Num getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(Num totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<UniversityRowVO> getSchool() {
        return school;
    }
    public void setSchool(List<UniversityRowVO> school) {
        this.school = school;
    }

}
