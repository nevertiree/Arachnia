package com.nostudy.business.major;

import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorRespVO {

    private Num totalRecord;
    private List<MajorRowVO> school;

    public List<MajorRowVO> getSchool() {return school;}
    public void setSchool(List<MajorRowVO> school) {this.school = school;}

    public Num getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(Num totalRecord) {
        this.totalRecord = totalRecord;
    }

    final public class Num {
        int num;
        public int getNum() {
        return num;
    }
    }

}
