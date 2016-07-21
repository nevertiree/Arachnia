package com.nostudy.business.universityAndMajor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Lance on 7/16/16.
 */

@AllArgsConstructor
@Data
public class UniversityAndMajorVO {
    private String schoolid;
    private String majorid;
    private String specialtyname;
    private String specialtytype;
}
