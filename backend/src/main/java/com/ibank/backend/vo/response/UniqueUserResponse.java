package com.ibank.backend.vo.response;

import com.ibank.backend.vo.BaseResponse;
import com.ibank.backend.vo.UserVo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UniqueUserResponse extends BaseResponse{
    protected UserVo user;
}
