package com.nostudy.business.university;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Lance on 7/15/16.
 */
@AllArgsConstructor
@Data
public class UniversityRespVO {

    final public class Num {
        int num;
        public int getNum() {
            return num;
        }
    }

    private Num totalRecord;
    private List<UniversityRowVO> school;

}
