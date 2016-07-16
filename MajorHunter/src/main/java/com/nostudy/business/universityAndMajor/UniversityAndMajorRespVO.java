package com.nostudy.business.universityAndMajor;

import com.nostudy.business.university.UniversityRowVO;

import java.util.List;

/**
 * Created by Lance on 7/16/16.
 */
public class UniversityAndMajorRespVO {
    final class Num{
        int num;
        public int getNum() {return num;}
        public void setNum(int num) {this.num = num;}
    }

    private Num totalRecord;
    private List<UniversityRowVO> school;

    public Num getTotalRecord() {return totalRecord;}
    public void setTotalRecord(Num totalRecord) {this.totalRecord = totalRecord;}

    public List<UniversityRowVO> getSchool() {return school;}
    public void setSchool(List<UniversityRowVO> school) {this.school = school;}
}
