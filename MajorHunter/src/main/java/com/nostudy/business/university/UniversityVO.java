package com.nostudy.business.university;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Lance on 7/15/16.
 */

@AllArgsConstructor
@Data
public class UniversityVO {
    private String schoolid;
    private String schoolname;
    private String province;
    private String schooltype;
    private String schoolproperty;
    private int edudirectly;
    private String membership;
    private int f985;
    private int f211;
    private String level;
    private String schoolnature;
}