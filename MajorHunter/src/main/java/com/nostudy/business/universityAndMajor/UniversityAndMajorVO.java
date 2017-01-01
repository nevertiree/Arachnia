package com.nostudy.business.universityAndMajor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Lance on 7/16/16.
 */

@AllArgsConstructor
@NoArgsConstructor
public @Data class UniversityAndMajorVO {
    private int schoolid;
    private String majorid;
    private String specialtyname;
    private String specialtytype;


}
