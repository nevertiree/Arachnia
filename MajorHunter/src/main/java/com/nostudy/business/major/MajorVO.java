package com.nostudy.business.major;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Lance on 7/14/16.
 */
@AllArgsConstructor
@Data
public class MajorVO {
    private String code;
    private String specialname;
    private String zycengci;
    private String zytype;
    private int rankingType;

    public MajorVO(){}
}
