package com.nostudy.business.major;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Lance on 7/14/16.
 */

@AllArgsConstructor
@Data
public class MajorRespVO {

    private Num totalRecord;
    private List<MajorRowVO> school;
    final public class Num {
        int num;
        public int getNum() {
        return num;
    }
    }

}
