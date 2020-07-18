package com.mincho.Restful.user;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    @Size(min=2 , message= "Name은 2글자 부탁드립니다")
    private String name;
    @Past //past time only
    private Date joinDate;
}
