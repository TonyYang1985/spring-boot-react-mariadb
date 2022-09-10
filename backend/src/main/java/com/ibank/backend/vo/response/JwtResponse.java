package com.ibank.backend.vo.response;

import com.ibank.backend.vo.BaseResponse;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JwtResponse extends BaseResponse{
	private static final long serialVersionUID = 1L;
    private String type = "Bearer";
    private  String jwttoken;
}
