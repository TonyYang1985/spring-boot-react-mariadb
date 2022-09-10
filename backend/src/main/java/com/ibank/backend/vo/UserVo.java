package com.ibank.backend.vo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVo implements Serializable {
 
    private int id;

    private String firstname;

    private String lastname;

    private String email;

}
