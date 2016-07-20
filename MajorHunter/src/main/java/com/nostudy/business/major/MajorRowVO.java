package com.nostudy.business.major;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Lance on 7/14/16.
 */
@AllArgsConstructor
@Data
public class MajorRowVO {

    private String specialname;
    private String code;
    private String specialurl;
    private String clicks;
    private String monthclicks;
    private String weekclicks;
    private String zycengci;
    private String zytype;
    private String bnum;
    private String znum;
    private String zyid;
    private String ranking;
    private int rankingType;
}
