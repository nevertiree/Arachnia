package com.nostudy.business.universityAndMajor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Lance on 7/16/16.
 */
@AllArgsConstructor
@NoArgsConstructor
public @Data class UniversityAndMajorRowVO {

private String schoolid;
private String schoolname;//可用
private String specialtyname;//可用
private String specialtyurl;
private String specialtytype;//可用
private int edudirectly;
private int f985;
private int f211;
private String schoolprovince;
private String clicks;
private String monthclicks;
private String weekclicks;

}
