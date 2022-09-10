package com.ibank.backend.vo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    
    @NotBlank
    @Size(min = 3, max = 20)
    public String username;
  
    @NotBlank
    @Size(max = 50)
    @Email
    public String email;
  
    @NotBlank
    @Size(min = 6, max = 40)
    public String password;
  
}
