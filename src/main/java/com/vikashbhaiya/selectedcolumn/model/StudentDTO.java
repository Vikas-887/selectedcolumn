package com.vikashbhaiya.selectedcolumn.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentDTO {

    private Long studentId;

    @Size(max = 255)
    private String correspondanceAddress;

    @Size(max = 255)
    private String dob;

    @Size(max = 255)
    private String emailId;

    @Size(max = 255)
    private String fathersName;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastQualification;

    @Size(max = 255)
    private String lastname;

    @Size(max = 255)
    private String mobile;

    @Size(max = 255)
    private String photoUrl;

    @Size(max = 255)
    private String yearofPassing;

}
